package br.nicolas.remembering.unit.service;

import br.nicolas.remembering.repository.TeacherRepository;
import br.nicolas.remembering.service.TeacherService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.NoSuchElementException;
import java.util.Optional;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith (MockitoExtension.class)
class TeacherServiceTest {

    @InjectMocks
    private TeacherService service;

    @Mock
    private TeacherRepository repository;

    @Test
    void shouldThrowExceptionIfTeacherNotFound () {
        when(repository.findById(1L)).thenReturn(Optional.empty());

        Assertions.assertThrows(NoSuchElementException.class, () -> service.findTeacherById(1L));
    }


}