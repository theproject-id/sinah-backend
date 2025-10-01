-- Inserir 1 usuário default para referência nas notificações
INSERT INTO users (uuid, username, first_name, last_name, email, password, role, created_at, updated_at) VALUES
  ('00000000-0000-0000-0000-000000000001', 'default.user', 'Usuário', 'Default', 'default@hospital.com', '$2a$10$EixZaYVK1fsbw1ZfbX3OXePaWxn96p36r5h4bT7y1l1r1r1r1r1r1', 'ADMIN', now(), now());


INSERT INTO notifications (
    uuid, title, description, type, priority,
    patient_id, patient_name, patient_room, patient_bed,
    created_by, created_by_name,
    due_date, notes, additional_data
) VALUES
(
    '11111111-1111-1111-1111-111111111111',
    'Medication reminder',
    'Administer 5mg of Morphine at 8:00 PM',
    'REMINDER',
    'HIGH',
    '22222222-2222-2222-2222-222222222222',
    'John Doe',
    'Room 101',
    'Bed A',
    '33333333-3333-3333-3333-333333333333',
    'Dr. Alice Smith',
    NOW() + INTERVAL '2 hours',
    'Check patient vitals before administering',
    '{"medication":"Morphine","dosage":"5mg"}'
),
(
    '44444444-4444-4444-4444-444444444444',
    'Lab results available',
    'Blood test results are ready for review',
    'INFO',
    'MEDIUM',
    '55555555-5555-5555-5555-555555555555',
    'Mary Johnson',
    'Room 202',
    'Bed B',
    '66666666-6666-6666-6666-666666666666',
    'Nurse Bob',
    NULL,
    NULL,
    '{"lab":"Blood Test","status":"Completed"}'
),
(
    '77777777-7777-7777-7777-777777777777',
    'Emergency alert',
    'Patient showing signs of cardiac arrest',
    'ALERT',
    'CRITICAL',
    '88888888-8888-8888-8888-888888888888',
    'James Wilson',
    'ICU 3',
    'Bed C',
    '99999999-9999-9999-9999-999999999999',
    'System',
    NOW() + INTERVAL '30 minutes',
    'Immediate response required',
    '{"action":"Call Code Blue","priority":"Critical"}'
);
