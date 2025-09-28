-- Inserir 5 wards
INSERT INTO wards (uuid, created_at, updated_at) VALUES
  ('f0a9eaa6-7e8a-4d2f-bf31-0d2ab3c99a01', now(), now()),
  ('a9f51cc0-ec9e-4dd5-bdc4-82a7e2a393ac', now(), now()),
  ('c373dd4a-d0ce-4d63-bbb6-7fcdbb5df0ae', now(), now()),
  ('63d18972-b7e1-4297-b09b-93c9028755f7', now(), now()),
  ('8f3e5d8d-68b1-49e2-8f4a-3960f1ebf245', now(), now());

-- Inserir 5 patients
INSERT INTO patients (uuid, created_at, updated_at) VALUES
  ('dc6f77f3-7269-4f38-a6a1-cc69c3db63d6', now(), now()),
  ('3b95e4b8-c6ea-4195-8d3f-5b2bc3453c70', now(), now()),
  ('f5c20cb2-7d1a-42e7-b321-9120ad9f53a3', now(), now()),
  ('d281dd99-16cb-4f5b-b264-dcc5c9393a8a', now(), now()),
  ('cf43d3b7-438b-47d6-98fa-b8c63b3fd1e3', now(), now());

-- Inserir 5 notifications com valores ENUM válidos
INSERT INTO notifications (
    uuid,
    notification_type,
    notification_date,
    status,
    ward_uuid,
    patient_uuid,
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
    'f0a9eaa6-7e8a-4d2f-bf31-0d2ab3c99a01',
    'dc6f77f3-7269-4f38-a6a1-cc69c3db63d6',
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
    'a9f51cc0-ec9e-4dd5-bdc4-82a7e2a393ac',
    '3b95e4b8-c6ea-4195-8d3f-5b2bc3453c70',
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
    'c373dd4a-d0ce-4d63-bbb6-7fcdbb5df0ae',
    'f5c20cb2-7d1a-42e7-b321-9120ad9f53a3',
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
    '63d18972-b7e1-4297-b09b-93c9028755f7',
    'd281dd99-16cb-4f5b-b264-dcc5c9393a8a',
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
    '8f3e5d8d-68b1-49e2-8f4a-3960f1ebf245',
    'cf43d3b7-438b-47d6-98fa-b8c63b3fd1e3',
    'Infecção por Pseudomonas',
    'Pseudomonas aeruginosa',
    'Multirresistente',
    'HOSPITAL_ACQUIRED',
    'Urinária',
    'dr.claudio.souza',
    now(),
    now()
  );
