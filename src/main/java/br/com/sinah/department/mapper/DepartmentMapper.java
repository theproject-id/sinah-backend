package br.com.sinah.department.mapper;

import br.com.sinah.bed.dto.BedResponseDTO;
import br.com.sinah.bed.mapper.BedMapper;
import br.com.sinah.department.dto.DepartmentRequestDTO;
import br.com.sinah.department.dto.DepartmentResponseDTO;
import br.com.sinah.department.model.DepartmentModel;
import br.com.sinah.user.dto.UserDTO;
import br.com.sinah.user.mapper.UserMapper;
import br.com.sinah.user.model.UserModel;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class DepartmentMapper {
    public static DepartmentModel toModel(DepartmentRequestDTO requestDTO) {
        DepartmentModel model = new DepartmentModel();
        model.setUuid(null);
        model.setName(requestDTO.name());
        model.setDescription(requestDTO.description());
        model.setCreatedAt(LocalDateTime.now());
        model.setUpdatedAt(LocalDateTime.now());
        return model;
    }

    public static DepartmentResponseDTO toDTO(DepartmentModel model) {
        List<UserDTO> healthcareProfessionals = Collections.emptyList();
        if (model.getHealthcareProfessionals() != null) {
            healthcareProfessionals = model.getHealthcareProfessionals().stream()
                    .filter(Objects::nonNull)
                    .map(UserMapper::toDTO)
                    .toList();
        }
        return new DepartmentResponseDTO(
                model.getUuid(), model.getName(), model.getDescription(),healthcareProfessionals,
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
