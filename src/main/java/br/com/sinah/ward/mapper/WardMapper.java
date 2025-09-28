package br.com.sinah.ward.mapper;

import br.com.sinah.ward.dto.WardRequestDTO;
import br.com.sinah.ward.dto.WardResponseDTO;
import br.com.sinah.ward.model.WardModel;

import java.time.LocalDateTime;

public class WardMapper {
    public static WardModel toModel(WardRequestDTO requestDTO) {
        return new WardModel(
                null, null, requestDTO.name(), requestDTO.description(), LocalDateTime.now(), LocalDateTime.now());
    }

    public static WardResponseDTO toDTO(WardModel model) {
        return new WardResponseDTO(
                model.getUuid(), model.getName(), model.getDescription(), LocalDateTime.now(), LocalDateTime.now());
    }

    public static WardModel toUpdate(WardModel newWard, WardRequestDTO dto) {
        newWard.setName(dto.name());
        newWard.setDescription(dto.description());
        newWard.setUpdatedAt(LocalDateTime.now());
        return newWard;
    }
}
