package com.raktmitra.RaktMitra.controller;

import com.raktmitra.RaktMitra.entity.Bank;
import com.raktmitra.RaktMitra.services.BankService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

@CrossOrigin(origins = {
        "http://localhost:5173",
        "https://rakt-mitra-blood-donation.vercel.app"
}, allowCredentials = "true")
public class BankController {
    private final BankService bankService;

    public BankController(BankService bankService){
        this.bankService=bankService;
    }

    @PostMapping("/admin/add")
    public ResponseEntity<Bank>addBank(@RequestBody Bank bank){
        Bank savedBank= bankService.saveBank(bank);
        return ResponseEntity.ok(savedBank);
    }

    @GetMapping("/admin/banks")
    public ResponseEntity<List<Bank>>getBanks(){
        return ResponseEntity.ok(bankService.getAllBanks());
    }
    @GetMapping("/banks")
    public ResponseEntity<List<Bank>>getAllBanks(){
        return ResponseEntity.ok(bankService.getAllBanks());
    }

    @DeleteMapping("admin/delete/{id}")
    public ResponseEntity<String> deleteBank(@PathVariable Long id) {
        try {
            bankService.deleteBank(id);
            return ResponseEntity.ok("Bank deleted successfully");
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Internal Server Error");
        }
    }

}
