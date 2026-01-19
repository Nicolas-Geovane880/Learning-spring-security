package br.nicolas.remembering.entity;

import br.nicolas.remembering.enums.UserRoles;
import br.nicolas.remembering.exceptions.StudentHasNoGradesException;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@AllArgsConstructor @NoArgsConstructor @Getter
@Setter @Builder @Entity @Table (name = "student")
public class Student {

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

    @Builder.Default
    @Column (name = "student_grades")
    private List<Double> grades = new ArrayList<>(3);

    @Column (name = "student_final_grade")
    private Double finalGrade;

    @Column (name = "is_student_passed")
    private boolean isPassed;

    @Builder.Default
    @Column (name = "student_role", nullable = false)
    @Enumerated (value = EnumType.STRING)
    private final UserRoles role = UserRoles.STUDENT;

    public double calculateFinalGrade () {
        double finalGrade = this.grades.stream()
                .mapToDouble(Double::doubleValue)
                .average()
                .orElseThrow(() -> new StudentHasNoGradesException("Student has not grades"));

        this.finalGrade = finalGrade;

        return finalGrade;
    }

    public void checkIfIsPassed () {
        double finalGrade = calculateFinalGrade();

        this.isPassed = finalGrade >= 7;
    }
}
