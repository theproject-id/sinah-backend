package br.com.sinah.bed.service;

import br.com.sinah.bed.dto.BedRequestDTO;
import br.com.sinah.bed.dto.BedResponseDTO;
import br.com.sinah.bed.mapper.BedMapper;
import br.com.sinah.bed.repository.BedRepository;
import br.com.sinah.common.exception.NotFoundException;

import br.com.sinah.room.repository.RoomRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class BedService {

    private final BedRepository bedRepository;
    private final RoomRepository roomRepository;


    public BedService(BedRepository bedRepository, RoomRepository roomRepository) {
        this.bedRepository = bedRepository;
        this.roomRepository = roomRepository;
    }

    public List<BedResponseDTO> findAll() {
        return bedRepository.findAll().stream().map(BedMapper::toDTO).toList();
    }

    public BedResponseDTO findByUuid(UUID uuid) {
        var find = bedRepository.findById(uuid).orElseThrow(() -> new NotFoundException("Bed not found"));
        return BedMapper.toDTO(find);
    }

    public BedResponseDTO create(BedRequestDTO bed) {
        var room = this.roomRepository.findById(bed.roomUuid()).orElseThrow(() -> new NotFoundException("Room not found"));
        var save = bedRepository.save(BedMapper.toModel(bed,room));
        return BedMapper.toDTO(save);
    }

    public void deleteByUuid(UUID uuid) {
        var find = bedRepository.findById(uuid).orElseThrow(() -> new NotFoundException("Bed not found"));
        bedRepository.deleteById(uuid);
    }

    public BedResponseDTO update(UUID uuid, BedRequestDTO dto) {
        var find = bedRepository.findById(uuid).orElseThrow(() -> new NotFoundException("Bed not found"));
        if (dto.roomUuid()!= find.getRoom().getUuid()){
            find.setRoom(this.roomRepository.findById(dto.roomUuid()).orElseThrow(() -> new NotFoundException("Room not found")));
        }
        var updated = BedMapper.toUpdate(find, dto);
        return BedMapper.toDTO(this.bedRepository.save(updated));
    }
}
