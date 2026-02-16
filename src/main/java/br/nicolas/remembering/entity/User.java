package br.nicolas.remembering.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@MappedSuperclass
@Getter @Setter @SuperBuilder @AllArgsConstructor @NoArgsConstructor
public class User {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    protected Long id;

    @Column (nullable = false)
    protected String name;

    @Column (nullable = false, unique = true)
    protected String email;

    @Column (nullable = false)
    protected String password;
}
