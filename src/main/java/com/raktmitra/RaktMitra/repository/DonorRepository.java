package com.raktmitra.RaktMitra.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.raktmitra.RaktMitra.entity.Donor;

import java.util.List;
import java.util.Optional;

public interface DonorRepository extends JpaRepository<Donor,Long> {
    Optional<Donor> findByEmail(String email);

    List<Donor> findByStatusTrue();

    List<Donor> findByStatusTrueOrderByIdDesc();

    List<Donor> findByBloodGroupIgnoreCaseContaining(String bloodGroup);
}
