package com.raktmitra.RaktMitra.controller;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import com.raktmitra.RaktMitra.dto.DonorDto;
import com.raktmitra.RaktMitra.entity.User;
import com.raktmitra.RaktMitra.repository.DonorRepository;
import com.raktmitra.RaktMitra.repository.UserRepo;
import com.raktmitra.RaktMitra.services.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.raktmitra.RaktMitra.entity.Donor;
import com.raktmitra.RaktMitra.services.DonorService;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = {
        "http://localhost:5173",
        "https://rakt-mitra-blood-donation.vercel.app"
}, allowCredentials = "true")
public class DonorController {
    @Autowired
    private final DonorService donorService;
    private final UserRepo userRepo;
    public DonorController(DonorService donorService,UserRepo userRepo) {
        this.donorService = donorService;
        this.userRepo=userRepo;
    }

    private static final String UPLOAD_DIR = "uploads/";
    @PostMapping("/register")
    public ResponseEntity<?> registerDonor(
            @RequestParam("name") String name,
            @RequestParam("email") String email,
            @RequestParam("phone") String phone,
            @RequestParam("address") String address,
            @RequestParam("city") String city,
            @RequestParam("state") String state,
            @RequestParam("dob") String dob,
            @RequestParam("weight") Double weight,
            @RequestParam("gender") String gender,
            @RequestParam("bloodGroup") String bloodGroup,
            @RequestParam("foodPreference") String foodPreference,
            @RequestParam("smokingStatus") String smokingStatus,
            @RequestParam("alcoholConsumption") String alcoholConsumption,
            @RequestParam(value = "image", required = false) MultipartFile image
    ) {
        try {
            // ✅ Image handling
            String imageUrl = null;
            if (image != null && !image.isEmpty()) {
                Path uploadPath = Paths.get(UPLOAD_DIR);
                if (!Files.exists(uploadPath)) {
                    Files.createDirectories(uploadPath);
                }

                String fileName = System.currentTimeMillis() + "_" + image.getOriginalFilename();
                Path filePath = uploadPath.resolve(fileName);
                Files.copy(image.getInputStream(), filePath);
                imageUrl = "http://localhost:8080/uploads/" + fileName;

            }

            // ✅ User lookup
            User user = userRepo.findByEmail(email).orElse(null);

            // ✅ Create donor object
            Donor donor = new Donor();
            donor.setName(name);
            donor.setEmail(email);
            donor.setPhone(phone);
            donor.setAddress(address);
            donor.setCity(city);
            donor.setState(state);
            donor.setDob(LocalDate.parse(dob));
            donor.setWeight(weight);
            donor.setGender(gender);
            donor.setBloodGroup(bloodGroup);
            donor.setFoodPreference(foodPreference);
            donor.setSmokingStatus(smokingStatus);
            donor.setAlcoholConsumption(alcoholConsumption);
            donor.setImageUrl(imageUrl);
            donor.setUser(user);

            Donor saved = donorService.registerDonor(donor);
            return ResponseEntity.ok(saved);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Error registering donor: " + e.getMessage());
        }
    }
    @GetMapping("/donor")
    public List<Donor> getAllDonors() {
        return donorService.getAllDonors();
    }

    @GetMapping("/donor/{id}")
    public ResponseEntity<Donor>getDonarById(@PathVariable Long id){
        return donorService.getDonorById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/check-email")
    public ResponseEntity<?> checkEmail(@RequestParam String email) {
        boolean exists = donorService.existsEmail(email);
        return ResponseEntity.ok(Map.of("exists", exists));
    }



}
