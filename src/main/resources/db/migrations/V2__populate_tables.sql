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
