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