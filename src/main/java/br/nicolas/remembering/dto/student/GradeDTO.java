package br.nicolas.remembering.dto.student;

import lombok.Builder;
import lombok.Getter;
import java.util.ArrayList;
import java.util.List;

@Getter @Builder
public class GradeDTO {

    private Long studentId;

    private Long teacherId;

    @Builder.Default
    private List<Double> grades = new ArrayList<>(3);
}
