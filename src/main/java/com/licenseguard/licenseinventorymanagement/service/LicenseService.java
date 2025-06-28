package com.licenseguard.licenseinventorymanagement.service;

import com.licenseguard.licenseinventorymanagement.model.License;
import com.licenseguard.licenseinventorymanagement.repository.LicenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LicenseService {

    private final LicenseRepository licenseRepository;

    @Autowired
    public LicenseService(LicenseRepository licenseRepository) {
        this.licenseRepository = licenseRepository;
    }

    public List<License> getAllLicenses() {
        return licenseRepository.findAll();
    }

    public Optional<License> getLicenseById(Long id) {
        return licenseRepository.findById(id);
    }

    public Optional<License> getLicenseByLicenseKey(String licenseKey) {
        return licenseRepository.findByLicenseKey(licenseKey);
    }

    public License saveLicense(License license) {
        return licenseRepository.save(license);
    }

    public void deleteLicense(Long id) {
        licenseRepository.deleteById(id);
    }
}
