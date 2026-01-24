package br.nicolas.remembering.service;

import br.nicolas.remembering.dto.student.GradeDTO;
import br.nicolas.remembering.entity.Student;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service @AllArgsConstructor
public class GradeService {

    private StudentService studentService;


    public void setStudentGrades (GradeDTO gradesDTO) {
        for (Double grade : gradesDTO.getGrades()) {
            if (grade < 0 || grade > 10) throw new IllegalArgumentException("Grades have to be between 0 and 10");
        }

        Student foundStudent = studentService.findById(gradesDTO.getStudentId());

        calculateAndCheckIfStudentIsPassed(foundStudent, gradesDTO.getGrades());

        studentService.update(foundStudent);
    }


    public void calculateAndCheckIfStudentIsPassed (Student student, List<Double> grades) {
        if (student.getGrades() != null) {
            throw new IllegalArgumentException("Student already has grades");
        }
        student.calculateAndCheckIfStudentIsPassed(grades);
    }
}
