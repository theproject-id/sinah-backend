package br.com.sinah.notification.mapper;

import br.com.sinah.notification.dto.NotificationRequestDTO;
import br.com.sinah.notification.dto.NotificationResponseDTO;
import br.com.sinah.notification.model.NotificationModel;
import br.com.sinah.patient.model.PatientModel;
import br.com.sinah.user.model.UserModel;
import br.com.sinah.ward.model.WardModel;
import br.com.sinah.department.model.DepartmentModel;
import br.com.sinah.room.model.RoomModel;

import java.time.LocalDateTime;
import java.util.UUID;

public class NotificationMapper {
    public static NotificationResponseDTO toDTO(NotificationModel model) {
        UUID patientId = model.getPatient() != null ? model.getPatient().getUuid() : null;
        UUID departmentId = model.getDepartment() != null ? model.getDepartment().getUuid() : null;
        UUID wardId = model.getWard() != null ? model.getWard().getUuid() : null;
        UUID roomId = model.getRoom() != null ? model.getRoom().getUuid() : null;
        UUID userId = model.getUser() != null ? model.getUser().getUuid() : null;

        return new NotificationResponseDTO(
                model.getUuid(),
                model.getTitle(),
                model.getDescription(),
                model.getPriority(),
                model.getNotificationType(),
                model.getNotificationDate(),
                patientId,
                departmentId,
                wardId,
                roomId,
                userId,
                model.getAdditionalData(),
                model.getCreatedAt(),
                model.getDueDate(),
                model.getUpdatedAt());
    }

    public static NotificationModel toModel(
            NotificationRequestDTO request,
            WardModel ward,
            PatientModel patient,
            UserModel user,
            DepartmentModel department,
            RoomModel room) {
        NotificationModel model = new NotificationModel();
        model.setTitle(request.title());
        model.setDescription(request.description());
        model.setPriority(request.priority());
        model.setPatient(patient);
        model.setNotificationType(request.notificationType());
        model.setDepartment(department);
        model.setWard(ward);
        model.setRoom(room);
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
            WardModel ward,
            PatientModel patient,
            UserModel user,
            DepartmentModel department,
            RoomModel room,
            NotificationRequestDTO request) {
        model.setTitle(request.title());
        model.setDescription(request.description());
        model.setPriority(request.priority());
        model.setPatient(patient);
        model.setNotificationType(request.notificationType());
        model.setDepartment(department);
        model.setWard(ward);
        model.setRoom(room);
        model.setNotificationDate(request.notificationDate());
        model.setUser(user);
        model.setAdditionalData(request.additionalData());
        model.setDueDate(request.dueDate() != null ? request.dueDate() : model.getDueDate());
        model.setUpdatedAt(LocalDateTime.now());
        return model;
    }
}
