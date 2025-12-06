package com.raktmitra.RaktMitra.services;

import com.raktmitra.RaktMitra.entity.Donor;
import com.raktmitra.RaktMitra.repository.DonorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DonorService {

    @Autowired
    private DonorRepository donorRepository;

    public Donor registerDonor(Donor donor) {
        Optional<Donor> existing = donorRepository.findByEmail(donor.getEmail());
        if (existing.isPresent()) {
            Donor existingDonor = existing.get();
            existingDonor.setAddress(donor.getAddress());
            existingDonor.setCity(donor.getCity());
            existingDonor.setState(donor.getState());
            existingDonor.setDob(donor.getDob());
            existingDonor.setGender(donor.getGender());
            existingDonor.setWeight(donor.getWeight());
            existingDonor.setBloodGroup(donor.getBloodGroup());
            existingDonor.setFoodPreference(donor.getFoodPreference());
            existingDonor.setSmokingStatus(donor.getSmokingStatus());
            existingDonor.setAlcoholConsumption(donor.getAlcoholConsumption());
            existingDonor.setImageUrl(donor.getImageUrl());
            return donorRepository.save(existingDonor);
        }
        return donorRepository.save(donor);
    }

    public List<Donor> getAllDonors() {
        return donorRepository.findByStatusTrueOrderByIdDesc();
    }

    public Optional<Donor> getDonorById(Long id) {
        return donorRepository.findById(id);
    }

    public List<Donor> donorSearch( String bloodGroup) {
        return donorRepository.findByBloodGroupIgnoreCaseAndStatusTrue(bloodGroup);
    }


    public boolean existsEmail(String email) {
        return donorRepository.existsByEmail(email);
    }
}
