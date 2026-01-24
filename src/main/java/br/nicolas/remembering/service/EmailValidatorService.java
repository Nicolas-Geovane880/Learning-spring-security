package br.nicolas.remembering.service;

import br.nicolas.remembering.exceptions.EmailAlreadyInUseException;
import br.nicolas.remembering.repository.StudentRepository;
import br.nicolas.remembering.repository.TeacherRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service @AllArgsConstructor
public class EmailValidatorService {

    private StudentRepository studentRepository;

    private TeacherRepository teacherRepository;


    public void checkIfEmailIsAlreadyInUse(String email) {
        if (studentRepository.findByEmail(email).isPresent() || teacherRepository.findByEmail(email).isPresent()) {
            throw new EmailAlreadyInUseException("Email already in use");
        }
    }
}
