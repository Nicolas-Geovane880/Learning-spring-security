package br.nicolas.remembering.dto.classes;

import br.nicolas.remembering.constant.ConstantValues;
import br.nicolas.remembering.constant.ErrorMessage;
import jakarta.validation.constraints.*;
import lombok.Builder;
import lombok.Getter;
import org.hibernate.validator.constraints.Length;

@Getter @Builder
public class ClassCreateDTO {

    @NotBlank(message = ErrorMessage.FIELD_NOT_BLANK)
    private String discipline;

    @NotNull (message = ErrorMessage.FIELD_NOT_NULL)
    @Min (value = ConstantValues.CLASS_MINIMUM_YEAR, message = ErrorMessage.CLASS_YEAR_INTERVAL)
    @Max (value = ConstantValues.CLASS_MAXIMUM_YEAR, message = ErrorMessage.CLASS_YEAR_INTERVAL)
    private int year;

    @NotBlank (message = ErrorMessage.FIELD_NOT_BLANK)
    private String shift;

    @NotNull (message = ErrorMessage.FIELD_NOT_NULL)
    @Positive (message = ErrorMessage.POSITIVE_NUMBER_FIELD)
    private Long teacherId;
}
