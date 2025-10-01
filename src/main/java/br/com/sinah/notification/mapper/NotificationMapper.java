package br.com.sinah.notification.mapper;

import br.com.sinah.notification.dto.NotificationRequestDTO;
import br.com.sinah.notification.dto.NotificationResponseDTO;
import br.com.sinah.notification.model.NotificationModel;

public class NotificationMapper {

    public static NotificationResponseDTO toDTO(NotificationModel model) {
        return new NotificationResponseDTO(
                model.getUuid(),
                model.getTitle(),
                model.getDescription(),
                model.getType(),
                model.getPriority(),
                model.getPatientId(),
                model.getPatientName(),
                model.getPatientRoom(),
                model.getPatientBed(),
                model.getCreatedBy(),
                model.getCreatedByName(),
                model.getCreatedAt(),
                model.getUpdatedAt(),
                model.getDueDate(),
                model.getNotes(),
                model.getAdditionalData());
    }

    public static NotificationModel toModel(NotificationRequestDTO dto) {
        var model = new NotificationModel();
        model.setTitle(dto.title());
        model.setDescription(dto.description());
        model.setType(dto.type());
        model.setPriority(dto.priority());
        model.setPatientId(dto.patientId());
        model.setPatientName(dto.patientName());
        model.setPatientRoom(dto.patientRoom());
        model.setPatientBed(dto.patientBed());
        model.setCreatedBy(dto.createdBy());
        model.setDueDate(dto.dueDate());
        model.setNotes(dto.notes());
        model.setAdditionalData(dto.additionalData());
        return model;
    }

    public static NotificationModel toUpdate(NotificationModel model, NotificationRequestDTO dto) {
        model.setTitle(dto.title());
        model.setDescription(dto.description());
        model.setType(dto.type());
        model.setPriority(dto.priority());
        model.setPatientId(dto.patientId());
        model.setPatientName(dto.patientName());
        model.setPatientRoom(dto.patientRoom());
        model.setPatientBed(dto.patientBed());
        model.setDueDate(dto.dueDate());
        model.setNotes(dto.notes());
        model.setAdditionalData(dto.additionalData());
        return model;
    }
}
