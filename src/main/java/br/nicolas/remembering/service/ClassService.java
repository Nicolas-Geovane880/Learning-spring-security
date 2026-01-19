package br.nicolas.remembering.service;

import br.nicolas.remembering.entity.Class;
import br.nicolas.remembering.entity.Teacher;
import br.nicolas.remembering.exceptions.InvalidRequestValueException;
import br.nicolas.remembering.repository.ClassRepository;
import br.nicolas.remembering.repository.TeacherRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.NoSuchElementException;

@Service @AllArgsConstructor
public class ClassService {

    private ClassRepository repository;

    private TeacherService teacherService;

    public Class findById (Long id) {
        if (id == null || id <= 0) throw new InvalidRequestValueException("Id is invalid");

        return repository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Class not found"));
    }

    public Class save (Class clasS, Long teacherId) {
        Teacher foundTeacher = teacherService.findById(teacherId);
        clasS.setTeacher(foundTeacher);

        return repository.save(clasS);
    }
}
