package com.raktmitra.RaktMitra.controller;

import com.raktmitra.RaktMitra.entity.Donor;
import com.raktmitra.RaktMitra.entity.Patient;
import com.raktmitra.RaktMitra.services.DonorService;
import com.raktmitra.RaktMitra.services.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

@RestController
@RequestMapping("/user/search")
@CrossOrigin(origins = {
        "http://localhost:5173",
        "https://rakt-mitra-blood-donation.vercel.app"
}, allowCredentials = "true")
public class SearchController {

    @Autowired
    private DonorService donorService;

    @Autowired
    private PatientService patientService;

    @GetMapping
    public ResponseEntity<?> searchByBloodGroup(
            @RequestParam String type,
            @RequestParam String bloodGroup) {

        // Decode URL values: A%2B → A+
        String decodedBloodGroup = URLDecoder.decode(bloodGroup, StandardCharsets.UTF_8)
                .replace(" ", "+")    // Fix '+' converted into space
                .trim()
                .toUpperCase();

        System.out.println("Searching for blood group: " + decodedBloodGroup);

        if (type.equalsIgnoreCase("Donor")) {
            List<Donor> donors = donorService.donorSearch(decodedBloodGroup);
            return ResponseEntity.ok(donors);

        } else if (type.equalsIgnoreCase("Patient")) {
            List<Patient> patients = patientService.patientSearch(decodedBloodGroup);
            return ResponseEntity.ok(patients);

        } else {
            return ResponseEntity
                    .badRequest()
                    .body("Invalid type. Use 'Donor' or 'Patient'");
        }
    }
}
