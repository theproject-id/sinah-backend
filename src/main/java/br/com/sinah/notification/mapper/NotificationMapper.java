package br.com.sinah.notification.mapper;

import br.com.sinah.notification.dto.NotificationRequestDTO;
import br.com.sinah.notification.dto.NotificationResponseDTO;
import br.com.sinah.notification.model.NotificationModel;
import br.com.sinah.patient.model.PatientModel;
import br.com.sinah.user.model.UserModel;

import java.time.LocalDateTime;
import java.util.UUID;

public class NotificationMapper {
    public static NotificationResponseDTO toDTO(NotificationModel model) {
        UUID patientId = model.getPatient() != null ? model.getPatient().getUuid() : null;
        UUID userId = model.getUser() != null ? model.getUser().getUuid() : null;

        return new NotificationResponseDTO(
                model.getUuid(),
                model.getTitle(),
                model.getDescription(),
                model.getPriority(),
                model.getNotificationType(),
                model.getNotificationDate(),
                patientId,
                userId,
                model.getAdditionalData(),
                model.getCreatedAt(),
                model.getDueDate(),
                model.getUpdatedAt());
    }

    public static NotificationModel toModel(
            NotificationRequestDTO request,
            PatientModel patient,
            UserModel user) {
        NotificationModel model = new NotificationModel();
        model.setTitle(request.title());
        model.setDescription(request.description());
        model.setPriority(request.priority());
        model.setPatient(patient);
        model.setNotificationType(request.notificationType());
        model.setNotificationDate(request.notificationDate());
        model.setUser(user);
        model.setAdditionalData(request.additionalData());
        model.setCreatedAt(LocalDateTime.now());
        model.setDueDate(request.dueDate() != null ? request.dueDate() : LocalDateTime.now());
        model.setUpdatedAt(LocalDateTime.now());
        return model;
    }

    public static NotificationModel toUpdate(
            NotificationModel model,
            PatientModel patient,
            UserModel user,
            NotificationRequestDTO request) {
        model.setTitle(request.title());
        model.setDescription(request.description());
        model.setPriority(request.priority());
        model.setPatient(patient);
        model.setNotificationType(request.notificationType());
        model.setNotificationDate(request.notificationDate());
        model.setUser(user);
        model.setAdditionalData(request.additionalData());
        model.setDueDate(request.dueDate() != null ? request.dueDate() : model.getDueDate());
        model.setUpdatedAt(LocalDateTime.now());
        return model;
    }
}
