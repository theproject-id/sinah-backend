package br.com.sinah.ward.dto;

import br.com.sinah.room.dto.RoomResponseDTO;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public record WardResponseDTO(
        UUID uuid,
        String name,
        String description,
        List<RoomResponseDTO> rooms,
        LocalDateTime createdAt,
        LocalDateTime updatedAt) {
}
