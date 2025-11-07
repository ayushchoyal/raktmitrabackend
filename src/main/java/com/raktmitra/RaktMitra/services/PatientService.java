package com.raktmitra.RaktMitra.services;

import com.raktmitra.RaktMitra.entity.Donor;
import com.raktmitra.RaktMitra.entity.Patient;
import com.raktmitra.RaktMitra.repository.PatientRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PatientService {
    private final PatientRepo patientRepo;
    public PatientService(PatientRepo patientRepo){
        this.patientRepo=patientRepo;
    }

    public Patient patientRegistration(Patient patient){
        return patientRepo.save(patient);
    }

    public List<Patient> getAllPatients(){
        return patientRepo.findAll();
    }

    public Optional<Patient> getPatientById(Long id) {
        return patientRepo.findById(id);
    }

    public Patient registerPatient(Patient patient) {
        Optional<Patient> existing = patientRepo.findByEmail(patient.getEmail());

        if (existing.isPresent()) {
            Patient existingPatient = existing.get();
            existingPatient.setAddress(patient.getAddress());
            existingPatient.setDob(patient.getDob());
            existingPatient.setGender(patient.getGender());
            existingPatient.setBloodGroup(patient.getBloodGroup());
            existingPatient.setPhone(patient.getPhone());

            existingPatient.setImage(patient.getImage());
            return patientRepo.save(existingPatient);
        }

        return patientRepo.save(patient);
    }

    public List<Patient> patientSearch(String bloodGroup) {
        return patientRepo.findByBloodGroupIgnoreCaseContaining(bloodGroup);
    }
}
