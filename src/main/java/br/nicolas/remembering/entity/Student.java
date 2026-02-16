package br.nicolas.remembering.entity;

import br.nicolas.remembering.enums.UserRoles;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.validator.internal.util.stereotypes.Lazy;

import java.util.List;

import static br.nicolas.remembering.constant.ConstantValues.MINIMUM_GRADE_TO_PASS;

@AllArgsConstructor @NoArgsConstructor @Getter
@Setter @SuperBuilder @Entity @Table (name = "student")
public class Student extends User{

    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn (name = "class_id", nullable = false)
    private Class studentClass;

    @Column (name = "student_grades")
    private String grades;

    @Column (name = "student_final_grade")
    private Double finalGrade;

    @Column (name = "is_student_passed")
    private Boolean isPassed;

    @Builder.Default
    @Column (name = "student_role")
    @Enumerated (value = EnumType.STRING)
    private final UserRoles role = UserRoles.STUDENT;

    public void setStudentGradesStatus(List<Double> grades, double finalGrade) {

        this.grades = grades.toString();
        this.finalGrade = finalGrade;
        this.isPassed = finalGrade >= MINIMUM_GRADE_TO_PASS;
    }
}
