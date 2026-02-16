package br.nicolas.remembering.entity_mock;

import br.nicolas.remembering.entity.Class;
import br.nicolas.remembering.entity.Student;
import br.nicolas.remembering.entity.Teacher;
import br.nicolas.remembering.enums.Discipline;
import br.nicolas.remembering.enums.Shift;

public class EntityMock {

    public Teacher getTeacherMock () {
        return Teacher.builder()
                .name("Teacher name test")
                .email("TeacherTest@mail.com")
                .password("TeacherTest123")
                .build();
    }

    public Class getClassMock (Teacher teacher) {
        return Class.builder()
                .discipline(Discipline.MATH)
                .year(2022)
                .shift(Shift.EVENING)
                .teacher(teacher)
                .build();
    }

    public Student getStudentMock (Class classes) {
        return Student.builder()
                .name("Student name test")
                .email("StudentTest@mail.com")
                .password("StudentTest123")
                .studentClass(classes)
                .build();
    }
}
