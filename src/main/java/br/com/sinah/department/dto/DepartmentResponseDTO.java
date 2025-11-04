package br.com.sinah.department.dto;

import br.com.sinah.user.dto.UserDTO;
import br.com.sinah.user.model.UserModel;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public record DepartmentResponseDTO(
        UUID uuid,
        String name,
        String description,
        List<UserDTO> healthcareProfessionals,
        LocalDateTime createdAt,
        LocalDateTime updatedAt) {
}
