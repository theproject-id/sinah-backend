package br.com.sinah.notification.model;

import br.com.sinah.notification.enums.InfectionOrigin;
import br.com.sinah.notification.enums.NotificationStatus;
import br.com.sinah.notification.enums.NotificationType;
import br.com.sinah.patient.model.PatientModel;
import br.com.sinah.user.model.UserModel;
import br.com.sinah.ward.model.WardModel;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
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

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private NotificationType notificationType;

    @Column(nullable = false)
    private LocalDateTime notificationDate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private NotificationStatus status;

    @ManyToOne
    @JoinColumn(name = "ward_uuid", nullable = false)
    private WardModel ala;

    @ManyToOne
    @JoinColumn(nullable = false)
    private PatientModel patient;

    @ManyToOne
    @JoinColumn(name = "user_uuid", nullable = false)
    private UserModel user;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column
    private String microorganism;

    @Column
    private String resistance;

    @Column
    @Enumerated(EnumType.STRING)
    private InfectionOrigin infectionOrigin;

    @Column
    private String localInfection;

    private String responsibleUser;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false, updatable = false)
    private LocalDateTime updatedAt;
}
