package br.nicolas.remembering.service;

import br.nicolas.remembering.constant.ConstantValues;
import br.nicolas.remembering.constant.ErrorMessage;
import br.nicolas.remembering.dto.teacher.TeacherCreateDTO;
import br.nicolas.remembering.dto.teacher.TeacherResponseDTO;
import br.nicolas.remembering.dto.teacher.TeacherUpdateDTO;
import br.nicolas.remembering.entity.Teacher;
import br.nicolas.remembering.mapper.TeacherMapper;
import br.nicolas.remembering.repository.TeacherRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.NoSuchElementException;

@Service @AllArgsConstructor
public class TeacherService {

    private TeacherRepository repository;

    private TeacherMapper mapper;

    @Transactional (readOnly = true)
    public TeacherResponseDTO getTeacherById (Long teacherId) {
        Teacher foundTeacher = findTeacherById(teacherId);

        return mapper.parseToResponse(foundTeacher);
    }

    @Transactional (readOnly = true)
    public Teacher findTeacherById(Long teacherId) {
        return repository.findById(teacherId)
                .orElseThrow(() -> new NoSuchElementException(ErrorMessage.TEACHER_NOT_FOUND));
    }

    @Transactional
    public TeacherResponseDTO save (TeacherCreateDTO createDTO) {
        Teacher teacher = mapper.parseToEntity(createDTO);

        return mapper.parseToResponse(repository.save(teacher));
    }

    @Transactional
    public TeacherResponseDTO update (TeacherUpdateDTO updateDTO, Teacher currentTeacher) {
        mapper.updateEntityFromDTO(updateDTO, currentTeacher);

        return mapper.parseToResponse(currentTeacher);
    }

    @Transactional
    public void delete(Long teacherId) {
        if (repository.existsById(teacherId)) {
            repository.deleteById(teacherId);
        }
        else throw new NoSuchElementException(ErrorMessage.TEACHER_NOT_FOUND);
    }

    public boolean checkIfTeacherCanLessonMoreClasses (int classCountByTeacher) {
        return classCountByTeacher < ConstantValues.MAX_CLASSES_TEACHER_CAN_LESSON;
    }

    @Transactional (readOnly = true)
    public boolean existsByEmail (String email) {
        return repository.existsByEmail(email);
    }

    @Transactional (readOnly = true)
    public boolean existsById (Long teacherId) {
        return repository.existsById(teacherId);
    }
}
