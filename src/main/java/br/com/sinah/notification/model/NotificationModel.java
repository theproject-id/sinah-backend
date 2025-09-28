package br.com.sinah.notification.model;

import br.com.sinah.notification.enums.InfectionOrigin;
import br.com.sinah.notification.enums.NotificationStatus;
import br.com.sinah.notification.enums.NotificationType;
import br.com.sinah.patient.model.PatientModel;
import br.com.sinah.ward.model.WardModel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "notifications")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class NotificationModel {
    @Column(unique = true, insertable = false, updatable = false, columnDefinition = "serial")
    private Long id;
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID uuid;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private NotificationType notificationType; // IRA ou RM
    @Column(name = "notification_date", nullable = false)
    private LocalDateTime notificationDate;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private NotificationStatus status; // Suspeita, Confirmada, Descartada
    @ManyToOne
    @JoinColumn(name = "ward_uuid", nullable = false)
    private WardModel ala;
    @ManyToOne
    @JoinColumn(name = "patient_uuid", nullable = false)
    private PatientModel patient;
    @Column(columnDefinition = "TEXT")
    private String description;
    @Column
    private String microorganism;
    @Column
    private String resistance;
    @Column(name = "infection_origin")
    @Enumerated(EnumType.STRING)
    private InfectionOrigin infectionOrigin;
    @Column(name = "local_infection")
    private String localInfection;
    private String responsibleUser;
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }
    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}

