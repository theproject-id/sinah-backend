package br.com.sinah.room.dto;

import br.com.sinah.bed.dto.BedResponseDTO;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public record RoomResponseDTO(
        UUID uuid,
        String name,
        String description,
        List<BedResponseDTO> beds,
        UUID wardUuid,
        LocalDateTime createdAt,
        LocalDateTime updatedAt) {
}
