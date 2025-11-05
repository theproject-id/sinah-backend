-- Popula o banco com 5 registros para cada entidade conforme o schema em V1__init_schema.sql

-- 1) Departments (5 registros)
INSERT INTO public.departments ("uuid", "name", description, created_at, updated_at) VALUES
  ('11111111-1111-1111-1111-111111111111','Cardiologia','Departamento de Cardiologia', now(), now()),
  ('22222222-2222-2222-2222-222222222222','Infectologia','Departamento de Infectologia', now(), now()),
  ('33333333-3333-3333-3333-333333333333','Cirurgia','Departamento de Cirurgia', now(), now()),
  ('44444444-4444-4444-4444-444444444444','Pediatria','Departamento de Pediatria', now(), now()),
  ('55555555-5555-5555-5555-555555555555','Clínica Médica','Departamento de Clínica Médica', now(), now());

-- 2) Wards (5 registros)
INSERT INTO public.wards ("uuid", "name", description, created_at, updated_at) VALUES
  ('aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa','UTI','Unidade de Terapia Intensiva', now(), now()),
  ('bbbbbbbb-bbbb-bbbb-bbbb-bbbbbbbbbbbb','Emergência','Setor de emergência hospitalar', now(), now()),
  ('cccccccc-cccc-cccc-cccc-cccccccccccc','Pediatria','Ala pediátrica', now(), now()),
  ('dddddddd-dddd-dddd-dddd-dddddddddddd','Enfermaria Geral','Ala para internação geral', now(), now()),
  ('eeeeeeee-eeee-eeee-eeee-eeeeeeeeeeee','Centro Cirúrgico','Área de procedimentos cirúrgicos', now(), now());

-- 3) Users (5 registros) - password hashed placeholder
-- NOTE: passwords are example bcrypt-like strings; replace/securize in production
INSERT INTO public.users ("uuid", username, first_name, last_name, email, "password", "role", department_uuid, created_at, updated_at) VALUES
  ('00000000-0000-0000-0000-000000000001','admin','Usuário','Admin','admin@hospital.com','$2a$10$eXampleHashAdmin1234567890abcd','ADMIN','11111111-1111-1111-1111-111111111111', now(), now()),
  ('00000000-0000-0000-0000-000000000002','nurse.maria','Maria','Silva','maria.silva@hospital.com','$2a$10$eXampleHashNurse1234567890abcd','NURSE','22222222-2222-2222-2222-222222222222', now(), now()),
  ('00000000-0000-0000-0000-000000000003','dr.joao','João','Santos','joao.santos@hospital.com','$2a$10$eXampleHashDrJoao1234567890','DOCTOR','33333333-3333-3333-3333-333333333333', now(), now()),
  ('00000000-0000-0000-0000-000000000004','enfermeira.lucia','Lúcia','Fernandes','lucia.fernandes@hospital.com','$2a$10$eXampleHashLucia1234567890','NURSE','44444444-4444-4444-4444-444444444444', now(), now()),
  ('00000000-0000-0000-0000-000000000005','dr.carlos','Carlos','Mendes','carlos.mendes@hospital.com','$2a$10$eXampleHashDrCarlos123456789','DOCTOR','55555555-5555-5555-5555-555555555555', now(), now());

-- 4) Rooms (5 registros) - cada sala pertence a uma ward
INSERT INTO public.rooms ("uuid", name, ward_uuid, description, created_at, updated_at) VALUES
  ('61111111-1111-1111-1111-111111111111','UTI-1','aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa','Sala de UTI 1', now(), now()),
  ('62222222-2222-2222-2222-222222222222','EMG-1','bbbbbbbb-bbbb-bbbb-bbbb-bbbbbbbbbbbb','Sala de Emergência 1', now(), now()),
  ('63333333-3333-3333-3333-333333333333','PED-1','cccccccc-cccc-cccc-cccc-cccccccccccc','Sala Pediatria 1', now(), now()),
  ('64444444-4444-4444-4444-444444444444','ENF-1','dddddddd-dddd-dddd-dddd-dddddddddddd','Sala Enfermaria 1', now(), now()),
  ('65555555-5555-5555-5555-555555555555','CC-1','eeeeeeee-eeee-eeee-eeee-eeeeeeeeeeee','Sala Centro Cirúrgico 1', now(), now());

-- 5) Beds (5 registros) - cada leito pertence a uma room
INSERT INTO public.beds ("uuid", "number", room_uuid, created_at, updated_at) VALUES
  ('b1111111-1111-1111-1111-111111111111', 1, '61111111-1111-1111-1111-111111111111', now(), now()),
  ('b2222222-2222-2222-2222-222222222222', 2, '62222222-2222-2222-2222-222222222222', now(), now()),
  ('b3333333-3333-3333-3333-333333333333', 3, '63333333-3333-3333-3333-333333333333', now(), now()),
  ('b4444444-4444-4444-4444-444444444444', 4, '64444444-4444-4444-4444-444444444444', now(), now()),
  ('b5555555-5555-5555-5555-555555555555', 5, '65555555-5555-5555-5555-555555555555', now(), now());

