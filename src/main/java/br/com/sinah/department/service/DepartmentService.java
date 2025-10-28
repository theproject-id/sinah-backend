package br.com.sinah.department.service;

import br.com.sinah.common.exception.NotFoundException;
import br.com.sinah.department.dto.DepartmentRequestDTO;
import br.com.sinah.department.dto.DepartmentResponseDTO;
import br.com.sinah.department.mapper.DepartmentMapper;
import br.com.sinah.department.repository.DepartmentRepository;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class DepartmentService {

    private final DepartmentRepository departmentRepository;

    public DepartmentService(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    public List<DepartmentResponseDTO> findAll() {
        return departmentRepository.findAll().stream().map(DepartmentMapper::toDTO).toList();
    }

    public DepartmentResponseDTO findByUuid(UUID uuid) {
        var find = departmentRepository.findById(uuid).orElseThrow(() -> new NotFoundException("Ward not found"));
        return DepartmentMapper.toDTO(find);
    }

    public DepartmentResponseDTO create(DepartmentRequestDTO ward) {
        var save = departmentRepository.save(DepartmentMapper.toModel(ward));
        return DepartmentMapper.toDTO(save);
    }

    public void deleteByUuid(UUID uuid) {
        var find = departmentRepository.findById(uuid).orElseThrow(() -> new NotFoundException("Ward not found"));
        departmentRepository.deleteById(uuid);
    }

    public DepartmentResponseDTO update(UUID uuid, DepartmentRequestDTO dto) {
        var find = departmentRepository.findById(uuid).orElseThrow(() -> new NotFoundException("Ward not found"));
        var updated = DepartmentMapper.toUpdate(find, dto);
        return DepartmentMapper.toDTO(this.departmentRepository.save(updated));
    }
}
