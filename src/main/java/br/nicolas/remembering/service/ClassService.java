package br.nicolas.remembering.service;

import br.nicolas.remembering.entity.Class;
import br.nicolas.remembering.entity.Teacher;
import br.nicolas.remembering.repository.ClassRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.NoSuchElementException;

@Service @AllArgsConstructor
public class ClassService {

    private ClassRepository repository;

    private TeacherService teacherService;


    public Class findById (Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Class not found"));
    }


    public Class save (Class classes, Long teacherId) {
        Teacher foundTeacher = teacherService.findById(teacherId);
        classes.setTeacher(foundTeacher);

        return repository.save(classes);
    }
}
