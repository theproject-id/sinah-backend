-- Inserir 1 usuário default para referência nas notificações
INSERT INTO users (uuid, username, first_name, last_name, email, password, role, created_at, updated_at) VALUES
  ('00000000-0000-0000-0000-000000000001', 'default.user', 'Usuário', 'Default', 'default@hospital.com', '$2a$10$EixZaYVK1fsbw1ZfbX3OXePaWxn96p36r5h4bT7y1l1r1r1r1r1r1', 'ADMIN', now(), now());

-- Inserir 5 wards
INSERT INTO wards (uuid, name, description, created_at, updated_at) VALUES
  ('f0a9eaa6-7e8a-4d2f-bf31-0d2ab3c99a01', 'UTI', 'Unidade de Terapia Intensiva', now(), now()),
  ('a9f51cc0-ec9e-4dd5-bdc4-82a7e2a393ac', 'Emergência', 'Setor de emergência hospitalar', now(), now()),
  ('c373dd4a-d0ce-4d63-bbb6-7fcdbb5df0ae', 'Pediatria', 'Ala destinada ao atendimento infantil', now(), now()),
  ('63d18972-b7e1-4297-b09b-93c9028755f7', 'Enfermaria Geral', 'Ala para internação geral de pacientes', now(), now()),
  ('8f3e5d8d-68b1-49e2-8f4a-3960f1ebf245', 'Centro Cirúrgico', 'Área destinada a procedimentos cirúrgicos', now(), now());

-- Inserir 5 patients
INSERT INTO patients (
    uuid, full_name, gender, date_of_birth,
    medical_record_number, national_id, address, phone,
    clinical_history, created_at, updated_at
) VALUES
(
    '10000000-0000-0000-0000-000000000001',
    'Alice Martins',
    'FEMALE',
    '1982-04-10',
    'MRN-1001',
    '12345678901',
    'Rua das Flores, 123 - São Paulo, SP',
    '+55 11 91234-5678',
    'Patient with chronic asthma. No recent hospitalizations.',
    now(), now()
),
(
    '10000000-0000-0000-0000-000000000002',
    'Bruno Silva',
    'MALE',
    '1978-11-22',
    'MRN-1002',
    '23456789012',
    'Av. Central, 456 - Campinas, SP',
    '+55 19 99876-5432',
    'Diabetic, using insulin daily. No surgical history.',
    now(), now()
),
(
    '10000000-0000-0000-0000-000000000003',
    'Carla Souza',
    'FEMALE',
    '1995-01-15',
    'MRN-1003',
    '34567890123',
    'Rua B, 78 - Curitiba, PR',
    '+55 41 91234-4321',
    'Healthy. Recent appendectomy.',
    now(), now()
),
(
    '10000000-0000-0000-0000-000000000004',
    'Daniel Oliveira',
    'MALE',
    '1989-07-30',
    'MRN-1004',
    '45678901234',
    'Rua do Sol, 321 - Salvador, BA',
    '+55 71 93456-1234',
    'History of hypertension and high cholesterol.',
    now(), now()
),
(
    '10000000-0000-0000-0000-000000000005',
    'Eduarda Lima',
    'FEMALE',
    '2000-03-25',
    'MRN-1005',
    '56789012345',
    'Av. das Américas, 999 - Rio de Janeiro, RJ',
    '+55 21 95678-4321',
    'Patient with kidney transplant in 2019. On immunosuppressants.',
    now(), now()
);
-- Inserir 5 notifications com valores ENUM válidos
INSERT INTO notifications (
    uuid,
    notification_type,
    notification_date,
    status,
    ward_uuid,
    patient_uuid,
    user_uuid,
    description,
    microorganism,
    resistance,
    infection_origin,
    local_infection,
    responsible_user,
    created_at,
    updated_at
) VALUES
  (
    'b93a10c5-1e9e-4d8f-a2f5-948a2b224b01',
    'IRA',
    now(),
    'SUSPECTED',
    'f0a9eaa6-7e8a-4d2f-bf31-0d2ab3c99a01',  -- UTI
    '10000000-0000-0000-0000-000000000001',  -- Alice Martins
    '00000000-0000-0000-0000-000000000001',  -- Usuário default
    'Infecção por KPC',
    'Klebsiella pneumoniae',
    'Carbapenêmico',
    'HOSPITAL_ACQUIRED',
    'Pulmonar',
    'enfermeira.maria',
    now(),
    now()
  ),
  (
    'd304ec39-24e0-4e41-a957-0e0459b1a98d',
    'RM',
    now(),
    'CONFIRMED',
    'a9f51cc0-ec9e-4dd5-bdc4-82a7e2a393ac',  -- Emergência
    '10000000-0000-0000-0000-000000000002',  -- Bruno Silva
    '00000000-0000-0000-0000-000000000001',  -- Usuário default
    'Paciente com MRSA',
    'Staphylococcus aureus',
    'Oxacilina',
    'COMMUNITY_ACQUIRED',
    'Cutâneo',
    'dr.joao.silva',
    now(),
    now()
  ),
  (
    'ee7e7d11-fb65-4f6f-b15a-d5d57d1f730e',
    'IRA',
    now(),
    'SUSPECTED',
    'c373dd4a-d0ce-4d63-bbb6-7fcdbb5df0ae',  -- Pediatria
    '10000000-0000-0000-0000-000000000003',  -- Carla Souza
    '00000000-0000-0000-0000-000000000001',  -- Usuário default
    'Colonização por VRE',
    'Enterococcus faecium',
    'Vancomicina',
    'HOSPITAL_ACQUIRED',
    'Gastrointestinal',
    'enfermeiro.pedro',
    now(),
    now()
  ),
  (
    'c2d2626e-24c7-4e14-b65f-1cb978c9372b',
    'RM',
    now(),
    'DISCARDED',
    '63d18972-b7e1-4297-b09b-93c9028755f7',  -- Enfermaria Geral
    '10000000-0000-0000-0000-000000000004',  -- Daniel Oliveira
    '00000000-0000-0000-0000-000000000001',  -- Usuário default
    'Paciente em precaução de contato',
    NULL,
    NULL,
    'COMMUNITY_ACQUIRED',
    NULL,
    'enfermeira.lucia',
    now(),
    now()
  ),
  (
    'aad4f52f-8428-4fc9-96e0-071dff7f32ff',
    'IRA',
    now(),
    'CONFIRMED',
    '8f3e5d8d-68b1-49e2-8f4a-3960f1ebf245',  -- Centro Cirúrgico
    '10000000-0000-0000-0000-000000000005',  -- Eduarda Lima
    '00000000-0000-0000-0000-000000000001',  -- Usuário default
    'Infecção pós-operatória',
    'Escherichia coli',
    'Cefalosporina',
    'HOSPITAL_ACQUIRED',
    'Abdominal',
    'dr.carlos.mendes',
    now(),
    now()
  );
