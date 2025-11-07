package com.raktmitra.RaktMitra.dto;

import java.util.List;

public class DonorDto {

    private Long id;
    private String name;
    private String email;
    private String phone;
    private Integer age;
    private String gender;
    private String bloodGroup;

    private String address;
    private String city;
    private String state;
    private String pincode;

    private Double weight;
    private Double height;

    private String food;

    private List<String> chronicDiseases;
    private String currentMedications;
    private String allergies;
    private String previousSurgeries;
    private String lastDonationDate;

    private String recentIllness;
    private String pregnancyStatus;
    private String smokingStatus;
    private String alcoholConsumption;

    private boolean consentToContact;
    private boolean consentToShare;
    private boolean status;

    private String imageUrl;

    // 🔹 Constructors
    public DonorDto() {}

    // You can also add a constructor that takes Donor entity if needed
    // public DonorDto(Donor donor) { ... }

    // 🔹 Getters & Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public Integer getAge() { return age; }
    public void setAge(Integer age) { this.age = age; }

    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }

    public String getBloodGroup() { return bloodGroup; }
    public void setBloodGroup(String bloodGroup) { this.bloodGroup = bloodGroup; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }

    public String getState() { return state; }
    public void setState(String state) { this.state = state; }

    public String getPincode() { return pincode; }
    public void setPincode(String pincode) { this.pincode = pincode; }

    public Double getWeight() { return weight; }
    public void setWeight(Double weight) { this.weight = weight; }

    public Double getHeight() { return height; }
    public void setHeight(Double height) { this.height = height; }

    public String getFood() { return food; }
    public void setFood(String food) { this.food = food; }

    public List<String> getChronicDiseases() { return chronicDiseases; }
    public void setChronicDiseases(List<String> chronicDiseases) { this.chronicDiseases = chronicDiseases; }

    public String getCurrentMedications() { return currentMedications; }
    public void setCurrentMedications(String currentMedications) { this.currentMedications = currentMedications; }

    public String getAllergies() { return allergies; }
    public void setAllergies(String allergies) { this.allergies = allergies; }

    public String getPreviousSurgeries() { return previousSurgeries; }
    public void setPreviousSurgeries(String previousSurgeries) { this.previousSurgeries = previousSurgeries; }

    public String getLastDonationDate() { return lastDonationDate; }
    public void setLastDonationDate(String lastDonationDate) { this.lastDonationDate = lastDonationDate; }

    public String getRecentIllness() { return recentIllness; }
    public void setRecentIllness(String recentIllness) { this.recentIllness = recentIllness; }

    public String getPregnancyStatus() { return pregnancyStatus; }
    public void setPregnancyStatus(String pregnancyStatus) { this.pregnancyStatus = pregnancyStatus; }

    public String getSmokingStatus() { return smokingStatus; }
    public void setSmokingStatus(String smokingStatus) { this.smokingStatus = smokingStatus; }

    public String getAlcoholConsumption() { return alcoholConsumption; }
    public void setAlcoholConsumption(String alcoholConsumption) { this.alcoholConsumption = alcoholConsumption; }

    public boolean isConsentToContact() { return consentToContact; }
    public void setConsentToContact(boolean consentToContact) { this.consentToContact = consentToContact; }

    public boolean isConsentToShare() { return consentToShare; }
    public void setConsentToShare(boolean consentToShare) { this.consentToShare = consentToShare; }

    public boolean isStatus() { return status; }
    public void setStatus(boolean status) { this.status = status; }

    public String getImageUrl() { return imageUrl; }
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }
}
