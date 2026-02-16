package br.nicolas.remembering.service;

import br.nicolas.remembering.constant.ErrorMessage;
import br.nicolas.remembering.dto.student.GradeDTO;
import br.nicolas.remembering.entity.Student;
import br.nicolas.remembering.exception.InvalidGradesException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Service @AllArgsConstructor
public class GradeService {

    private StudentService studentService;

    @Transactional
    public void setStudentGrades (GradeDTO gradesDTO) {
        Student foundStudent = studentService.findStudentById(gradesDTO.getStudentId());

        if (foundStudent.getGrades() != null) throw new InvalidGradesException(ErrorMessage.STUDENT_ALREADY_HAS_GRADE);

        List<Double> grades = gradesDTO.getGrades().stream()
                .map(o -> (Double) o)
                .toList();

        Double finalGrade = calculateFinalGrade(foundStudent, grades);

        foundStudent.setStudentGradesStatus(grades, finalGrade);
    }

    public Double calculateFinalGrade(Student student, List<Double> grades) {
        double finalGrade = grades.stream()
                .mapToDouble(Double::doubleValue)
                .average()
                .orElse(0);

        return BigDecimal.valueOf(finalGrade)
                .setScale(2, RoundingMode.HALF_UP)
                .doubleValue();
    }
}
