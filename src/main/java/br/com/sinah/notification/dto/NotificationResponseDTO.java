package br.com.sinah.notification.dto;

import br.com.sinah.notification.enums.InfectionOrigin;
import br.com.sinah.notification.enums.NotificationStatus;
import br.com.sinah.notification.enums.NotificationType;

import java.time.LocalDateTime;
import java.util.UUID;

public record NotificationResponseDTO(
        UUID uuid,
        NotificationType notificationType,
        LocalDateTime notificationDate,
        NotificationStatus status,
        UUID wardId,
        UUID patientId,
        String description,
        String microorganism,
        String resistance,
        InfectionOrigin infectionOrigin,
        String localInfection,
        String responsibleUser,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
