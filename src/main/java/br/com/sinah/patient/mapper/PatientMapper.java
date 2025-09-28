package br.com.sinah.patient.mapper;

import br.com.sinah.patient.dto.PatientRequestDTO;
import br.com.sinah.patient.dto.PatientResponseDTO;
import br.com.sinah.patient.model.PatientModel;

import java.time.LocalDateTime;

public class PatientMapper {
    public static PatientResponseDTO toDTO(PatientModel model) {
        return new PatientResponseDTO(
                model.getUuid(),
                model.getFullName(),
                model.getGender(),
                model.getDateOfBirth(),
                model.getMedicalRecordNumber(),
                model.getNationalId(),
                model.getAddress(),
                model.getPhone(),
                model.getClinicalHistory());
    }

    public static PatientModel toModel(PatientRequestDTO dto) {
        return new PatientModel(
                null,
                null,
                dto.fullName(),
                dto.gender(),
                dto.dateOfBirth(),
                dto.medicalRecordNumber(),
                dto.nationalId(),
                dto.address(),
                dto.phone(),
                dto.clinicalHistory(),
                LocalDateTime.now(),
                LocalDateTime.now());
    }

    public static PatientModel toUpdate(PatientModel model, PatientRequestDTO dto) {
        model.setFullName(dto.fullName());
        model.setAddress(dto.address());
        model.setGender(dto.gender());
        model.setClinicalHistory(dto.clinicalHistory());
        model.setMedicalRecordNumber(dto.medicalRecordNumber());
        model.setPhone(dto.phone());
        model.setNationalId(dto.nationalId());
        model.setDateOfBirth(dto.dateOfBirth());
        model.setUpdatedAt(LocalDateTime.now());
        return model;
    }
}
