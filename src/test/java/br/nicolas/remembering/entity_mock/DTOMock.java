package br.nicolas.remembering.entity_mock;

import br.nicolas.remembering.dto.classes.ClassCreateDTO;
import br.nicolas.remembering.dto.classes.ClassUpdateDTO;
import br.nicolas.remembering.dto.student.GradeDTO;
import br.nicolas.remembering.dto.student.StudentCreateDTO;
import br.nicolas.remembering.dto.teacher.TeacherCreateDTO;
import java.util.List;

public class DTOMock {

    public ClassCreateDTO getClassCreateDTO () {
        return ClassCreateDTO.builder()
                .discipline("MATH")
                .year(2022)
                .shift("EVENING")
                .teacherId(1L)
                .build();
    }

    public ClassUpdateDTO getClassUpdateDTO () {
        return ClassUpdateDTO.builder()
                .discipline("ENGLISH")
                .year(2023)
                .shift("AFTERNOON")
                .teacherId(1L)
                .build();
    }

    public StudentCreateDTO getStudentCreateDTO () {
        return StudentCreateDTO.builder()
                .name("Student test name")
                .email("StudentTest@mail.com")
                .password("StudentTest123")
                .classId(1L)
                .build();
    }

    public TeacherCreateDTO getTeacherCreateDTO () {
        return TeacherCreateDTO.builder()
                .name("Teacher test name")
                .email("TeacherTest@mail.com")
                .password("TeacherTest123")
                .build();
    }

    public GradeDTO getGradeDTO () {
        return GradeDTO.builder()
                .grades(List.of(10.0, 10.0, 10.0))
                .studentId(1L)
                .build();
    }
}
