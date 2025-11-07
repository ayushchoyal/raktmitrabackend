package com.raktmitra.RaktMitra.repository;


import com.raktmitra.RaktMitra.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PatientRepo extends JpaRepository<Patient,Long> {
    Optional<Patient>findByEmail(String email);

    List<Patient> findByBloodGroupIgnoreCaseContaining(String bloodGroup);
}
