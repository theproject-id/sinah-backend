package br.com.sinah.department.dto;

import java.util.UUID;

public record DepartmentRequestDTO(
        String name,
        String description) {
}
