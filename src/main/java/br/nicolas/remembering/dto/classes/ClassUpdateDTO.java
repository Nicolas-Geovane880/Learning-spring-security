package br.nicolas.remembering.dto.classes;

import br.nicolas.remembering.constant.ErrorMessage;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Positive;
import lombok.Builder;
import lombok.Getter;

@Getter @Builder
public class ClassUpdateDTO {

    private String discipline;

    private int year;

    @Min(value = 2022, message = ErrorMessage.CLASS_YEAR_INTERVAL)
    @Max(value = 2026, message = ErrorMessage.CLASS_YEAR_INTERVAL)
    private String shift;

    @Positive(message = ErrorMessage.POSITIVE_NUMBER_FIELD)
    private Long teacherId;
}
