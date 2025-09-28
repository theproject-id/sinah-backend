package br.com.sinah.patient.service;

import br.com.sinah.common.exception.NotFoundException;
import br.com.sinah.patient.dto.PatientRequestDTO;
import br.com.sinah.patient.dto.PatientResponseDTO;
import br.com.sinah.patient.mapper.PatientMapper;
import br.com.sinah.patient.model.PatientModel;
import br.com.sinah.patient.repository.PatientRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@Service
public class PatientService {

    private final PatientRepository patientRepository;

    public PatientResponseDTO create(PatientRequestDTO dto){
        PatientModel request = PatientMapper.toModel(dto);
        return  PatientMapper.toDTO(this.patientRepository.save(request));
    }
    public List<PatientResponseDTO> findAll(){
       return this.patientRepository.findAll().stream().map(PatientMapper::toDTO).toList();
    }
    public PatientResponseDTO findById(UUID uuid){
        PatientModel find = this.patientRepository.findById(uuid).orElseThrow(()-> new NotFoundException("Patient not found"));
        return PatientMapper.toDTO(find);
    }
    public PatientResponseDTO update(UUID uuid,PatientRequestDTO dto){
        PatientModel find = this.patientRepository.findById(uuid).orElseThrow(()-> new NotFoundException("Patient not found"));
        PatientModel response = this.patientRepository.save(PatientMapper.toUpdate(find,dto));
        return PatientMapper.toDTO(response);

    }
    public void delete(UUID uuid){
        PatientModel find = this.patientRepository.findById(uuid).orElseThrow(()-> new NotFoundException("Patient not found"));
        this.patientRepository.delete(find);
    }
}
