package br.com.sinah.room.service;

import br.com.sinah.common.exception.NotFoundException;
import br.com.sinah.room.dto.RoomRequestDTO;
import br.com.sinah.room.dto.RoomResponseDTO;
import br.com.sinah.room.mapper.RoomMapper;
import br.com.sinah.room.repository.RoomRepository;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class RoomService {

    private final RoomRepository roomRepository;

    public RoomService(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    public List<RoomResponseDTO> findAll() {
        return roomRepository.findAll().stream().map(RoomMapper::toDTO).toList();
    }

    public RoomResponseDTO findByUuid(UUID uuid) {
        var find = roomRepository.findById(uuid).orElseThrow(() -> new NotFoundException("Ward not found"));
        return RoomMapper.toDTO(find);
    }

    public RoomResponseDTO create(RoomRequestDTO ward) {
        var save = roomRepository.save(RoomMapper.toModel(ward));
        return RoomMapper.toDTO(save);
    }

    public void deleteByUuid(UUID uuid) {
        var find = roomRepository.findById(uuid).orElseThrow(() -> new NotFoundException("Ward not found"));
        roomRepository.deleteById(uuid);
    }

    public RoomResponseDTO update(UUID uuid, RoomRequestDTO dto) {
        var find = roomRepository.findById(uuid).orElseThrow(() -> new NotFoundException("Ward not found"));
        var updated = RoomMapper.toUpdate(find, dto);
        return RoomMapper.toDTO(this.roomRepository.save(updated));
    }
}
