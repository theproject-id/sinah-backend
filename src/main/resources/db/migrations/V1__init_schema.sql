-- TABLE Departments;

CREATE TABLE public.departments (
	created_at timestamp(6) NOT NULL,
	id serial4 NOT NULL,
	updated_at timestamp(6) NULL,
	"uuid" uuid NOT NULL,
	description text NULL,
	"name" varchar(255) NOT NULL,
	CONSTRAINT departments_id_key UNIQUE (id),
	CONSTRAINT departments_pkey PRIMARY KEY (uuid)
);

-- TABLE Refresh Tokens;

CREATE TABLE public.refresh_tokens (
	revoked bool NULL,
	expires_at timestamptz(6) NOT NULL,
	id serial4 NOT NULL,
	issued_at timestamptz(6) NULL,
	user_uuid uuid NOT NULL,
	"uuid" uuid NOT NULL,
	"token" varchar(255) NOT NULL,
	CONSTRAINT refresh_tokens_id_key UNIQUE (id),
	CONSTRAINT refresh_tokens_pkey PRIMARY KEY (uuid),
	CONSTRAINT refresh_tokens_token_key UNIQUE (token)
);

-- TABLE Wards;

CREATE TABLE public.wards (
	created_at timestamp(6) NOT NULL,
	id serial4 NOT NULL,
	updated_at timestamp(6) NULL,
	"uuid" uuid NOT NULL,
	description text NULL,
	"name" varchar(255) NOT NULL,
	CONSTRAINT wards_id_key UNIQUE (id),
	CONSTRAINT wards_pkey PRIMARY KEY (uuid)
);

-- TABLE Rooms;

CREATE TABLE public.rooms (
	created_at timestamp(6) NOT NULL,
	id serial4 NOT NULL,
	updated_at timestamp(6) NULL,
	"uuid" uuid NOT NULL,
	ward_uuid uuid NOT NULL,
	description text NULL,
	"name" varchar(255) NOT NULL,
	CONSTRAINT rooms_id_key UNIQUE (id),
	CONSTRAINT rooms_pkey PRIMARY KEY (uuid),
	CONSTRAINT fkfye81bfbljou3f46cvcclg8ep FOREIGN KEY (ward_uuid) REFERENCES public.wards("uuid")
);

-- TABLE Users;

CREATE TABLE public.users (
	created_at timestamptz(6) NULL,
	id serial4 NOT NULL,
	updated_at timestamptz(6) NULL,
	department_uuid uuid NULL,
	"uuid" uuid NOT NULL,
	email varchar(255) NOT NULL,
	first_name varchar(255) NOT NULL,
	last_name varchar(255) NOT NULL,
	"password" varchar(255) NOT NULL,
	"role" varchar(255) NOT NULL,
	username varchar(255) NOT NULL,
	CONSTRAINT users_id_key UNIQUE (id),
	CONSTRAINT users_pkey PRIMARY KEY (uuid),
	CONSTRAINT users_username_key UNIQUE (username),
	CONSTRAINT fk545no9m4q5kkp2r6p76gi08my FOREIGN KEY (department_uuid) REFERENCES public.departments("uuid")
);

-- TABLE Beds;

CREATE TABLE public.beds (
	"number" int4 NOT NULL,
	created_at timestamp(6) NOT NULL,
	id serial4 NOT NULL,
	updated_at timestamp(6) NULL,
	room_uuid uuid NOT NULL,
	"uuid" uuid NOT NULL,
	CONSTRAINT beds_id_key UNIQUE (id),
	CONSTRAINT beds_pkey PRIMARY KEY (uuid),
	CONSTRAINT fk651g0h5m5425ggrbmoaksr4od FOREIGN KEY (room_uuid) REFERENCES public.rooms("uuid")
);

-- TABLE Patients;

CREATE TABLE public.patients (
	date_of_birth date NOT NULL,
	created_at timestamp(6) NOT NULL,
	id serial4 NOT NULL,
	updated_at timestamp(6) NULL,
	bed_uuid uuid NOT NULL,
	"uuid" uuid NOT NULL,
	address varchar(255) NULL,
	cpf varchar(255) NULL,
	full_name varchar(255) NOT NULL,
	gender varchar(255) NOT NULL,
	phone varchar(255) NULL,
	CONSTRAINT patients_bed_uuid_key UNIQUE (bed_uuid),
	CONSTRAINT patients_cpf_key UNIQUE (cpf),
	CONSTRAINT patients_gender_check CHECK (((gender)::text = ANY ((ARRAY['MALE'::character varying, 'FEMALE'::character varying])::text[]))),
	CONSTRAINT patients_id_key UNIQUE (id),
	CONSTRAINT patients_pkey PRIMARY KEY (uuid),
	CONSTRAINT fk6pjagw6c45exf3n1ubq492qn9 FOREIGN KEY (bed_uuid) REFERENCES public.beds("uuid")
);

-- TABLE Notifications;

CREATE TABLE public.notifications (
	created_at timestamp(6) NOT NULL,
	due_date timestamp(6) NOT NULL,
	id serial4 NOT NULL,
	notification_date timestamp(6) NOT NULL,
	updated_at timestamp(6) NOT NULL,
	patient_uuid uuid NOT NULL,
	user_uuid uuid NOT NULL,
	"uuid" uuid NOT NULL,
	description text NULL,
	notification_type varchar(255) NOT NULL,
	priority varchar(255) NOT NULL,
	title varchar(255) NOT NULL,
	additional_data jsonb NULL,
	CONSTRAINT notifications_id_key UNIQUE (id),
	CONSTRAINT notifications_notification_type_check CHECK (((notification_type)::text = ANY ((ARRAY['IRA'::character varying, 'RM'::character varying])::text[]))),
	CONSTRAINT notifications_pkey PRIMARY KEY (uuid),
	CONSTRAINT notifications_priority_check CHECK (((priority)::text = ANY ((ARRAY['SUSPECTED'::character varying, 'CONFIRMED'::character varying, 'DISCARDED'::character varying])::text[]))),
	CONSTRAINT fketbinhr7ckam4kqtb53dksdv0 FOREIGN KEY (patient_uuid) REFERENCES public.patients("uuid"),
	CONSTRAINT fks0x0oclrnldoletd5xfid3ru7 FOREIGN KEY (user_uuid) REFERENCES public.users("uuid")
);