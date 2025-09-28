package br.com.sinah.notification.service;

import br.com.sinah.common.exception.NotFoundException;
import br.com.sinah.notification.dto.NotificationRequestDTO;
import br.com.sinah.notification.dto.NotificationResponseDTO;
import br.com.sinah.notification.mapper.NotificationMapper;
import br.com.sinah.notification.model.NotificationModel;
import br.com.sinah.notification.repository.NotificationRepository;
import br.com.sinah.patient.repository.PatientRepository;
import br.com.sinah.ward.repository.WardRepository;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class NotificationService {

    private final NotificationRepository notificationRepository;
    private final WardRepository wardRepository;
    private final PatientRepository patientRepository;

    public NotificationService(
            NotificationRepository notificationRepository,
            WardRepository wardRepository,
            PatientRepository patientRepository) {
        this.notificationRepository = notificationRepository;
        this.wardRepository = wardRepository;
        this.patientRepository = patientRepository;
    }

    public NotificationResponseDTO create(NotificationRequestDTO dto) {
        var ward = wardRepository.findById(dto.wardId()).orElseThrow(() -> new NotFoundException("Ward not found"));
        var patient = patientRepository
                .findById(dto.patientId())
                .orElseThrow(() -> new NotFoundException("Patient not found"));
        NotificationModel model = NotificationMapper.toModel(dto, ward, patient);
        NotificationModel saved = notificationRepository.save(model);
        return NotificationMapper.toDTO(saved);
    }

    public List<NotificationResponseDTO> findAll() {
        return notificationRepository.findAll().stream()
                .map(NotificationMapper::toDTO)
                .collect(Collectors.toList());
    }

    public NotificationResponseDTO findById(UUID id) {
        return notificationRepository
                .findById(id)
                .map(NotificationMapper::toDTO)
                .orElseThrow(() -> new NotFoundException("Notification not found"));
    }

    public NotificationResponseDTO update(UUID id, NotificationRequestDTO dto) {
        NotificationModel model =
                notificationRepository.findById(id).orElseThrow(() -> new NotFoundException("Notification not found"));
        var ward = wardRepository.findById(dto.wardId()).orElseThrow(() -> new NotFoundException("Ward not found"));
        var patient = patientRepository
                .findById(dto.patientId())
                .orElseThrow(() -> new NotFoundException("Patient not found"));
        var requestUpdated = NotificationMapper.toUpdate(model, ward, patient, dto);
        NotificationModel updated = notificationRepository.save(requestUpdated);
        return NotificationMapper.toDTO(updated);
    }

    public void delete(UUID id) {
        if (!notificationRepository.existsById(id)) {
            throw new NotFoundException("Notification not found");
        }
        notificationRepository.deleteById(id);
    }
}
