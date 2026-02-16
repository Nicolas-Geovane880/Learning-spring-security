package br.nicolas.remembering.integration.repository;

import br.nicolas.remembering.AbstractIntegrationTestConfig;
import br.nicolas.remembering.entity.Class;
import br.nicolas.remembering.entity.Teacher;
import br.nicolas.remembering.entity_mock.EntityMock;
import br.nicolas.remembering.enums.Discipline;
import br.nicolas.remembering.enums.Shift;
import br.nicolas.remembering.repository.ClassRepository;
import br.nicolas.remembering.repository.TeacherRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

@ActiveProfiles(value = "test")
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE) // ESSENCIAL
public class ClassRepositoryIT extends AbstractIntegrationTestConfig {

    @Autowired
    private ClassRepository repository;

    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private TestEntityManager manager;

    private final EntityMock entityMock = new EntityMock();

    @BeforeEach
    void setUp () {
        repository.deleteAll();
    }

    @Test
    void shouldSaveClass () {
        Teacher savedTeacher = teacherRepository.save(entityMock.getTeacherMock());
        Class savedClass = repository.save(entityMock.getClassMock(savedTeacher));

        manager.flush();
        manager.clear();

        Optional<Class> optClass = repository.findById(savedClass.getId());

        Assertions.assertTrue(optClass.isPresent());
        Assertions.assertEquals(optClass.get().getTeacher().getId(), savedTeacher.getId());
        Assertions.assertEquals(optClass.get().getDiscipline(), savedClass.getDiscipline());
    }

    @Test
    void shouldFindClassById () {
        Teacher savedTeacher = teacherRepository.save(entityMock.getTeacherMock());
        Class savedClass = repository.save(entityMock.getClassMock(savedTeacher));

        manager.flush();
        manager.clear();

        Optional<Class> optClass = repository.findById(savedClass.getId());

        Assertions.assertTrue(optClass.isPresent());
        Assertions.assertEquals(optClass.get().getId(), savedClass.getId());
        Assertions.assertEquals(optClass.get().getDiscipline(), savedClass.getDiscipline());
    }

    @Test
    void shouldUpdateClass () {
        Teacher savedTeacher = teacherRepository.save(entityMock.getTeacherMock());
        Class savedClass = repository.save(entityMock.getClassMock(savedTeacher));

        manager.flush();

        Discipline newDiscipline = Discipline.PHYSIC;
        Shift newShift = Shift.MORNING;

        savedClass.setDiscipline(newDiscipline);
        savedClass.setShift(newShift);

        Class updatedClass = repository.save(savedClass);

        manager.flush();
        manager.clear();

        Optional<Class> optClass = repository.findById(updatedClass.getId());

        Assertions.assertTrue(optClass.isPresent());
        Assertions.assertEquals(optClass.get().getTeacher().getId(), savedTeacher.getId());
        Assertions.assertEquals(optClass.get().getDiscipline(), updatedClass.getDiscipline());
    }

    @Test
    void shouldDeleteClass () {
        Teacher savedTeacher = teacherRepository.save(entityMock.getTeacherMock());
        Class savedClass = repository.save(entityMock.getClassMock(savedTeacher));

        manager.flush();

        repository.deleteById(savedClass.getId());

        manager.flush();
        manager.clear();

        Optional<Class> optClass = repository.findById(savedClass.getId());

        Assertions.assertTrue(optClass.isEmpty());
    }
}
