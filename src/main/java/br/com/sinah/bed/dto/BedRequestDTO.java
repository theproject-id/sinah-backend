package br.com.sinah.bed.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public record BedRequestDTO(UUID uuid, int number, LocalDateTime createdAt, LocalDateTime updatedAt) {
}

