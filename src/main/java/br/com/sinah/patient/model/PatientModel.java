package br.com.sinah.patient.model;

import br.com.sinah.patient.enums.Gender;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

import java.util.UUID;
@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "patients")
public class PatientModel {
    @Column(unique = true, insertable = false, updatable = false, columnDefinition = "serial")
    private Long id;
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID uuid;

    @Column(name = "full_name", nullable = false)
    private String fullName;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Gender gender;

    @Column(name = "date_of_birth", nullable = false)
    private LocalDate dateOfBirth;

    @Column(name = "medical_record_number", unique = true, nullable = false)
    private String medicalRecordNumber;

    @Column(name = "national_id", unique = true)
    private String nationalId;

    @Column
    private String address;

    @Column
    private String phone;

    @Column(name = "clinical_history")
    private String clinicalHistory;


    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

}

