package br.nicolas.remembering.dto.clasS;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ClassCreateDTO {

    private String name;

    private String discipline;

    private int year;

    private String shift;

    private Long teacherId;
}
