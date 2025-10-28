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
        NotificationType notificationType,
        UUID departmentId,
        UUID wardId,
        UUID roomId,
        LocalDateTime notificationDate,
        UUID userUuid,
        Map<String, Object> additionalData,
        LocalDateTime dueDate) {}
