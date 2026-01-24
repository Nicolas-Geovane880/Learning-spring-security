package br.nicolas.remembering.service;

import br.nicolas.remembering.entity.Class;
import br.nicolas.remembering.entity.Student;
import br.nicolas.remembering.repository.StudentRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.NoSuchElementException;

@Service @AllArgsConstructor
public class StudentService {

    private StudentRepository repository;

    private ClassService classService;

    private PasswordEncoder passwordEncoder;

    private EmailValidatorService emailValidatorService;


    public Student save (Student student, Long classId) {
        emailValidatorService.checkIfEmailIsAlreadyInUse(student.getEmail());

        Class foundClass = classService.findById(classId);
        student.setStudentClass(foundClass);

        String hashPassword = passwordEncoder.encode(student.getPassword());
        student.setPassword(hashPassword);

        return repository.save(student);
    }


    public Student findById (Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Student not found"));
    }


    @Transactional
    public Student updateFromRequest (Long id, Student updatedStudent) {
        Student found = findById(id);

        String newName = updatedStudent.getName();
        String newEmail = updatedStudent.getEmail();
        String newPassword = updatedStudent.getPassword();

        if (newEmail != null && !newEmail.equals(found.getEmail())) {
            emailValidatorService.checkIfEmailIsAlreadyInUse(newEmail);
            found.setEmail(newEmail);
        }
        if (newPassword != null && passwordEncoder.matches(newPassword, found.getPassword())) {
            found.setPassword(passwordEncoder.encode(newPassword));
        }
        if (newName != null) found.setName(newName);

        return repository.save(found);
    }

    public void update (Student student) {
        repository.save(student);
    }
}
