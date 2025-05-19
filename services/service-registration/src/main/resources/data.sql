CREATE TABLE IF NOT EXISTS registration
(
    id              UUID PRIMARY KEY,
    id_student VARCHAR(255) NOT NULL,
    id_course  VARCHAR(255) NOT NULL
    );

INSERT INTO registration (id, id_student, id_course)
SELECT '123e4567-e89b-12d3-a456-426614174000',
       '123e4567-e89b-12d3-a456-426614174001',
       '123e4567-e89b-12d3-a456-426614174001'
    WHERE NOT EXISTS (
    SELECT 1 FROM registration
    WHERE id = '123e4567-e89b-12d3-a456-426614174000'
);

INSERT INTO registration (id, id_student, id_course)
SELECT '123e4567-e89b-12d3-a456-426614174001',
       '123e4567-e89b-12d3-a456-426614174002',
       '123e4567-e89b-12d3-a456-426614174002'
    WHERE NOT EXISTS (
    SELECT 1 FROM registration
    WHERE id = '123e4567-e89b-12d3-a456-426614174001'
);

INSERT INTO registration (id, id_student, id_course)
SELECT '123e4567-e89b-12d3-a456-426614174002',
       '123e4567-e89b-12d3-a456-426614174003',
       '123e4567-e89b-12d3-a456-426614174003'
    WHERE NOT EXISTS (
    SELECT 1 FROM registration
    WHERE id = '123e4567-e89b-12d3-a456-426614174002'
);
