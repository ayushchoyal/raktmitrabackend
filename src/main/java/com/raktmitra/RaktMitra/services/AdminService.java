package com.raktmitra.RaktMitra.services;

import com.raktmitra.RaktMitra.entity.Donor;
import com.raktmitra.RaktMitra.entity.Patient;

import java.util.List;

public interface AdminService {
    List<Donor> getAllDonors();
    void deleteDonor(Long id);
    Donor updateDonorStatus(Long id, boolean status);
    List<Patient> getAllPatients();
        void deletePatientById(Long id);

}

