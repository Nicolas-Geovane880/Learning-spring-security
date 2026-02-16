package br.nicolas.remembering.dto.student;

import br.nicolas.remembering.annotation.GradeListValidation;
import br.nicolas.remembering.constant.ErrorMessage;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter @Builder @Setter
public class GradeDTO {

    @NotNull (message = ErrorMessage.FIELD_NOT_NULL)
    @Positive (message = ErrorMessage.POSITIVE_NUMBER_FIELD)
    private Long studentId;

    @Builder.Default
    @GradeListValidation
    private List<Object> grades = new ArrayList<>(3);
}
