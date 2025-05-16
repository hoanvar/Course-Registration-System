CREATE TABLE IF NOT EXISTS course
(
    id              UUID PRIMARY KEY,
    course_id           VARCHAR(255)        NOT NULL,
    course_name         VARCHAR(255)        NOT NULL,
    description         VARCHAR(255) NOT NULL
    );

-- Thêm nhiều bản ghi vào bảng course nếu id chưa tồn tại

INSERT INTO course (id, course_id, course_name, description)
SELECT 'f47c7a0b-20ed-4d5a-b9c0-12fdc8e69e33',
       'CSE101',
       'Introduction to Computer Science',
       'Basic course on computer science and programming fundamentals'
    WHERE NOT EXISTS (SELECT 1
                  FROM course
                  WHERE id = 'f47c7a0b-20ed-4d5a-b9c0-12fdc8e69e33');

INSERT INTO course (id, course_id, course_name, description)
SELECT 'e42b8a9d-6c9b-45be-8356-bf7c1d5360c7',
       'MATH101',
       'Calculus I',
       'Introduction to differential and integral calculus'
    WHERE NOT EXISTS (SELECT 1
                  FROM course
                  WHERE id = 'e42b8a9d-6c9b-45be-8356-bf7c1d5360c7');

INSERT INTO course (id, course_id, course_name, description)
SELECT 'a4d8d8c9-7e27-4d1f-a5bc-f4316a18f62e',
       'PHY101',
       'Physics I',
       'Fundamentals of mechanics and thermodynamics'
    WHERE NOT EXISTS (SELECT 1
                  FROM course
                  WHERE id = 'a4d8d8c9-7e27-4d1f-a5bc-f4316a18f62e');
