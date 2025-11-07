package com.raktmitra.RaktMitra.controller;

import com.raktmitra.RaktMitra.dto.UserDto;
import com.raktmitra.RaktMitra.entity.Bank;
import com.raktmitra.RaktMitra.entity.User;
import com.raktmitra.RaktMitra.repository.UserRepo;
import com.raktmitra.RaktMitra.services.BankService;
import com.raktmitra.RaktMitra.services.UserService;
import com.raktmitra.RaktMitra.config.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@CrossOrigin(origins = {
        "http://localhost:5173",
        "https://rakt-mitra-blood-donation.vercel.app"
}, allowCredentials = "true")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;


    public UserController(UserService userService){
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody User user) {
        try {
            if (user.getRole() == null || user.getRole().isEmpty()) user.setRole("USER");
            User savedUser = userService.registerUser(user);
            return ResponseEntity.ok(Map.of("success", true, "user", savedUser));
        } catch (Exception e) {
            return ResponseEntity.status(400).body(Map.of("success", false, "error", e.getMessage()));
        }
    }

    @PostMapping("/signin")
    public ResponseEntity<?> signin(@RequestBody Map<String, String> request) {
        String email = request.get("email");
        String password = request.get("password");

        User user = userRepo.findByEmail(email).orElse(null);

        if (user != null && passwordEncoder.matches(password, user.getPassword())) {
            String token = jwtUtil.generateToken(user.getEmail());
            Map<String, Object> response = Map.of(
                    "success", true,
                    "message", "Login successful",
                    "token", token,
                    "user", Map.of(
                            "id", user.getId(),
                            "name", user.getUsername(),
                            "email", user.getEmail(),
                            "phone", user.getPhone(),
                            "role", user.getRole()
                    )
            );
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("success", false, "message", "Invalid email or password"));
        }
    }
    @GetMapping("/user/details")
    public ResponseEntity<UserDto> getUserDetails(@AuthenticationPrincipal UserDetails userDetails) {
        User user = userService.findByEmail(userDetails.getUsername());

        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        UserDto dto = new UserDto(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getPhone(),
                user.getRole()
        );

        return ResponseEntity.ok(dto);
    }


}
