package br.nicolas.remembering.service;

import br.nicolas.remembering.constant.ErrorMessage;
import br.nicolas.remembering.dto.classes.ClassCreateDTO;
import br.nicolas.remembering.dto.classes.ClassResponseDTO;
import br.nicolas.remembering.dto.classes.ClassUpdateDTO;
import br.nicolas.remembering.dto.student.StudentCreateDTO;
import br.nicolas.remembering.dto.student.StudentResponseDTO;
import br.nicolas.remembering.dto.student.StudentUpdateDTO;
import br.nicolas.remembering.dto.teacher.TeacherCreateDTO;
import br.nicolas.remembering.dto.teacher.TeacherResponseDTO;
import br.nicolas.remembering.dto.teacher.TeacherUpdateDTO;
import br.nicolas.remembering.entity.Class;
import br.nicolas.remembering.entity.Student;
import br.nicolas.remembering.entity.Teacher;
import br.nicolas.remembering.exception.EmailAlreadyInUseException;
import br.nicolas.remembering.exception.TeacherOverpassClassLimitException;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

@Service @AllArgsConstructor
public class AcademicOrchestrator {

    private ClassService classService;

    private TeacherService teacherService;

    private StudentService studentService;

    private EmailService emailService;

    // ---- Save methods
    @Transactional
    public ClassResponseDTO saveClassWithTeacher (ClassCreateDTO createDTO) {
        Teacher foundTeacher = teacherService.findTeacherById(createDTO.getTeacherId());

        if (teacherService.checkIfTeacherCanLessonMoreClasses(classService.countClassesByTeacherId(createDTO.getTeacherId()))) {
            return classService.save(createDTO, foundTeacher);
        }
        else throw new TeacherOverpassClassLimitException(ErrorMessage.MAX_CLASSES_TEACHER_CAN_LESSON);
    }

    @Transactional
    public StudentResponseDTO saveStudentWithClass (StudentCreateDTO createDTO) {
        if (emailService.checkIfEmailIsAlreadyInUse(createDTO.getEmail())) throw new EmailAlreadyInUseException(ErrorMessage.EMAIL_ALREADY_IN_USE);

        Class foundClass = classService.findClassById(createDTO.getClassId());

        return studentService.save(createDTO, foundClass);
    }

    @Transactional
    public TeacherResponseDTO saveTeacher (TeacherCreateDTO createDTO) {
        if (emailService.checkIfEmailIsAlreadyInUse(createDTO.getEmail())) throw new EmailAlreadyInUseException(ErrorMessage.EMAIL_ALREADY_IN_USE);

        return teacherService.save(createDTO);
    }

    // ---- Update methods
    @Transactional
    public StudentResponseDTO updateStudent (Long studentId, StudentUpdateDTO updateDTO) {
        Student currentStudent = studentService.findStudentById(studentId);

        if (updateDTO.getEmail() != null && !currentStudent.getEmail().equals(updateDTO.getEmail())) {
            emailService.checkIfEmailIsAlreadyInUse(updateDTO.getEmail());
        }

        if (updateDTO.getClassId() != null) {
            Class currentStudentClass = currentStudent.getStudentClass();
            if (!currentStudentClass.getId().equals(updateDTO.getClassId())) {
                Class newStudentClass = classService.findClassById(updateDTO.getClassId());
                currentStudentClass.changeStudentClass(currentStudent, newStudentClass);
            }
        }

        return studentService.update(updateDTO, currentStudent);
    }

    @Transactional
    public ClassResponseDTO updateClass (Long classId, ClassUpdateDTO updateDTO) {
        Class currentClass = classService.findClassById(classId);

        if (updateDTO.getTeacherId() != null) {
            Teacher newClassTeacher= teacherService.findTeacherById(updateDTO.getTeacherId());
            currentClass.setTeacher(newClassTeacher);
        }

        return classService.update(updateDTO, currentClass);
    }

    @Transactional
    public TeacherResponseDTO updateTeacher (Long teacherId, TeacherUpdateDTO updateDTO) {
        Teacher currentTeacher = teacherService.findTeacherById(teacherId);

        if (updateDTO.getEmail() != null && !currentTeacher.getEmail().equals(updateDTO.getEmail())) {
            emailService.checkIfEmailIsAlreadyInUse(updateDTO.getEmail());
        }

        if (updateDTO.getEmail() != null) {
            emailService.checkIfEmailIsAlreadyInUse(updateDTO.getEmail());
        }

        return teacherService.update(updateDTO, currentTeacher);
    }

    @Transactional (readOnly = true)
    public Page<StudentResponseDTO> getAllPassedStudentsByClassId (Long classId, int page, int size) {
        if (classService.existsById(classId)) {
            return studentService.getAllPassedStudentsByClassId(classId, page, size);
        }

        throw new NoSuchElementException(ErrorMessage.CLASS_NOT_FOUND);
    }

    // ---- Delete methods
    @Transactional
    public void deleteTeacher (Long teacherId) {
        if (teacherService.existsById(teacherId)) {
            classService.removeTeacherFromClassesBeforeDelete(teacherId);
            teacherService.delete(teacherId);
        }
        else throw new NoSuchElementException(ErrorMessage.TEACHER_NOT_FOUND);
    }

    @Transactional
    public void deleteClass (Long classId) {
        if (classService.existsById(classId)) {
            studentService.removeClassesFromStudentsBeforeDelete(classId);
            classService.delete(classId);
        }
        else throw new NoSuchElementException(ErrorMessage.CLASS_NOT_FOUND);
    }
}
