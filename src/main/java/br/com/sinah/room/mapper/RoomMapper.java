package br.com.sinah.room.mapper;

import br.com.sinah.room.dto.RoomRequestDTO;
import br.com.sinah.room.dto.RoomResponseDTO;
import br.com.sinah.room.model.RoomModel;
import br.com.sinah.ward.model.WardModel;
import br.com.sinah.bed.mapper.BedMapper;
import br.com.sinah.bed.model.BedModel;
import br.com.sinah.bed.dto.BedResponseDTO;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class RoomMapper {
    public static RoomModel toModel(RoomRequestDTO requestDTO,WardModel wardModel) {
        RoomModel model = new RoomModel();
        model.setName(requestDTO.name());
        model.setWard(wardModel);
        model.setDescription(requestDTO.description());
        model.setCreatedAt(LocalDateTime.now());
        model.setUpdatedAt(LocalDateTime.now());
        return model;
    }

    public static RoomResponseDTO toDTO(RoomModel model) {
        List<BedResponseDTO> beds = Collections.emptyList();
        if (model.getBeds() != null) {
            beds = model.getBeds().stream()
                    .filter(Objects::nonNull)
                    .map(BedMapper::toDTO)
                    .collect(Collectors.toList());
        }

        return new RoomResponseDTO(
                model.getUuid(),
                model.getName(),
                model.getDescription(),
                beds,
                model.getWard() != null ? model.getWard().getUuid() : null,
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
