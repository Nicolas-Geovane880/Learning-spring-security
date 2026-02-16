
CREATE TABLE teacher (
    id BIGINT AUTO_INCREMENT,
    email VARCHAR(255) NOT NULL UNIQUE,
    name VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    teacher_role VARCHAR(20) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE class (
    id BIGINT AUTO_INCREMENT,
    class_name VARCHAR(255) NOT NULL,
    class_year INTEGER NOT NULL,
    class_discipline VARCHAR(50) NOT NULL,
    class_shift VARCHAR(20) NOT NULL,
    teacher_id BIGINT NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT FK_CLASS_TEACHER FOREIGN KEY (teacher_id) REFERENCES teacher(id)
);

CREATE TABLE student (
    id BIGINT AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    student_role VARCHAR(20) NOT NULL,
    student_final_grade DOUBLE,
    student_grades VARCHAR(20),
    is_student_passed BOOLEAN,
    class_id BIGINT,
    PRIMARY KEY (id),
    CONSTRAINT FK_STUDENT_CLASS FOREIGN KEY (class_id) REFERENCES class(id)
);