package br.nicolas.remembering.dto.teacher;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonPropertyOrder (value = {"id", "name", "email"})
public class TeacherResponseDTO {

    private Long id;

    private String name;

    private String email;
}
