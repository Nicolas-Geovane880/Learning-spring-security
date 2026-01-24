package br.nicolas.remembering.dto.classes;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ClassResponseDTO {

    private Long id;

    private String discipline;

    private String shift;

    private String teacherName;

    private int studentsNumber;
}
