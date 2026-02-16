package br.nicolas.remembering.integration.repository;

import br.nicolas.remembering.AbstractIntegrationTestConfig;
import br.nicolas.remembering.entity.Teacher;
import br.nicolas.remembering.entity_mock.EntityMock;
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
public class TeacherRepositoryIT extends AbstractIntegrationTestConfig{

    @Autowired
    private TeacherRepository repository;

    @Autowired
    private TestEntityManager manager;

    private final EntityMock entityMock = new EntityMock();

    @BeforeEach
    void setUp () {
        repository.deleteAll();
    }

    @Test
    void shouldSaveTeacher () {
        Teacher savedTeacher = repository.save(entityMock.getTeacherMock());

        manager.flush();
        manager.clear();

        Optional<Teacher> optTeacher = repository.findById(savedTeacher.getId());

        Assertions.assertTrue(optTeacher.isPresent());
        Assertions.assertEquals(optTeacher.get().getEmail(), savedTeacher.getEmail());
    }

    @Test
    void shouldFindTeacherById () {
        Teacher savedTeacher = repository.save(entityMock.getTeacherMock());

        manager.flush();
        manager.clear();

        Optional<Teacher> optTeacher = repository.findById(savedTeacher.getId());

        Assertions.assertTrue(optTeacher.isPresent());
        Assertions.assertEquals(optTeacher.get().getEmail(), savedTeacher.getEmail());
    }

    @Test
    void shouldUpdateTeacher () {
        Teacher savedTeacher = repository.save(entityMock.getTeacherMock());

        manager.flush();

        String newName = "Updated Name";
        String newEmail = "Updated Email";

        savedTeacher.setName(newName);
        savedTeacher.setEmail(newEmail);

        Teacher updatedTeacher = repository.save(savedTeacher);

        manager.flush();
        manager.clear();

        Optional<Teacher> optTeacher = repository.findById(updatedTeacher.getId());

        Assertions.assertTrue(optTeacher.isPresent());
        Assertions.assertEquals(newName, updatedTeacher.getName());
        Assertions.assertEquals(newEmail, updatedTeacher.getEmail());
    }

    @Test
    void shouldDeleteTeacher () {
        Teacher savedTeacher = repository.save(entityMock.getTeacherMock());

        manager.flush();

        repository.deleteById(savedTeacher.getId());

        manager.flush();
        manager.clear();

        Optional<Teacher> optTeacher = repository.findById(savedTeacher.getId());

        Assertions.assertTrue(optTeacher.isEmpty());
    }
}
