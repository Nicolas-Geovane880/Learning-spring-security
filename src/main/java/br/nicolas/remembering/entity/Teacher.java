package br.nicolas.remembering.entity;

import br.nicolas.remembering.enums.UserRoles;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@AllArgsConstructor @NoArgsConstructor @Getter @Setter
@SuperBuilder @Entity @Table (name = "teacher")
public class Teacher extends User {

    @Builder.Default
    @Column (name = "teacher_role")
    @Enumerated (value = EnumType.STRING)
    private final UserRoles role = UserRoles.TEACHER;
}
