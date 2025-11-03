package br.com.sinah.room.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public record RoomResponseDTO(
        UUID uuid, String name, String description, LocalDateTime createdAt, LocalDateTime updatedAt) {
}
