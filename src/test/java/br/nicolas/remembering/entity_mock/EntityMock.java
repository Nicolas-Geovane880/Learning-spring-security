package br.nicolas.remembering.entity_mock;

import br.nicolas.remembering.entity.Class;
import br.nicolas.remembering.entity.Student;
import br.nicolas.remembering.entity.Teacher;
import br.nicolas.remembering.enums.Discipline;
import br.nicolas.remembering.enums.Shift;

public class EntityMock {

    public Teacher getTeacherMock () {
        return Teacher.builder()
                .id(1L)
                .name("Teacher name test")
                .email("TeacherTest@mail.com")
                .password("TeacherTest123")
                .build();
    }

    public Class getClassMock () {
        Class clasS = Class.builder()
                .discipline(Discipline.MATH)
                .shift(Shift.EVENING)
                .teacher(getTeacherMock())
                .build();

        for (int i = 0; i < 5; i++) {
            clasS.addStudent(getStudentMock());
        }

        return clasS;
    }

    public Student getStudentMock () {
        return Student.builder()
                .name("Student name test")
                .email("StudentTest@mail.com")
                .password("StudentTest123")
                .build();
    }
}
