package br.com.sinah.patient.dto;

import br.com.sinah.patient.enums.Gender;

import java.time.LocalDate;
import java.util.UUID;

public record PatientResponseDTO(
        UUID uuid,
        String fullName,
        Gender gender,
        LocalDate dateOfBirth,
        String medicalRecordNumber,
        String nationalId,
        String address,
        String phone,
        String clinicalHistory) {}
