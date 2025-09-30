package br.com.sinah.notification.mapper;

import br.com.sinah.notification.dto.NotificationRequestDTO;
import br.com.sinah.notification.dto.NotificationResponseDTO;
import br.com.sinah.notification.dto.PatientDisplayDTO;
import br.com.sinah.notification.dto.UserDisplayDTO;
import br.com.sinah.notification.dto.WardDisplayDTO;
import br.com.sinah.notification.model.NotificationModel;
import br.com.sinah.patient.model.PatientModel;
import br.com.sinah.user.model.UserModel;
import br.com.sinah.ward.model.WardModel;

import java.time.LocalDateTime;

public class NotificationMapper {
    public static NotificationResponseDTO toDTO(NotificationModel model) {
        PatientDisplayDTO patientDisplay = null;
        if (model.getPatient() != null) {
            patientDisplay = new PatientDisplayDTO(
                    model.getPatient().getUuid() != null
                            ? model.getPatient().getUuid().toString()
                            : null,
                    model.getPatient().getFullName());
        }
        UserDisplayDTO userDisplay = null;
        if (model.getUser() != null) {
            userDisplay = new UserDisplayDTO(
                    model.getUser().getUuid() != null
                            ? model.getUser().getUuid().toString()
                            : null,
                    model.getUser().getUsername(),
                    model.getUser().getFirstName() + " " + model.getUser().getLastName());
        }
        WardDisplayDTO wardDisplay = null;
        if (model.getAla() != null) {
            wardDisplay = new WardDisplayDTO(
                    model.getAla().getUuid() != null ? model.getAla().getUuid().toString() : null,
                    model.getAla().getName());
        }
        return new NotificationResponseDTO(
                model.getUuid(),
                model.getNotificationType(),
                model.getNotificationDate(),
                model.getStatus(),
                wardDisplay,
                model.getDescription(),
                model.getMicroorganism(),
                model.getResistance(),
                model.getInfectionOrigin(),
                model.getLocalInfection(),
                model.getCreatedAt(),
                model.getUpdatedAt(),
                patientDisplay,
                userDisplay);
    }

    public static NotificationModel toModel(
            NotificationRequestDTO request, WardModel ward, PatientModel patient, UserModel user) {
        return new NotificationModel(
                null,
                null,
                request.notificationType(),
                request.notificationDate(),
                request.status(),
                ward,
                patient,
                user,
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
            NotificationModel model, WardModel ward, PatientModel patient, UserModel user, NotificationRequestDTO dto) {
        model.setNotificationType(dto.notificationType());
        model.setNotificationDate(dto.notificationDate());
        model.setStatus(dto.status());
        model.setAla(ward);
        model.setPatient(patient);
        model.setUser(user);
        model.setDescription(dto.description());
        model.setMicroorganism(dto.microorganism());
        model.setResistance(dto.resistance());
        model.setInfectionOrigin(dto.infectionOrigin());
        model.setLocalInfection(dto.localInfection());
        model.setResponsibleUser(dto.responsibleUser());
        return model;
    }
}
