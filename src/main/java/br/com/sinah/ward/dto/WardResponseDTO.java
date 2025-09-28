package br.com.sinah.ward.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public record WardResponseDTO(
        UUID uuid, String name, String description, LocalDateTime createdAt, LocalDateTime updatedAt) {}
