package br.com.sinah.bed.mapper;

import br.com.sinah.bed.dto.BedRequestDTO;
import br.com.sinah.bed.dto.BedResponseDTO;
import br.com.sinah.bed.model.BedModel;

import java.time.LocalDateTime;

public class BedMapper {
    public static BedModel toModel(BedRequestDTO requestDTO) {
        return new BedModel();
    }

    public static BedResponseDTO toDTO(BedModel model) {
        return new BedResponseDTO(
                model.getUuid(),model.getNumber(), model.getCreatedAt(), LocalDateTime.now());
    }

    public static BedModel toUpdate(BedModel newBed, BedRequestDTO dto) {
        newBed.setUuid(dto.uuid());
        newBed.setNumber(dto.number());
        newBed.setUpdatedAt(LocalDateTime.now());
        return newBed;
    }
}
