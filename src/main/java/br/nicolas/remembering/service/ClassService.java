package br.nicolas.remembering.service;

import br.nicolas.remembering.constant.ErrorMessage;
import br.nicolas.remembering.dto.classes.ClassCreateDTO;
import br.nicolas.remembering.dto.classes.ClassResponseDTO;
import br.nicolas.remembering.dto.classes.ClassUpdateDTO;
import br.nicolas.remembering.entity.Class;
import br.nicolas.remembering.entity.Teacher;
import br.nicolas.remembering.enums.Discipline;
import br.nicolas.remembering.mapper.ClassMapper;
import br.nicolas.remembering.repository.ClassRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.NoSuchElementException;

@Service @AllArgsConstructor
public class ClassService {

    private ClassRepository repository;

    private ClassMapper mapper;

    @Transactional (readOnly = true)
    public ClassResponseDTO getClassById (Long classId) {
        Class foundClass = findClassById(classId);

        return mapper.parseToResponse(foundClass);
    }

    @Transactional (readOnly = true)
    public Class findClassById(Long classId) {
        return repository.findById(classId)
                .orElseThrow(() -> new NoSuchElementException(ErrorMessage.CLASS_NOT_FOUND));
    }

    @Transactional
    public ClassResponseDTO save (ClassCreateDTO createDTO, Teacher teacherClass) {
        Class classes = mapper.parseToEntity(createDTO);
        classes.setTeacher(teacherClass);

        return mapper.parseToResponse(repository.save(classes));
    }

    @Transactional
    public ClassResponseDTO update (ClassUpdateDTO updateDTO, Class currentClass) {
        mapper.updateEntityFromDTO(updateDTO, currentClass);

        return mapper.parseToResponse(currentClass);
    }

    @Transactional
    public void delete(Long classId) {
        if (existsById(classId)) {
            repository.deleteById(classId);
        }
        else throw new NoSuchElementException(ErrorMessage.CLASS_NOT_FOUND);
    }

    @Transactional (readOnly = true)
    public boolean existsById (Long classId) {
        return repository.existsById(classId);
    }

    @Transactional (readOnly = true)
    public Page<ClassResponseDTO> getAllClassesByDiscipline (String disciplineStr, int page, int size) {
        Discipline discipline = Discipline.fromString(disciplineStr);

        return repository.getAllClassesByDiscipline(discipline.name(), PageRequest.of(page, size))
                .map(mapper::parseToResponse);
    }

    @Transactional (readOnly = true)
    public int countClassesByTeacherId (Long teacherId) {
        return repository.countByTeacherId(teacherId);
    }

    @Transactional (readOnly = true)
    public void removeTeacherFromClassesBeforeDelete(Long teacherId) {
        repository.removeTeacherFromClasses(teacherId);
    }
}
