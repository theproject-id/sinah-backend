CREATE TABLE users (
        id              BIGINT GENERATED ALWAYS AS IDENTITY,
        uuid            UUID PRIMARY KEY UNIQUE NOT NULL,
        first_name      VARCHAR(30) NOT NULL,
        last_name       VARCHAR(30) NOT NULL,
        username        VARCHAR(30) NOT NULL,
        email           VARCHAR(50) NOT NULL,
        password        VARCHAR(255) NOT NULL,
        role            VARCHAR(30) NOT NULL,
        created_at      TIMESTAMP NOT NULL DEFAULT now(),
        updated_at      TIMESTAMP NOT NULL DEFAULT now()
);

CREATE TABLE refresh_tokens (
    id              BIGINT GENERATED ALWAYS AS IDENTITY,
    uuid            UUID PRIMARY KEY UNIQUE NOT NULL,
    token           VARCHAR(255) NOT NULL UNIQUE,
    user_uuid       UUID NOT NULL,
    issued_at       TIMESTAMP NOT NULL DEFAULT NOW(),
    expires_at      TIMESTAMP NOT NULL,
    revoked         BOOLEAN NOT NULL DEFAULT FALSE,
    CONSTRAINT fk_refresh_tokens_user_uuid
        FOREIGN KEY (user_uuid) REFERENCES users(uuid)
        ON DELETE CASCADE
);

CREATE TABLE notifications (
    id                  BIGINT GENERATED ALWAYS AS IDENTITY,
    uuid                UUID PRIMARY KEY UNIQUE NOT NULL,
    title               VARCHAR(255) NOT NULL,
    description         TEXT NOT NULL,
    type                VARCHAR(50) NOT NULL,
    priority            VARCHAR(50) NOT NULL,
    patient_id          UUID NOT NULL,
    patient_name        VARCHAR(100) NOT NULL,
    patient_room        VARCHAR(50),
    patient_bed         VARCHAR(50),
    created_by          UUID NOT NULL,
    created_by_name     VARCHAR(100) NOT NULL,
    due_date            TIMESTAMP,
    notes               TEXT,
    additional_data     JSONB,
    created_at          TIMESTAMP NOT NULL DEFAULT now(),
    updated_at          TIMESTAMP NOT NULL DEFAULT now()
);
