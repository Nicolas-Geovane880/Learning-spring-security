package br.nicolas.remembering.unit.service;

import br.nicolas.remembering.entity.Class;
import br.nicolas.remembering.entity_mock.DTOMock;
import br.nicolas.remembering.entity_mock.EntityMock;
import br.nicolas.remembering.mapper.ClassMapper;
import br.nicolas.remembering.repository.ClassRepository;
import br.nicolas.remembering.service.ClassService;
import br.nicolas.remembering.service.TeacherService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.NoSuchElementException;
import java.util.Optional;
import static org.mockito.Mockito.*;

@ExtendWith (MockitoExtension.class)
class ClassServiceTest {

    @InjectMocks
    private ClassService service;

    @Mock
    private ClassRepository repository;

    @Test
    void shouldThrowExceptionIfClassNotFound () {
        when(repository.findById(1L)).thenReturn(Optional.empty());

        Assertions.assertThrows(NoSuchElementException.class, () -> service.findClassById(1L));
    }
}