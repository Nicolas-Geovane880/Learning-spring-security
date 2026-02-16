package br.nicolas.remembering.unit.service;

import br.nicolas.remembering.dto.student.GradeDTO;
import br.nicolas.remembering.entity.Student;
import br.nicolas.remembering.entity_mock.DTOMock;
import br.nicolas.remembering.entity_mock.EntityMock;
import br.nicolas.remembering.exception.InvalidGradesException;
import br.nicolas.remembering.repository.StudentRepository;
import br.nicolas.remembering.service.GradeService;
import br.nicolas.remembering.service.StudentService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.mockito.Mockito.when;

@ExtendWith (MockitoExtension.class)
class GradeServiceTest {

    @InjectMocks
    private GradeService service;

    @Mock
    private StudentService studentService;

    private final EntityMock entityMock = new EntityMock();

    private final DTOMock dtoMock = new DTOMock();

    @Test
    void shouldSetStudentGrades () {
        GradeDTO gradeDTO = dtoMock.getGradeDTO();

        Student existing = entityMock.getStudentMock(null);
        existing.setId(1L);

        when(studentService.findStudentById(gradeDTO.getStudentId())).thenReturn(existing);

        service.setStudentGrades(gradeDTO);

        Assertions.assertEquals(10.0, existing.getFinalGrade());
        Assertions.assertTrue(existing.getIsPassed());
    }

    @Test
    void shouldThrowExceptionIfStudentAlreadyHasGrades () {
        Student existing = entityMock.getStudentMock(null);
        existing.setGrades("grades");
        GradeDTO gradeDTO = dtoMock.getGradeDTO();

        when(studentService.findStudentById(gradeDTO.getStudentId())).thenReturn(existing);

        Assertions.assertThrows(InvalidGradesException.class, () -> service.setStudentGrades(gradeDTO));
    }
}