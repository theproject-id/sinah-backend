package br.com.sinah.notification.dto;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;

public record NotificationResponseDTO(
        UUID uuid,
        String title,
        String description,
        String type,
        String priority,
        UUID patientId,
        String patientName,
        String patientRoom,
        String patientBed,
        UUID createdBy,
        String createdByName,
        LocalDateTime createdAt,
        LocalDateTime updatedAt,
        LocalDateTime dueDate,
        String notes,
        Map<String, Object> additionalData) {}
