package br.com.sinah.notification.dto;

import br.com.sinah.notification.enums.NotificationType;
import br.com.sinah.notification.enums.PriorityLevel;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;

public record NotificationResponseDTO(
        UUID uuid,
        String title,
        String description,
        PriorityLevel priority,
        NotificationType notificationType,
        LocalDateTime notificationDate,
        UUID patientId,
        UUID departmentId,
        UUID wardId,
        UUID roomId,
        UUID userId,
        Map<String, Object> additionalData,
        LocalDateTime createdAt,
        LocalDateTime dueDate,
        LocalDateTime updatedAt) {}
