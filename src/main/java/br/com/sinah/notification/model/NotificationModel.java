package br.com.sinah.notification.model;

import br.com.sinah.notification.enums.InfectionOrigin;
import br.com.sinah.notification.enums.NotificationStatus;
import br.com.sinah.notification.enums.NotificationType;
import br.com.sinah.patient.model.PatientModel;
import br.com.sinah.ward.model.WardModel;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "notifications")
public class NotificationModel {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private NotificationType notificationType; // IRA ou RM
    @Column(name = "notification_date", nullable = false)
    private LocalDateTime notificationDate;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private NotificationStatus status; // Suspeita, Confirmada, Descartada
    @ManyToOne
    @JoinColumn(name = "ward_id", nullable = false)
    private WardModel ala;
    @ManyToOne
    @JoinColumn(name = "patient_id", nullable = false)
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

    public NotificationModel(UUID id, NotificationType notificationType, LocalDateTime notificationDate, NotificationStatus status, WardModel ala, PatientModel patient, String description, String microorganism, String resistance, InfectionOrigin infectionOrigin, String localInfection, String responsibleUser, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.notificationType = notificationType;
        this.notificationDate = notificationDate;
        this.status = status;
        this.ala = ala;
        this.patient = patient;
        this.description = description;
        this.microorganism = microorganism;
        this.resistance = resistance;
        this.infectionOrigin = infectionOrigin;
        this.localInfection = localInfection;
        this.responsibleUser = responsibleUser;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public NotificationModel(NotificationType notificationType, LocalDateTime localDateTime, NotificationStatus status, UUID uuid, UUID patientId, String description, String microorganism, String resistance, InfectionOrigin infectionOrigin, String s, String string, LocalDateTime now, LocalDateTime nowed) {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public NotificationType getNotificationType() {
        return notificationType;
    }

    public void setNotificationType(NotificationType notificationType) {
        this.notificationType = notificationType;
    }

    public LocalDateTime getNotificationDate() {
        return notificationDate;
    }

    public void setNotificationDate(LocalDateTime notificationDate) {
        this.notificationDate = notificationDate;
    }

    public NotificationStatus getStatus() {
        return status;
    }

    public void setStatus(NotificationStatus status) {
        this.status = status;
    }

    public WardModel getAla() {
        return ala;
    }

    public void setAla(WardModel ala) {
        this.ala = ala;
    }

    public PatientModel getPatient() {
        return patient;
    }

    public void setPatient(PatientModel patient) {
        this.patient = patient;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMicroorganism() {
        return microorganism;
    }

    public void setMicroorganism(String microorganism) {
        this.microorganism = microorganism;
    }

    public String getResistance() {
        return resistance;
    }

    public void setResistance(String resistance) {
        this.resistance = resistance;
    }

    public InfectionOrigin getInfectionOrigin() {
        return infectionOrigin;
    }

    public void setInfectionOrigin(InfectionOrigin infectionOrigin) {
        this.infectionOrigin = infectionOrigin;
    }

    public String getLocalInfection() {
        return localInfection;
    }

    public void setLocalInfection(String localInfection) {
        this.localInfection = localInfection;
    }

    public String getResponsibleUser() {
        return responsibleUser;
    }

    public void setResponsibleUser(String responsibleUser) {
        this.responsibleUser = responsibleUser;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        NotificationModel that = (NotificationModel) o;
        return Objects.equals(id, that.id) && notificationType == that.notificationType && Objects.equals(notificationDate, that.notificationDate) && status == that.status && Objects.equals(ala, that.ala) && Objects.equals(patient, that.patient) && Objects.equals(description, that.description) && Objects.equals(microorganism, that.microorganism) && Objects.equals(resistance, that.resistance) && infectionOrigin == that.infectionOrigin && Objects.equals(localInfection, that.localInfection) && Objects.equals(responsibleUser, that.responsibleUser) && Objects.equals(createdAt, that.createdAt) && Objects.equals(updatedAt, that.updatedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, notificationType, notificationDate, status, ala, patient, description, microorganism, resistance, infectionOrigin, localInfection, responsibleUser, createdAt, updatedAt);
    }

    @Override
    public String toString() {
        return "NotificationModel{" +
                "id=" + id +
                ", notificationType=" + notificationType +
                ", notificationDate=" + notificationDate +
                ", status=" + status +
                ", ala=" + ala +
                ", patient=" + patient +
                ", description='" + description + '\'' +
                ", microorganism='" + microorganism + '\'' +
                ", resistance='" + resistance + '\'' +
                ", infectionOrigin=" + infectionOrigin +
                ", localInfection='" + localInfection + '\'' +
                ", responsibleUser='" + responsibleUser + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}

