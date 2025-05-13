-- Ensure the 'patient' table exists
CREATE TABLE IF NOT EXISTS student
(
    id              UUID                PRIMARY KEY,
    name            VARCHAR(255)        NOT NULL,
    email           VARCHAR(255) UNIQUE NOT NULL,
    date_of_birth    DATE                NOT NULL,
    address         VARCHAR(255)        NOT NULL,
    phone           VARCHAR(255)        NOT NULL
    );

-- Insert well-known UUIDs for specific student
INSERT INTO student (id, name, email, date_of_birth, address, phone)
SELECT '123e4567-e89b-12d3-a456-426614174000',
       'Vu Lan',
       'vulan@gmail.com',
       '2003-03-19',
       'Ha Dong, Ha Noi',
       '0795306148'
    WHERE NOT EXISTS (SELECT 1
                  FROM student
                  WHERE id = '123e4567-e89b-12d3-a456-426614174000');

INSERT INTO student (id, name, email, date_of_birth, address, phone)
SELECT '123e4567-e89b-12d3-a456-426614174001',
       'Vu Thi Lan',
       'vulan123@gmail.com',
       '2003-03-18',
       'Bac Giang',
       '0295306148'
    WHERE NOT EXISTS (SELECT 1
                  FROM student
                  WHERE id = '123e4567-e89b-12d3-a456-426614174001');
