package br.com.sinah.room.mapper;

import br.com.sinah.room.dto.RoomRequestDTO;
import br.com.sinah.room.dto.RoomResponseDTO;
import br.com.sinah.room.model.RoomModel;

import java.time.LocalDateTime;

public class RoomMapper {
    public static RoomModel toModel(RoomRequestDTO requestDTO) {
        RoomModel model = new RoomModel();
        model.setName(requestDTO.name());
        model.setDescription(requestDTO.description());
        model.setCreatedAt(LocalDateTime.now());
        model.setUpdatedAt(LocalDateTime.now());
        return model;
    }

    public static RoomResponseDTO toDTO(RoomModel model) {
        return new RoomResponseDTO(
                model.getUuid(),
                model.getName(),
                model.getDescription(),
                model.getCreatedAt() != null ? model.getCreatedAt() : LocalDateTime.now(),
                model.getUpdatedAt() != null ? model.getUpdatedAt() : LocalDateTime.now());
    }

    public static RoomModel toUpdate(RoomModel newRoom, RoomRequestDTO dto) {
        newRoom.setName(dto.name());
        newRoom.setDescription(dto.description());
        newRoom.setUpdatedAt(LocalDateTime.now());
        return newRoom;
    }
}
