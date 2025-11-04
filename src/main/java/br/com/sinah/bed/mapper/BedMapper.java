package br.com.sinah.bed.mapper;

import br.com.sinah.bed.dto.BedRequestDTO;
import br.com.sinah.bed.dto.BedResponseDTO;
import br.com.sinah.bed.model.BedModel;
import br.com.sinah.room.model.RoomModel;

import java.time.LocalDateTime;

public class BedMapper {
    public static BedModel toModel(BedRequestDTO requestDTO,RoomModel room) {
        return new BedModel(
                null,
                null,
                requestDTO.number(),
                room,
                LocalDateTime.now(),
                LocalDateTime.now());
    }

    public static BedResponseDTO toDTO(BedModel model) {
        return new BedResponseDTO(
                model.getUuid(), model.getNumber(), model.getRoom() != null ? model.getRoom().getUuid() : null, model.getCreatedAt(), model.getUpdatedAt());
    }

    public static BedModel toUpdate(BedModel existing, BedRequestDTO dto) {
        existing.setNumber(dto.number());
        existing.setUpdatedAt(LocalDateTime.now());
        return existing;
    }
}
