package br.com.sinah.patient.dto;

import br.com.sinah.patient.enums.Gender;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

public record PatientResponseDTO(
        UUID uuid,
        String fullName,
        Gender gender,
        LocalDate dateOfBirth,
        String cpf,
        String address,
        String phone,
        UUID bedUuid,
        LocalDateTime createdAt,
        LocalDateTime updatedAt) {
}
