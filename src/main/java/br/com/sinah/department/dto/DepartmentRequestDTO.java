package br.com.sinah.department.dto;

import java.util.UUID;

public record DepartmentRequestDTO(UUID uuid, String name, String description) {}
