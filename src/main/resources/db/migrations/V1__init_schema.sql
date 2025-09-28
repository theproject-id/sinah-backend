

CREATE TABLE wards (
    id                  BIGINT GENERATED ALWAYS AS IDENTITY,
    uuid                UUID PRIMARY KEY UNIQUE NOT NULL,
    name                VARCHAR(150),
    description         TEXT,
    created_at          TIMESTAMP NOT NULL DEFAULT now(),
    updated_at          TIMESTAMP NOT NULL DEFAULT now()
);
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
CREATE TABLE notifications (
    id                  BIGINT GENERATED ALWAYS AS IDENTITY,
    uuid                UUID PRIMARY KEY UNIQUE NOT NULL,
    notification_type   VARCHAR(20) NOT NULL,
    notification_date   TIMESTAMP NOT NULL,
    status              VARCHAR(20) NOT NULL,
    ward_uuid           UUID NOT NULL,
    patient_uuid        UUID NOT NULL,
    description         TEXT,
    microorganism       VARCHAR(255),
    resistance          VARCHAR(255),
    infection_origin    VARCHAR(20),
    local_infection     VARCHAR(255),
    responsible_user    VARCHAR(255),
    created_at          TIMESTAMP NOT NULL DEFAULT now(),
    updated_at          TIMESTAMP NOT NULL DEFAULT now(),

    CONSTRAINT fk_notifications_ward_uuid
        FOREIGN KEY (ward_uuid) REFERENCES wards(uuid)
        ON DELETE RESTRICT,

    CONSTRAINT fk_notifications_patient_uuid
        FOREIGN KEY (patient_uuid) REFERENCES patients(uuid)
        ON DELETE RESTRICT
);

