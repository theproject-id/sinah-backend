CREATE TABLE patients (
    id                      BIGINT GENERATED ALWAYS AS IDENTITY,
    uuid                    UUID PRIMARY KEY UNIQUE NOT NULL,
    full_name               VARCHAR(255) NOT NULL,
    gender                  VARCHAR(20) NOT NULL,
    date_of_birth           DATE NOT NULL,
    medical_record_number   VARCHAR(100) NOT NULL UNIQUE,
    national_id             VARCHAR(100) UNIQUE,
    address                 TEXT,
    phone                   VARCHAR(50),
    clinical_history        TEXT,
    created_at              TIMESTAMP NOT NULL DEFAULT now(),
    updated_at              TIMESTAMP NOT NULL DEFAULT now()
);
