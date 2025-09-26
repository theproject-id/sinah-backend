package br.com.sinah.notification.mapper;

import br.com.sinah.notification.dto.NotificationRequestDTO;
import br.com.sinah.notification.dto.NotificationResponseDTO;
import br.com.sinah.notification.model.NotificationModel;

import java.time.LocalDateTime;

public class NotificationMapper {
    public static NotificationResponseDTO toDTO(NotificationModel model) {
        return new NotificationResponseDTO(
                model.getId(),
                model.getNotificationType(),
                model.getNotificationDate(),
                model.getStatus(),
                model.getAla().getId(),
                model.getPatient().getId(),
                model.getDescription(),
                model.getMicroorganism(),
                model.getResistance(),
                model.getInfectionOrigin(),
                model.getLocalInfection(),
                model.getResponsibleUser(),
                model.getCreatedAt(),
                model.getUpdatedAt()
        );
    }

    public static NotificationModel toModel(NotificationRequestDTO request) {
        return new NotificationModel(
                request.notificationType(),
                request.notificationDate(),
                request.status(),
                request.wardId(),
                request.patientId(),
                request.description(),
                request.microorganism(),
                request.resistance(),
                request.infectionOrigin(),
                request.localInfection(),
                request.responsibleUser(),
                LocalDateTime.now(),
                LocalDateTime.now()
        );
    }
}
