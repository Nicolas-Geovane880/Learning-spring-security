package br.nicolas.remembering.entity;

import br.nicolas.remembering.enums.Discipline;
import br.nicolas.remembering.enums.Shift;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor @NoArgsConstructor
@Getter @Builder @Entity @Setter
@Table (name = "class")
public class Class {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    @Column (name = "class_name", nullable = false)
    private String name;

    @Enumerated (value = EnumType.STRING)
    @Column (name = "class_discipline", nullable = false)
    private Discipline discipline;

    @Column (name = "class_year", nullable = false)
    private int year;

    @Enumerated (value = EnumType.STRING)
    @Column (name = "class_shift", nullable = false)
    private Shift shift;

    @Builder.Default
    @OneToMany (mappedBy = "studentClass", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Student> students = new ArrayList<>(40);

    @ManyToOne
    @JoinColumn (name = "teacher_id", nullable = false)
    private Teacher teacher;

    public void addStudent (Student student) {
        student.setStudentClass(this);
        this.students.add(student);
    }

    public void removeStudent (Student student) {
        student.setStudentClass(null);
        this.students.remove(student);
    }
}
