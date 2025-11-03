package br.com.sinah.department.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public record DepartmentResponseDTO(
        UUID uuid, String name, String description, LocalDateTime createdAt, LocalDateTime updatedAt) {
}
