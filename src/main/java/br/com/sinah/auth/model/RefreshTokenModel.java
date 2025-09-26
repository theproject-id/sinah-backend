package br.com.sinah.auth.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.Instant;
import java.util.UUID;

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

    public RefreshTokenModel() {
    }

    public RefreshTokenModel(UUID uuid, String token, UUID userUuid, Instant issuedAt, Instant expiresAt) {
        this.uuid = uuid;
        this.token = token;
        this.userUuid = userUuid;
        this.issuedAt = issuedAt;
        this.expiresAt = expiresAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public UUID getUserUuid() {
        return userUuid;
    }

    public void setUserUuid(UUID userUuid) {
        this.userUuid = userUuid;
    }

    public Instant getIssuedAt() {
        return issuedAt;
    }

    public void setIssuedAt(Instant issuedAt) {
        this.issuedAt = issuedAt;
    }

    public Instant getExpiresAt() {
        return expiresAt;
    }

    public void setExpiresAt(Instant expiresAt) {
        this.expiresAt = expiresAt;
    }

    public boolean isRevoked() {
        return revoked;
    }

    public void setRevoked(boolean revoked) {
        this.revoked = revoked;
    }
}
