package br.nicolas.remembering.unit.service;

import br.nicolas.remembering.entity_mock.DTOMock;
import br.nicolas.remembering.entity_mock.EntityMock;
import br.nicolas.remembering.mapper.StudentMapper;
import br.nicolas.remembering.repository.StudentRepository;
import br.nicolas.remembering.service.ClassService;
import br.nicolas.remembering.service.StudentService;
import br.nicolas.remembering.service.EmailService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.NoSuchElementException;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class StudentServiceTest {

    @InjectMocks
    private StudentService service;

    @Mock
    private StudentRepository repository;

    @Test
    void shouldThrowExceptionIfStudentNotFound () {
        when(repository.findById(1L)).thenReturn(Optional.empty());

        Assertions.assertThrows(NoSuchElementException.class, () -> service.findStudentById(1L));
    }
}