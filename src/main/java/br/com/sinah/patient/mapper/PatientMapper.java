package br.com.sinah.patient.mapper;

import br.com.sinah.bed.model.BedModel;
import br.com.sinah.department.model.DepartmentModel;
import br.com.sinah.patient.dto.PatientRequestDTO;
import br.com.sinah.patient.dto.PatientResponseDTO;
import br.com.sinah.patient.model.PatientModel;
import br.com.sinah.room.model.RoomModel;
import br.com.sinah.ward.model.WardModel;

import java.time.LocalDateTime;

public class PatientMapper {
    public static PatientResponseDTO toDTO(PatientModel model) {
        return new PatientResponseDTO(
                model.getUuid(),
                model.getFullName(),
                model.getGender(),
                model.getDateOfBirth(),
                model.getCpf(),
                model.getAddress(),
                model.getPhone(),
                model.getBed().getUuid(),
                model.getCreatedAt(),
                model.getUpdatedAt());
    }

    public static PatientModel toModel(PatientRequestDTO dto) {
        PatientModel model = new PatientModel();
        model.setFullName(dto.fullName());
        model.setGender(dto.gender());
        model.setDateOfBirth(dto.dateOfBirth());
        model.setCpf(dto.cpf());
        model.setAddress(dto.address());
        model.setPhone(dto.phone());

        if (dto.bedUuid() != null) model.setBed(new BedModel(dto.bedUuid()));
        if (dto.roomUuid() != null) model.setRoom(new RoomModel(dto.roomUuid()));

        model.setCreatedAt(LocalDateTime.now());
        model.setUpdatedAt(LocalDateTime.now());
        return model;
    }

    public static PatientModel toUpdate(PatientModel model, PatientRequestDTO dto) {
        model.setFullName(dto.fullName());
        model.setGender(dto.gender());
        model.setDateOfBirth(dto.dateOfBirth());
        model.setCpf(dto.cpf());
        model.setAddress(dto.address());
        model.setPhone(dto.phone());

        if (dto.departmentUuid() != null) model.setDepartment(new DepartmentModel(dto.departmentUuid()));
        else model.setDepartment(null);
        if (dto.wardUuid() != null) model.setWard(new WardModel(dto.wardUuid()));
        else model.setWard(null);
        if (dto.roomUuid() != null) model.setRoom(new RoomModel(dto.roomUuid()));
        else model.setRoom(null);

        model.setUpdatedAt(LocalDateTime.now());
        return model;
    }
}
