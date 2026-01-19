package br.nicolas.remembering.entity;

import br.nicolas.remembering.enums.UserRoles;
import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor @NoArgsConstructor @Getter @Setter
@Builder @Entity @Table (name = "teacher")
public class Teacher {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    @Column (name = "teacher_name", nullable = false)
    private String name;

    @Column (name = "teacher_email", nullable = false, unique = true)
    private String email;

    @Column (name = "teacher_password", nullable = false)
    private String password;

    @Builder.Default
    @Column (name = "teacher_role", nullable = false)
    @Enumerated (value = EnumType.STRING)
    private final UserRoles role = UserRoles.TEACHER;
}
