package br.nicolas.remembering.service;

import br.nicolas.remembering.constant.ErrorMessage;
import br.nicolas.remembering.dto.student.StudentCreateDTO;
import br.nicolas.remembering.dto.student.StudentResponseDTO;
import br.nicolas.remembering.dto.student.StudentUpdateDTO;
import br.nicolas.remembering.entity.Class;
import br.nicolas.remembering.entity.Student;
import br.nicolas.remembering.mapper.StudentMapper;
import br.nicolas.remembering.repository.StudentRepository;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

@Service @AllArgsConstructor
public class StudentService {

    private StudentRepository repository;

    private StudentMapper mapper;

    @Transactional (readOnly = true)
    public StudentResponseDTO getStudentById (Long studentId) {
        Student foundStudent = findStudentById(studentId);

        return mapper.parseToResponse(foundStudent);
    }

    @Transactional (readOnly = true)
    public Student findStudentById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new NoSuchElementException(ErrorMessage.STUDENT_NOT_FOUND));
    }

    @Transactional
    public StudentResponseDTO save (StudentCreateDTO dto, Class studentClass) {
        Student student = mapper.parseToEntity(dto);

        studentClass.addStudent(student);

        return mapper.parseToResponse(repository.save(student));
    }

    @Transactional
    public StudentResponseDTO update (StudentUpdateDTO dto, Student currentStudent) {
        mapper.updateEntityFromDTO(dto, currentStudent);

        return mapper.parseToResponse(currentStudent);
    }

    @Transactional
    public void delete(@Positive Long studentId) {
        if (repository.existsById(studentId)) {
            repository.deleteById(studentId);
        }
        else throw new NoSuchElementException(ErrorMessage.STUDENT_NOT_FOUND);
    }

    @Transactional (readOnly = true)
    public Long findTeacherIdByStudentId (Long id) {
        return repository.findTeacherIdByStudentId(id);
    }

    public Page<StudentResponseDTO> getAllPassedStudentsByClassId (Long id, int page, int size ) {
        return repository.findPassedStudentsByClassId(id, PageRequest.of(page, size))
                .map(mapper::parseToResponse);
    }

    @Transactional (readOnly = true)
    public boolean existsByEmail (String email) {
        return repository.existsByEmail(email);
    }

    @Transactional (readOnly = true)
    public void removeClassesFromStudentsBeforeDelete (Long classId) {
        repository.removeClassesFromStudents(classId);
    }
}
