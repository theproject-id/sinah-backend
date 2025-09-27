CREATE TABLE wards (
    id                  BIGINT GENERATED ALWAYS AS IDENTITY,
    uuid                UUID PRIMARY KEY UNIQUE NOT NULL,
    name                VARCHAR(150),
    description         TEXT,
    created_at          TIMESTAMP NOT NULL DEFAULT now(),
    updated_at          TIMESTAMP NOT NULL DEFAULT now()
);