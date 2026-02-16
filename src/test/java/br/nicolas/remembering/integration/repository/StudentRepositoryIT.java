package br.nicolas.remembering.integration.repository;

import br.nicolas.remembering.AbstractIntegrationTestConfig;
import br.nicolas.remembering.entity.Class;
import br.nicolas.remembering.entity.Student;
import br.nicolas.remembering.entity.Teacher;
import br.nicolas.remembering.entity_mock.EntityMock;
import br.nicolas.remembering.repository.ClassRepository;
import br.nicolas.remembering.repository.StudentRepository;
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

@ActiveProfiles (value = "test")
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE) // ESSENCIAL
public class StudentRepositoryIT extends AbstractIntegrationTestConfig{

    @Autowired
    private StudentRepository repository;

    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private ClassRepository classRepository;

    private final EntityMock entityMock = new EntityMock();

    @Autowired
    private TestEntityManager manager;

    @BeforeEach
    void setUp () {
        repository.deleteAll();
    }

    @Test
    void shouldSaveStudent () {
        Teacher savedTeacher = teacherRepository.save(entityMock.getTeacherMock());
        Class savedClass = classRepository.save(entityMock.getClassMock(savedTeacher));
        Student savedStudent = repository.save(entityMock.getStudentMock(savedClass));

        manager.flush();
        manager.clear();

        Optional<Student> optStudent = repository.findById(savedStudent.getId());

        Assertions.assertTrue(optStudent.isPresent());
        Assertions.assertEquals(optStudent.get().getStudentClass().getId(), savedClass.getId());
        Assertions.assertEquals(optStudent.get().getEmail(), savedStudent.getEmail());
    }

    @Test
    void shouldFindStudentById () {
        Teacher savedTeacher = teacherRepository.save(entityMock.getTeacherMock());
        Class savedClass = classRepository.save(entityMock.getClassMock(savedTeacher));
        Student savedStudent = repository.save(entityMock.getStudentMock(savedClass));

        manager.flush();
        manager.clear();

        Optional<Student> optStudent = repository.findById(savedClass.getId());

        Assertions.assertTrue(optStudent.isPresent());
        Assertions.assertEquals(optStudent.get().getStudentClass().getId(), savedStudent.getId());
        Assertions.assertEquals(optStudent.get().getEmail(), savedStudent.getEmail());
    }

    @Test
    void shouldUpdateStudent () {
        Teacher savedTeacher = teacherRepository.save(entityMock.getTeacherMock());
        Class savedClass = classRepository.save(entityMock.getClassMock(savedTeacher));
        Student savedStudent = repository.save(entityMock.getStudentMock(savedClass));

        manager.flush();

        String newName = "Updated Name";
        String newEmail = "UpdatedMail@mail";

        savedStudent.setName(newName);
        savedStudent.setEmail(newEmail);

        Student updatedStudent = repository.save(savedStudent);

        manager.flush();
        manager.clear();

        Optional<Student> optStudent = repository.findById(updatedStudent.getId());

        Assertions.assertTrue(optStudent.isPresent());
        Assertions.assertEquals(newName, optStudent.get().getName());
        Assertions.assertEquals(newEmail, optStudent.get().getEmail());
        Assertions.assertEquals(optStudent.get().getStudentClass().getId(), savedClass.getId());
    }

    @Test
    void shouldDeleteStudent () {
        Teacher savedTeacher = teacherRepository.save(entityMock.getTeacherMock());
        Class savedClass = classRepository.save(entityMock.getClassMock(savedTeacher));
        Student savedStudent = repository.save(entityMock.getStudentMock(savedClass));

        manager.flush();

        repository.deleteById(savedStudent.getId());

        manager.flush();
        manager.clear();

        Optional<Student> optStudent = repository.findById(savedClass.getId());

        Assertions.assertTrue(optStudent.isEmpty());
    }
}
