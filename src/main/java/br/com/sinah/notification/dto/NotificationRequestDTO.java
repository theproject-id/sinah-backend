package br.com.sinah.notification.dto;

import br.com.sinah.notification.enums.NotificationType;
import br.com.sinah.notification.enums.PriorityLevel;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;

public record NotificationRequestDTO(
        String title,
        String description,
        PriorityLevel priority,
        UUID patientId,
        UUID userUuid,
        NotificationType notificationType,
        LocalDateTime notificationDate,
        Map<String, Object> additionalData,
        LocalDateTime dueDate) {
}
