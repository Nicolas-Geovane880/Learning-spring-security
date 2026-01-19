package br.nicolas.remembering.unit.service;

import br.nicolas.remembering.entity.Teacher;
import br.nicolas.remembering.repository.TeacherRepository;
import br.nicolas.remembering.service.TeacherService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;
import java.util.Optional;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith (MockitoExtension.class)
class TeacherServiceTest {

    @Mock
    private TeacherRepository repository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private TeacherService service;

    @Test
    void shouldSaveTeacher () {
        Teacher requested = Teacher.builder()
                .name("Teacher test")
                .email("TeacherTest@mail")
                .password("TeacherTest123")
                .build();

        Teacher toSave = Teacher.builder()
                .id(1L)
                .name("Teacher test")
                .email("TeacherTest@mail")
                .password("TeacherTest123")
                .build();

        toSave.setId(1L);

        when(repository.save(requested)).thenReturn(toSave);

        Teacher saved = service.save(requested);

        Assertions.assertEquals(toSave.getId(), saved.getId());
        Assertions.assertEquals(toSave.getName(), saved.getName());
    }

    @Test
    void shouldFindById () {
        Teacher existing = Teacher.builder()
                .id(1L)
                .name("Teacher test")
                .email("TeacherTest@mail")
                .password("TeacherTest123")
                .build();

        when(repository.findById(1L)).thenReturn(Optional.of(existing));

        Teacher found = service.findById(1L);

        verify(repository, times(1)).findById(1L);

        Assertions.assertEquals(existing.getId(), found.getId());
        Assertions.assertEquals(existing.getName(), found.getName());
    }

    @Test
    void shouldUpdateTeacherIfExists () {
        Teacher existing = Teacher.builder()
                .id(1L)
                .name("Teacher test")
                .email("TeacherTest@mail")
                .password("TeacherTest123")
                .build();

        Teacher toUpdate = Teacher.builder()
                .id(1L)
                .name("Teacher updated test")
                .email("TeacherTestUpdated@mail")
                .password("TeacherTest123")
                .build();

        when(repository.findById(1L)).thenReturn(Optional.of(existing));
        when(repository.save(any(Teacher.class))).thenReturn(toUpdate);

        Teacher updated = service.update(1L, toUpdate);

        Assertions.assertEquals(1L, updated.getId());
        Assertions.assertEquals("Teacher updated test", updated.getName());
        Assertions.assertEquals("TeacherTestUpdated@mail", updated.getEmail());
    }
}