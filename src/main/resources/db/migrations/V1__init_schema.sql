CREATE TABLE users (
        id              INTEGER GENERATED ALWAYS AS IDENTITY,
        uuid UUID       PRIMARY KEY UNIQUE NOT NULL,
        first_name      VARCHAR(30) NOT NULL,
        last_name       VARCHAR(30) NOT NULL,
        username        VARCHAR(30) NOT NULL,
        email           VARCHAR(50) NOT NULL,
        password        VARCHAR(255) NOT NULL,
        role            VARCHAR(30) NOT NULL,
        created_at      TIMESTAMP NOT NULL DEFAULT now(),
        updated_at      TIMESTAMP NOT NULL DEFAULT now()
);