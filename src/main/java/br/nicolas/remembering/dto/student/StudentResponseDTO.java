package br.nicolas.remembering.dto.student;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder @Getter
@JsonInclude (value = JsonInclude.Include.NON_EMPTY)
@JsonPropertyOrder (value = {"id", "name", "email", "isPassed", "finalGrade", "grades"})
public class StudentResponseDTO {

    private Long id;

    private String name;

    private String email;

    private List<Double> grades;

    private Double finalGrade;

    private String isPassed;
}
