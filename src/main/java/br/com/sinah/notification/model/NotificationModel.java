package br.com.sinah.notification.model;
import br.com.sinah.department.model.DepartmentModel;
import br.com.sinah.room.model.RoomModel;
import io.hypersistence.utils.hibernate.type.json.JsonType;
import br.com.sinah.notification.enums.NotificationType;
import br.com.sinah.notification.enums.PriorityLevel;
import br.com.sinah.patient.model.PatientModel;
import br.com.sinah.user.model.UserModel;
import br.com.sinah.ward.model.WardModel;

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

    @Column(name = "title", nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PriorityLevel priority;

    @ManyToOne
    @JoinColumn(name="patient_uuid", nullable = false)
    private PatientModel patient;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private NotificationType notificationType;

    @ManyToOne
    @JoinColumn(name = "department_uuid", nullable = false)
    private DepartmentModel department;

    @ManyToOne
    @JoinColumn(name = "ward_uuid", nullable = false)
    private WardModel ward;

    @ManyToOne
    @JoinColumn(name = "room_uuid", nullable = false)
    private RoomModel room;

    @Column(nullable = false)
    private LocalDateTime notificationDate;

    @ManyToOne
    @JoinColumn(name = "user_uuid", nullable = false)
    private UserModel user;

    @Type(JsonType.class)
    @Column(name = "additional_data", columnDefinition = "jsonb")
    private Map<String, Object> additionalData;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private LocalDateTime dueDate;

    @Column(nullable = false)
    private LocalDateTime updatedAt;
}
