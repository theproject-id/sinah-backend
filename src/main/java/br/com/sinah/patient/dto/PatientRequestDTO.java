package br.com.sinah.patient.dto;

import br.com.sinah.patient.enums.Gender;

import java.time.LocalDate;
import java.util.UUID;

public record PatientRequestDTO(
        String fullName,
        Gender gender,
        LocalDate dateOfBirth,
        String cpf,
        String address,
        String phone,
        UUID departmentUuid,
        UUID wardUuid,
        UUID roomUuid) {}
