package br.nicolas.remembering.entity;

import br.nicolas.remembering.constant.ConstantValues;
import br.nicolas.remembering.constant.ErrorMessage;
import br.nicolas.remembering.enums.Discipline;
import br.nicolas.remembering.enums.Shift;
import br.nicolas.remembering.exception.ClassExceedStudentsNumberException;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Formula;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor @NoArgsConstructor
@Getter @Builder @Entity @Setter
@Table (name = "class")
public class Class {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated (value = EnumType.STRING)
    @Column (name = "class_discipline", nullable = false)
    private Discipline discipline;

    @Column (name = "class_year", nullable = false)
    private Integer year;

    @Enumerated (value = EnumType.STRING)
    @Column (name = "class_shift", nullable = false)
    private Shift shift;

    @Builder.Default
    @OneToMany (mappedBy = "studentClass", fetch = FetchType.LAZY)
    private List<Student> students = new ArrayList<>(ConstantValues.MAXIMUM_STUDENTS_PER_CLASS);

    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn (name = "teacher_id")
    private Teacher teacher;

    @Formula ("(SELECT COUNT(*) FROM student s WHERE s.class_id = id)")
    private int studentCount;

    public void addStudent (Student student) {
        if (students.size() >= 40) throw new ClassExceedStudentsNumberException(ErrorMessage.CLASS_EXCEED_STUDENTS_NUMBER);

        student.setStudentClass(this);
        this.students.add(student);
    }

    public void removeStudent (Student student) {
        student.setStudentClass(null);
        this.students.remove(student);
    }

    public void changeStudentClass (Student student, Class newClass) {
        this.removeStudent(student);
        newClass.addStudent(student);
    }
}
