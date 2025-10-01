package br.com.sinah.notification.model;

import io.hypersistence.utils.hibernate.type.json.JsonType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "notifications")
public class NotificationModel {
    @Column(unique = true, insertable = false, updatable = false, columnDefinition = "serial")
    private Long id;

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID uuid;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String description;

    @Column(nullable = false, length = 50)
    private String type;

    @Column(nullable = false, length = 50)
    private String priority;

    @Column(name = "patient_id", nullable = false)
    private UUID patientId;

    @Column(name = "patient_name", nullable = false, length = 100)
    private String patientName;

    @Column(name = "patient_room", length = 50)
    private String patientRoom;

    @Column(name = "patient_bed", length = 50)
    private String patientBed;

    @Column(name = "created_by", nullable = false)
    private UUID createdBy;

    @Column(name = "created_by_name", nullable = false, length = 100)
    private String createdByName;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "due_date")
    private LocalDateTime dueDate;

    @Column(columnDefinition = "TEXT")
    private String notes;

    @Type(JsonType.class)
    @Column(name = "additional_data", columnDefinition = "jsonb")
    private Map<String, Object> additionalData;
}
