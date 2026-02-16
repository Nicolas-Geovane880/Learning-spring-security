package br.nicolas.remembering.dto.classes;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter @Builder @Setter
public class ClassResponseDTO {

    private Long id;

    private String discipline;

    private String shift;

    private String teacherName;

    private int studentCount;
}
