package br.com.sinah.ward.service;

import br.com.sinah.common.exception.NotFoundException;
import br.com.sinah.ward.dto.WardRequestDTO;
import br.com.sinah.ward.dto.WardResponseDTO;
import br.com.sinah.ward.mapper.WardMapper;
import br.com.sinah.ward.repository.WardRepository;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class WardService {

    private final WardRepository wardRepository;

    public WardService(WardRepository wardRepository) {
        this.wardRepository = wardRepository;
    }

    public List<WardResponseDTO> findAll() {
        return wardRepository.findAll().stream().map(WardMapper::toDTO).toList();
    }

    public WardResponseDTO findByUuid(UUID uuid) {
        var find = wardRepository.findById(uuid).orElseThrow(() -> new NotFoundException("Ward not found"));
        return WardMapper.toDTO(find);
    }

    public WardResponseDTO create(WardRequestDTO ward) {
        var save = wardRepository.save(WardMapper.toModel(ward));
        return WardMapper.toDTO(save);
    }

    public void deleteByUuid(UUID uuid) {
        var find = wardRepository.findById(uuid).orElseThrow(() -> new NotFoundException("Ward not found"));
        wardRepository.deleteById(uuid);
    }

    public WardResponseDTO update(UUID uuid, WardRequestDTO dto) {
        var find = wardRepository.findById(uuid).orElseThrow(() -> new NotFoundException("Ward not found"));
        var updated = WardMapper.toUpdate(find, dto);
        return WardMapper.toDTO(this.wardRepository.save(updated));
    }
}
