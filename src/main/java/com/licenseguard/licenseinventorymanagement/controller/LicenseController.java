package com.licenseguard.licenseinventorymanagement.controller;

import com.licenseguard.licenseinventorymanagement.model.License;
import com.licenseguard.licenseinventorymanagement.service.LicenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/licenses")
@CrossOrigin(origins = "*")
public class LicenseController {

    private final LicenseService licenseService;

    @Autowired
    public LicenseController(LicenseService licenseService) {
        this.licenseService = licenseService;
    }

    @GetMapping
    public List<License> getAllLicenses() {
        return licenseService.getAllLicenses();
    }

    @GetMapping("/{id}")
    public ResponseEntity<License> getLicenseById(@PathVariable Long id) {
        Optional<License> license = licenseService.getLicenseById(id);
        return license.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/by-key/{licenseKey}")
    public ResponseEntity<License> getLicenseByLicenseKey(@PathVariable String licenseKey) {
        Optional<License> license = licenseService.getLicenseByLicenseKey(licenseKey);
        return license.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public License createLicense(@RequestBody License license) {
        return licenseService.saveLicense(license);
    }

    @PutMapping("/{id}")
public ResponseEntity<License> updateLicense(@PathVariable Long id, @RequestBody License licenseDetails) {
    Optional<License> optionalLicense = licenseService.getLicenseById(id);
    if (optionalLicense.isEmpty()) {
        return ResponseEntity.notFound().build();
    }

    License license = optionalLicense.get();

    // Do NOT update: licenseName, licenseType, licenseKey
    license.setDepartment(licenseDetails.getDepartment());
    license.setSoftwareName(licenseDetails.getSoftwareName());
    license.setEntitlements(licenseDetails.getEntitlements());
    license.setPurchaseDate(licenseDetails.getPurchaseDate());
    license.setExpirationDate(licenseDetails.getExpirationDate());
    license.setRenewalDate(licenseDetails.getRenewalDate());
    license.setAssignedUser(licenseDetails.getAssignedUser());
    license.setLicenseStatus(licenseDetails.getLicenseStatus());
    license.setInstallationCount(licenseDetails.getInstallationCount());
    license.setComplianceStatus(licenseDetails.getComplianceStatus());
    license.setNotes(licenseDetails.getNotes());

    License updatedLicense = licenseService.saveLicense(license);
    return ResponseEntity.ok(updatedLicense);
}


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLicense(@PathVariable Long id) {
        Optional<License> optionalLicense = licenseService.getLicenseById(id);
        if (optionalLicense.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        licenseService.deleteLicense(id);
        return ResponseEntity.noContent().build();
    }
}
