package br.nicolas.remembering.service;

import br.nicolas.remembering.entity.Student;
import br.nicolas.remembering.repository.StudentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service @AllArgsConstructor
public class StudentService {

    private StudentRepository repository;

    public void calculateFinalGrade (Student student) {
        student.calculateFinalGrade();
    }

    public void checkIfIsPassed (Student student) {
        student.checkIfIsPassed();
    }
}
