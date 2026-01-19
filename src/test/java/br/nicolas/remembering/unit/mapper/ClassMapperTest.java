package br.nicolas.remembering.unit.mapper;

import br.nicolas.remembering.dto.clasS.ClassResponseDTO;
import br.nicolas.remembering.entity.Class;
import br.nicolas.remembering.entity_mock.EntityMock;
import br.nicolas.remembering.mapper.ClassMapper;
import br.nicolas.remembering.repository.ClassRepository;
import br.nicolas.remembering.service.ClassService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Optional;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ClassMapperTest {

    private final ClassMapper mapper = Mappers.getMapper(ClassMapper.class);

    @Mock
    private ClassRepository repository;

    @InjectMocks
    private ClassService service;

    private final EntityMock entityMock = new EntityMock();

    @Test
    void shouldMapToClassResponse () {
        Class existing = entityMock.getClassMock();
        existing.setId(1L);

        when(repository.findById(1L)).thenReturn(Optional.of(existing));

        Class found = service.findById(1L);

        ClassResponseDTO classResponse = mapper.parseToResponse(found);

        Assertions.assertEquals(found.getName(), classResponse.getName());
        Assertions.assertEquals(found.getTeacher().getName(), classResponse.getTeacherName());
        Assertions.assertEquals(found.getShift().getShiftStr(), classResponse.getShift());
        Assertions.assertEquals(found.getDiscipline().getDisciplineStr(), classResponse.getDiscipline());
        Assertions.assertEquals(5, classResponse.getStudentsNumber());
    }
}







