package br.nicolas.remembering.dto.clasS;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ClassResponseDTO {

    private Long id;

    private String name;

    private String discipline;

    private String shift;

    private String teacherName;

    private int studentsNumber;
}
