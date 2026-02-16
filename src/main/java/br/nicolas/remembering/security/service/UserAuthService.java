package br.nicolas.remembering.security.service;

import br.nicolas.remembering.constant.ErrorMessage;
import br.nicolas.remembering.entity.Student;
import br.nicolas.remembering.entity.Teacher;
import br.nicolas.remembering.repository.StudentRepository;
import br.nicolas.remembering.repository.TeacherRepository;
import br.nicolas.remembering.security.model_wrapper.StudentDetailsImpl;
import br.nicolas.remembering.security.model_wrapper.TeacherDetailsImpl;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service @AllArgsConstructor
public class UserAuthService implements UserDetailsService {

    private StudentRepository studentRepository;

    private TeacherRepository teacherRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        Optional<Student> studentByEmail = studentRepository.findByEmail(email);
        if (studentByEmail.isPresent()) return new StudentDetailsImpl(studentByEmail.get());

        Optional<Teacher> teacherByEmail = teacherRepository.findByEmail(email);
        if (teacherByEmail.isPresent()) return new TeacherDetailsImpl(teacherByEmail.get());

        throw new UsernameNotFoundException(ErrorMessage.USER_NOT_FOUND);
    }
}
