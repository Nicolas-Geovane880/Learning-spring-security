package br.nicolas.remembering.unit.service;

import br.nicolas.remembering.dto.clasS.ClassResponseDTO;
import br.nicolas.remembering.entity.Class;
import br.nicolas.remembering.entity.Teacher;
import br.nicolas.remembering.entity_mock.EntityMock;
import br.nicolas.remembering.mapper.ClassMapper;
import br.nicolas.remembering.repository.ClassRepository;
import br.nicolas.remembering.repository.TeacherRepository;
import br.nicolas.remembering.service.ClassService;
import br.nicolas.remembering.service.TeacherService;
import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;
import static org.mockito.Mockito.*;

@ExtendWith (MockitoExtension.class)
class ClassServiceTest {

    @Mock
    private ClassRepository repository;

    @Mock
    private TeacherService teacherService;

    @InjectMocks
    private ClassService service;

    private final EntityMock entityMock = new EntityMock();

    @Test
    void shouldSaveClass () {
        Class requested = entityMock.getClassMock();

        Class toSave = entityMock.getClassMock();
        toSave.setId(1L);

        Teacher teacher = toSave.getTeacher();

        when(teacherService.findById(teacher.getId())).thenReturn(teacher);
        when(repository.save(requested)).thenReturn(toSave);

        Class saved = service.save(requested, toSave.getTeacher().getId());

        Assertions.assertEquals(1L, saved.getId());
        Assertions.assertEquals(requested.getTeacher().getName(), saved.getTeacher().getName());
    }

    @Test
    void shouldFindById () {
        Class existing = entityMock.getClassMock();
        existing.setId(1L);

        when(repository.findById(1L)).thenReturn(Optional.of(existing));

        Class found = service.findById(1L);

        Assertions.assertEquals(found.getId(), existing.getId());
        Assertions.assertEquals(found.getName(), existing.getName());
    }
}