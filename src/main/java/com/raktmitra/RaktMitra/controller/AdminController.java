package com.raktmitra.RaktMitra.controller;


import com.raktmitra.RaktMitra.entity.Donor;
import com.raktmitra.RaktMitra.entity.Patient;
import com.raktmitra.RaktMitra.services.AdminService;
import com.raktmitra.RaktMitra.services.DonorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = {
        "http://localhost:5173",
        "https://rakt-mitra-blood-donation.vercel.app"
}, allowCredentials = "true")
public class AdminController {
    private final AdminService adminService;


    public AdminController(AdminService adminService){
        this.adminService=adminService;
    }

    @GetMapping("/admin/donors")
    public List<Donor> getAllDonors(){
        return adminService.getAllDonors();
    }

    @GetMapping("/admin/patients")
    public List<Patient>getAllPatients(){
        return adminService.getAllPatients();
    }
    @DeleteMapping("/admin/donors/{id}")
    public ResponseEntity<?> deleteDonor(@PathVariable Long id) {
        adminService.deleteDonor(id);
        return ResponseEntity.ok("Donor deleted successfully");
    }

    @PutMapping("/admin/donors/{id}/status")
    public ResponseEntity<Donor> updateDonorStatus(
            @PathVariable Long id,
            @RequestBody Donor donorRequest
    ) {
        Donor updatedDonor = adminService.updateDonorStatus(id, donorRequest.isStatus());
        return ResponseEntity.ok(updatedDonor);
    }
}
