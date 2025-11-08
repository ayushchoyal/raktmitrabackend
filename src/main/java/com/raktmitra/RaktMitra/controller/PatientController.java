package com.raktmitra.RaktMitra.controller;


import com.raktmitra.RaktMitra.entity.Patient;
import com.raktmitra.RaktMitra.entity.User;
import com.raktmitra.RaktMitra.repository.UserRepo;
import com.raktmitra.RaktMitra.services.PatientService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = {

        "https://rakt-mitra-blood-donation.vercel.app"
}, allowCredentials = "true")
public class PatientController {

    private final PatientService patientService;
    private final UserRepo userRepo;

    public PatientController(PatientService patientService,UserRepo userRepo){
        this.patientService=patientService;
        this.userRepo=userRepo;
    }

    private static final String UPLOAD_DIR = "uploads/";

    @PostMapping("/patient/register")
    public ResponseEntity<?> registerPatient(
            @RequestParam("name") String name,
            @RequestParam("email") String email,
            @RequestParam("phone") String phone,
            @RequestParam("address") String address,
            @RequestParam("dob") String dob,
            @RequestParam("bloodGroup") String bloodGroup,
            @RequestParam("gender") String gender,
            @RequestParam(value = "emergencyContact", required = false) String emergencyContact,
            @RequestParam(value = "image", required = false) MultipartFile image,
            @RequestHeader("Authorization") String token
    ) throws IOException {


        // Save image
        String imagePath = null;
        if (image != null && !image.isEmpty()) {
            File uploadDir = new File(UPLOAD_DIR);
            if (!uploadDir.exists()) uploadDir.mkdirs();

            String fileName = System.currentTimeMillis() + "_" + image.getOriginalFilename();
            Path path = Paths.get(UPLOAD_DIR + fileName);
            Files.write(path, image.getBytes());

            imagePath = "https://raktmitrabackend.onrender.com/uploads/" + fileName;
        }
        User user = userRepo.findByEmail(email).orElse(null);
        Patient patient = new Patient();
        patient.setName(name);
        patient.setEmail(email);
        patient.setPhone(phone);
        patient.setAddress(address);
        patient.setDob(dob);
        patient.setBloodGroup(bloodGroup);
        patient.setGender(gender);

        patient.setImage(imagePath);


        patientService.registerPatient(patient);

        return ResponseEntity.ok(patient);
    }
    @GetMapping("/patients")
    public ResponseEntity<List<Patient>> getAllPatients() {
        return ResponseEntity.ok(patientService.getAllPatients());
    }

    @GetMapping("/patient/{id}")
    public ResponseEntity<Patient>getPatientById(@PathVariable  Long id){
        return patientService.getPatientById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());

    }
}


