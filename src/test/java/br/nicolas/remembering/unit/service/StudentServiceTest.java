package br.nicolas.remembering.unit.service;

import br.nicolas.remembering.entity.Student;
import br.nicolas.remembering.repository.StudentRepository;
import br.nicolas.remembering.service.StudentService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class StudentServiceTest {

    @Mock
    private StudentRepository repository;

    @InjectMocks
    private StudentService service;

    @Test
    void shouldCalculateFinalGrade () {
        Student student = Student.builder()
                .grades(List.of(10.0, 10.0, 10.0))
                .build();

        service.calculateFinalGrade(student);

        Assertions.assertEquals(10.0, student.getFinalGrade());
    }

    @Test
    void shouldSetIsPassed () {
        Student student = Student.builder()
                .grades(List.of(10.0, 10.0, 10.0))
                .build();

        service.checkIfIsPassed(student);

        Assertions.assertTrue(student.isPassed());
    }
}