package br.nicolas.remembering.util;

import br.nicolas.remembering.security.model_wrapper.TeacherDetailsImpl;
import br.nicolas.remembering.security.model_wrapper.UserDetailsImpl;
import br.nicolas.remembering.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.NoSuchElementException;

@Component (value = "securityRoleFilterUtil")
public class SecurityRoleFilterUtil {

    @Autowired
    private StudentService studentService;

    public boolean isIdOwner (Authentication authentication, Long userId) {
        if (authentication == null || userId == null) {
            return false;
        }

        Object principal = authentication.getPrincipal();

        if (!(principal instanceof UserDetailsImpl user)) {
            return false;
        }

        return user.getId().equals(userId);
    }

    public boolean ifTeacherLessonStudent (Authentication authentication, Long studentId) {
        if (authentication == null || studentId == null) {
            return false;
        }

        Object principal = authentication.getPrincipal();

        if (!(principal instanceof TeacherDetailsImpl teacherDetails)) {
            return false;
        }

        try {
            Long teacherId = studentService.findTeacherIdByStudentId(studentId);

            return teacherDetails.getId().equals(teacherId);

        } catch (NoSuchElementException e) {
            return false;
        }
    }
}
