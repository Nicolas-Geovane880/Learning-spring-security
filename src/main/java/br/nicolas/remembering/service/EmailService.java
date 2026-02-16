package br.nicolas.remembering.service;

import br.nicolas.remembering.constant.ErrorMessage;
import br.nicolas.remembering.exception.EmailAlreadyInUseException;
import br.nicolas.remembering.repository.StudentRepository;
import br.nicolas.remembering.repository.TeacherRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service @AllArgsConstructor
public class EmailService {

    private TeacherService teacherService;

    private StudentService studentService;

    public boolean checkIfEmailIsAlreadyInUse (String email) {
        return studentService.existsByEmail(email) || teacherService.existsByEmail(email);
    }
}
