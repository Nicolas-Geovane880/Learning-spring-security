package br.nicolas.remembering.service;

import br.nicolas.remembering.entity.Teacher;
import br.nicolas.remembering.exceptions.InvalidRequestValueException;
import br.nicolas.remembering.repository.TeacherRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.NoSuchElementException;

@Service @AllArgsConstructor
public class TeacherService {

    private TeacherRepository repository;

    private PasswordEncoder passwordEncoder;

    public Teacher save (Teacher teacher) {
        String hashPass = passwordEncoder.encode(teacher.getPassword());
        teacher.setPassword(hashPass);

        return repository.save(teacher);
    }

    public Teacher findById (Long id) {
        if (id == null || id <= 0) throw new InvalidRequestValueException("Id is invalid");

        return repository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Teacher not found"));
    }

    public Teacher findByEmail (String email) {
        if (email == null) throw new InvalidRequestValueException("Email is invalid");

        return repository.findByEmail(email)
                .orElseThrow(() -> new NoSuchElementException("Teacher not found"));
    }

    public Teacher update (Long id, Teacher updatedTeacher) {
        if (id == null || id <= 0) throw new InvalidRequestValueException("Id is invalid");

        Teacher found = findById(id);

        String newName = updatedTeacher.getName();
        String newEmail = updatedTeacher.getEmail();
        String newPassword = updatedTeacher.getPassword();

        if (updatedTeacher.getEmail() != null && !found.getEmail().equals(updatedTeacher.getEmail())) {
            if (repository.findByEmail(newEmail).isPresent()) {
                throw new IllegalArgumentException("Email already in use");
            }
            found.setEmail(newEmail);
        }

        if (newName != null) found.setName(newName);
        if (newPassword != null) found.setPassword(passwordEncoder.encode(newPassword));

        return repository.save(found);
    }

    public void deleteById(Long id) {
        if (id == null || id <= 0) throw new InvalidRequestValueException("Id is invalid");

        repository.deleteById(id);
    }
}
