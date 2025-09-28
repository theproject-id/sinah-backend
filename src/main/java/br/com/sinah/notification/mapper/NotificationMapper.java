package br.com.sinah.notification.mapper;

import br.com.sinah.notification.dto.NotificationRequestDTO;
import br.com.sinah.notification.dto.NotificationResponseDTO;
import br.com.sinah.notification.model.NotificationModel;
import br.com.sinah.patient.model.PatientModel;
import br.com.sinah.ward.model.WardModel;

import java.time.LocalDateTime;

public class NotificationMapper {
    public static NotificationResponseDTO toDTO(NotificationModel model) {
        return new NotificationResponseDTO(
                model.getUuid(),
                model.getNotificationType(),
                model.getNotificationDate(),
                model.getStatus(),
                model.getAla().getUuid(),
                model.getPatient().getUuid(),
                model.getDescription(),
                model.getMicroorganism(),
                model.getResistance(),
                model.getInfectionOrigin(),
                model.getLocalInfection(),
                model.getResponsibleUser(),
                model.getCreatedAt(),
                model.getUpdatedAt());
    }

    public static NotificationModel toModel(NotificationRequestDTO request, WardModel ward, PatientModel patient) {
        return new NotificationModel(
                null,
                null,
                request.notificationType(),
                request.notificationDate(),
                request.status(),
                ward,
                patient,
                request.description(),
                request.microorganism(),
                request.resistance(),
                request.infectionOrigin(),
                request.localInfection(),
                request.responsibleUser(),
                LocalDateTime.now(),
                LocalDateTime.now());
    }

    public static NotificationModel toUpdate(
            NotificationModel model, WardModel ward, PatientModel patient, NotificationRequestDTO dto) {
        model.setNotificationType(dto.notificationType());
        model.setNotificationDate(dto.notificationDate());
        model.setStatus(dto.status());
        model.setAla(ward);
        model.setPatient(patient);
        model.setDescription(dto.description());
        model.setMicroorganism(dto.microorganism());
        model.setResistance(dto.resistance());
        model.setInfectionOrigin(dto.infectionOrigin());
        model.setLocalInfection(dto.localInfection());
        model.setResponsibleUser(dto.responsibleUser());
        return model;
    }
}
