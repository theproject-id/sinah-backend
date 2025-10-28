package br.com.sinah.patient.model;

import br.com.sinah.department.model.DepartmentModel;
import br.com.sinah.patient.enums.Gender;

import br.com.sinah.room.model.RoomModel;
import br.com.sinah.ward.model.WardModel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "patients")
public class PatientModel {
    @Column(unique = true, insertable = false, updatable = false, columnDefinition = "serial")
    private Long id;

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID uuid;

    @Column(name = "full_name", nullable = false)
    private String fullName;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Gender gender;

    @Column(name = "date_of_birth", nullable = false)
    private LocalDate dateOfBirth;

    @Column(name = "cpf", unique = true)
    private String cpf;

    @Column
    private String address;

    @Column
    private String phone;

    @ManyToOne
    @JoinColumn(name = "department_uuid", nullable = false)
    private DepartmentModel department;

    @ManyToOne
    @JoinColumn(name = "ward_uuid", nullable = false)
    private WardModel ward;

    @ManyToOne
    @JoinColumn(name = "room_uuid", nullable = false)
    private RoomModel room;

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
