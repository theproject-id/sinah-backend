package br.com.sinah.ward.mapper;

import br.com.sinah.bed.dto.BedResponseDTO;
import br.com.sinah.bed.mapper.BedMapper;
import br.com.sinah.room.dto.RoomResponseDTO;
import br.com.sinah.room.mapper.RoomMapper;
import br.com.sinah.ward.dto.WardRequestDTO;
import br.com.sinah.ward.dto.WardResponseDTO;
import br.com.sinah.ward.model.WardModel;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

public class WardMapper {
    public static WardModel toModel(WardRequestDTO requestDTO) {
        return new WardModel(null, null, requestDTO.name(), requestDTO.description(), List.of(), LocalDateTime.now(), LocalDateTime.now());
    }

    public static WardResponseDTO toDTO(WardModel model) {
        List<RoomResponseDTO> rooms = Collections.emptyList();
        if (model.getRooms() != null) {
            rooms = model.getRooms().stream()
                    .filter(Objects::nonNull)
                    .map(RoomMapper::toDTO).toList();
        }
        return new WardResponseDTO(
                model.getUuid(), model.getName(), model.getDescription(), rooms, LocalDateTime.now(), LocalDateTime.now());
    }

    public static WardModel toUpdate(WardModel newWard, WardRequestDTO dto) {
        newWard.setName(dto.name());
        newWard.setDescription(dto.description());
        newWard.setUpdatedAt(LocalDateTime.now());
        return newWard;
    }
}
