package com.licenseguard.licenseinventorymanagement.repository;

import com.licenseguard.licenseinventorymanagement.model.License;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LicenseRepository extends JpaRepository<License, Long> {
    // Additional query methods can be defined here if needed
    Optional<License> findByLicenseKey(String licenseKey);
}
