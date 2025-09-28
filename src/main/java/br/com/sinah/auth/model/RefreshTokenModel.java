package br.com.sinah.auth.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "refresh_tokens")
public class RefreshTokenModel {
    @Column(unique = true, insertable = false, updatable = false, columnDefinition = "serial")
    private Long id;

    @Id
    @Column(unique = true, insertable = false, updatable = false)
    private UUID uuid;

    @Column(nullable = false, unique = true)
    private String token;

    @Column(nullable = false)
    private UUID userUuid;

    @Column(updatable = false)
    private Instant issuedAt;

    @Column(nullable = false)
    private Instant expiresAt;

    @Column
    private boolean revoked;

    public RefreshTokenModel() {}

    public RefreshTokenModel(UUID uuid, String token, UUID userUuid, Instant issuedAt, Instant expiresAt) {
        this.uuid = uuid;
        this.token = token;
        this.userUuid = userUuid;
        this.issuedAt = issuedAt;
        this.expiresAt = expiresAt;
    }
}
