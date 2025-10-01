package br.com.sinah.notification.service;

import br.com.sinah.common.exception.NotFoundException;
import br.com.sinah.notification.dto.NotificationRequestDTO;
import br.com.sinah.notification.dto.NotificationResponseDTO;
import br.com.sinah.notification.mapper.NotificationMapper;
import br.com.sinah.notification.repository.NotificationRepository;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class NotificationService {

    private final NotificationRepository notificationRepository;

    public NotificationService(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    public NotificationResponseDTO create(NotificationRequestDTO dto) {
        var model = NotificationMapper.toModel(dto);
        var saved = notificationRepository.save(model);
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
        var model =
                notificationRepository.findById(id).orElseThrow(() -> new NotFoundException("Notification not found"));
        var requestUpdated = NotificationMapper.toUpdate(model, dto);
        var updated = notificationRepository.save(requestUpdated);
        return NotificationMapper.toDTO(updated);
    }

    public void delete(UUID id) {
        if (!notificationRepository.existsById(id)) {
            throw new NotFoundException("Notification not found");
        }
        notificationRepository.deleteById(id);
    }
}
