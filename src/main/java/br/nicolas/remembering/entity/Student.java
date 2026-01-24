package br.nicolas.remembering.entity;

import br.nicolas.remembering.enums.UserRoles;
import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@AllArgsConstructor @NoArgsConstructor @Getter
@Setter @Builder @Entity @Table (name = "student")
public class Student{

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn (name = "class_id", nullable = false)
    private Class studentClass;

    @Column (name = "student_name", nullable = false)
    private String name;

    @Column (name = "student_email", nullable = false, unique = true)
    private String email;

    @Column (name = "student_password", nullable = false)
    private String password;

    @Column (name = "student_grades")
    private String grades;

    @Column (name = "student_final_grade")
    private Double finalGrade;

    @Column (name = "is_student_passed")
    private Boolean isPassed;

    @Builder.Default
    @Column (name = "student_role", nullable = false)
    @Enumerated (value = EnumType.STRING)
    private final UserRoles role = UserRoles.STUDENT;

    private Double calculateStudentFinalGrade(List<Double> grades) {
        double finalGrade = grades.stream()
                .mapToDouble(Double::doubleValue)
                .average()
                .orElse(0);

        this.grades = grades.toString();
        this.finalGrade = finalGrade;

        return finalGrade;
    }

    public void calculateAndCheckIfStudentIsPassed(List<Double> grades) {
        Double finalGrade = calculateStudentFinalGrade(grades);

        this.isPassed = finalGrade >= 7;
    }
}
