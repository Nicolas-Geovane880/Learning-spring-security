package br.nicolas.remembering.service;

import br.nicolas.remembering.entity.Teacher;
import br.nicolas.remembering.repository.TeacherRepository;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.NoSuchElementException;

@Service @AllArgsConstructor
public class TeacherService {

    private TeacherRepository repository;

    private PasswordEncoder passwordEncoder;

    private EmailValidatorService emailValidatorService;


    @Transactional
    public Teacher save (Teacher teacher) {
        emailValidatorService.checkIfEmailIsAlreadyInUse(teacher.getEmail());

        String hashPassword = passwordEncoder.encode(teacher.getPassword());
        teacher.setPassword(hashPassword);

        return repository.save(teacher);
    }


    @Transactional
    public Teacher findById (Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Teacher not found"));
    }


    public Teacher findByEmail (@NotNull String email) {
        return repository.findByEmail(email)
                .orElseThrow(() -> new NoSuchElementException("Teacher not found"));
    }


    @Transactional
    public Teacher update (Long id, Teacher updatedTeacher) {
        Teacher found = findById(id);

        String newName = updatedTeacher.getName();
        String newEmail = updatedTeacher.getEmail();
        String newPassword = updatedTeacher.getPassword();

        if (updatedTeacher.getEmail() != null && !found.getEmail().equals(updatedTeacher.getEmail())) {
            emailValidatorService.checkIfEmailIsAlreadyInUse(newEmail);
            found.setEmail(newEmail);
        }
        if (newPassword != null && passwordEncoder.matches(newPassword, found.getPassword())) {
            found.setPassword(passwordEncoder.encode(newPassword));
        }
        if (newName != null) found.setName(newName);

        return repository.save(found);
    }


    @Transactional
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
