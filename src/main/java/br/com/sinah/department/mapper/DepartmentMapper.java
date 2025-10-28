package br.com.sinah.department.mapper;

import br.com.sinah.department.dto.DepartmentRequestDTO;
import br.com.sinah.department.dto.DepartmentResponseDTO;
import br.com.sinah.department.model.DepartmentModel;

import java.time.LocalDateTime;

public class DepartmentMapper {
    public static DepartmentModel toModel(DepartmentRequestDTO requestDTO) {
        DepartmentModel model = new DepartmentModel();
        model.setUuid(requestDTO.uuid());
        model.setName(requestDTO.name());
        model.setDescription(requestDTO.description());
        model.setCreatedAt(LocalDateTime.now());
        model.setUpdatedAt(LocalDateTime.now());
        return model;
    }

    public static DepartmentResponseDTO toDTO(DepartmentModel model) {
        return new DepartmentResponseDTO(
                model.getUuid(), model.getName(), model.getDescription(),
                model.getCreatedAt() != null ? model.getCreatedAt() : LocalDateTime.now(),
                model.getUpdatedAt() != null ? model.getUpdatedAt() : LocalDateTime.now());
    }

    public static DepartmentModel toUpdate(DepartmentModel newDepartment, DepartmentRequestDTO dto) {
        newDepartment.setName(dto.name());
        newDepartment.setDescription(dto.description());
        newDepartment.setUpdatedAt(LocalDateTime.now());
        return newDepartment;
    }
}