-- 6) Patients (5 registros) - cada paciente ocupa um leito (bed_uuid) e gender deve ser 'MALE' ou 'FEMALE'
INSERT INTO public.patients ("uuid", full_name, gender, date_of_birth, bed_uuid, address, cpf, phone, created_at, updated_at) VALUES
  ('10000000-0000-0000-0000-000000000001','Alice Martins','FEMALE','1982-04-10','b1111111-1111-1111-1111-111111111111','Rua das Flores, 123','12345678901','+55 11 91234-5678', now(), now()),
  ('10000000-0000-0000-0000-000000000002','Bruno Silva','MALE','1978-11-22','b2222222-2222-2222-2222-222222222222','Av. Central, 456','23456789012','+55 19 99876-5432', now(), now()),
  ('10000000-0000-0000-0000-000000000003','Carla Souza','FEMALE','1995-01-15','b3333333-3333-3333-3333-333333333333','Rua B, 78','34567890123','+55 41 91234-4321', now(), now()),
  ('10000000-0000-0000-0000-000000000004','Daniel Oliveira','MALE','1989-07-30','b4444444-4444-4444-4444-444444444444','Rua do Sol, 321','45678901234','+55 71 93456-1234', now(), now()),
  ('10000000-0000-0000-0000-000000000005','Eduarda Lima','FEMALE','2000-03-25','b5555555-5555-5555-5555-555555555555','Av. das Américas, 999','56789012345','+55 21 95678-4321', now(), now());

-- 7) Notifications (5 registros) - usa notification_type ('IRA'|'RM') e priority ('SUSPECTED'|'CONFIRMED'|'DISCARDED')
INSERT INTO public.notifications ("uuid", notification_type, notification_date, due_date, updated_at, created_at, patient_uuid, user_uuid, title, description, additional_data, priority) VALUES
  ('71111111-1111-1111-1111-111111111111','IRA', now(), now() + interval '1 day', now(), now(), '10000000-0000-0000-0000-000000000001','00000000-0000-0000-0000-000000000002','Suspeita de infecção respiratória','Paciente com tosse e febre', jsonb_build_object('microorganism','Klebsiella pneumoniae'), 'SUSPECTED'),
  ('72222222-2222-2222-2222-222222222222','RM', now(), now() + interval '2 day', now(), now(), '10000000-0000-0000-0000-000000000002','00000000-0000-0000-0000-000000000003','Confirmado MRSA','Isolamento por MRSA', jsonb_build_object('microorganism','Staphylococcus aureus'), 'CONFIRMED'),
  ('73333333-3333-3333-3333-333333333333','IRA', now(), now() + interval '1 day', now(), now(), '10000000-0000-0000-0000-000000000003','00000000-0000-0000-0000-000000000004','Colonização por VRE','Paciente colonizado por VRE', jsonb_build_object('microorganism','Enterococcus faecium'), 'SUSPECTED'),
  ('74444444-4444-4444-4444-444444444444','RM', now(), now() + interval '3 day', now(), now(), '10000000-0000-0000-0000-000000000004','00000000-0000-0000-0000-000000000005','Precaução de contato','Paciente em precaução de contato', NULL, 'DISCARDED'),
  ('75555555-5555-5555-5555-555555555555','IRA', now(), now() + interval '2 day', now(), now(), '10000000-0000-0000-0000-000000000005','00000000-0000-0000-0000-000000000001','Infecção pós-operatória','Suspeita de infecção pós-op', jsonb_build_object('microorganism','Escherichia coli'), 'CONFIRMED');

-- 8) Refresh Tokens (5 registros) - para os usuários inseridos
INSERT INTO public.refresh_tokens ("uuid", "token", user_uuid, issued_at, expires_at, revoked) VALUES
  ('81111111-1111-1111-1111-111111111111','refresh-token-1','00000000-0000-0000-0000-000000000001', now(), now() + interval '30 day', false),
  ('82222222-2222-2222-2222-222222222222','refresh-token-2','00000000-0000-0000-0000-000000000002', now(), now() + interval '30 day', false),
  ('83333333-3333-3333-3333-333333333333','refresh-token-3','00000000-0000-0000-0000-000000000003', now(), now() + interval '30 day', false),
  ('84444444-4444-4444-4444-444444444444','refresh-token-4','00000000-0000-0000-0000-000000000004', now(), now() + interval '30 day', false),
  ('85555555-5555-5555-5555-555555555555','refresh-token-5','00000000-0000-0000-0000-000000000005', now(), now() + interval '30 day', false);
