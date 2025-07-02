package com.licenseguard.licenseinventorymanagement.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "licenses")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class License {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String licenseName; // unique display name for the license

    @Column(nullable = false)
    private String softwareName;

    @Column(nullable = false)
    private String licenseType; // subscription/perpetual

    @Column(nullable = false, unique = true)
    private String licenseKey;

    private Integer entitlements; // allowed installations/users

    private LocalDate purchaseDate;

    private LocalDate expirationDate; // expiryDate

    private LocalDate renewalDate;

    private String department;

    private String assignedUser;

    private String licenseStatus; // active, expired, etc.

    private Integer installationCount;

    private String complianceStatus; // calculated or manual

    private String notes;
}
