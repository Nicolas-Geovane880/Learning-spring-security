package br.nicolas.remembering.dto.student;

import br.nicolas.remembering.annotation.UniqueEmailCreation;
import br.nicolas.remembering.constant.ConstantValues;
import br.nicolas.remembering.constant.ErrorMessage;
import jakarta.validation.constraints.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder @Getter @Setter
public class StudentCreateDTO {

    @NotBlank (message = ErrorMessage.FIELD_NOT_BLANK)
    @Size (min = ConstantValues.NAME_MINIMUM_SIZE, max = ConstantValues.NAME_MAXIMUM_SIZE, message = ErrorMessage.FIELD_SIZE)
    private String name;

    @Email (message = ErrorMessage.EMAIL_INVALID, regexp = ConstantValues.EMAIL_REGEX)
    @NotBlank (message = ErrorMessage.FIELD_NOT_BLANK)
    @Size (min = ConstantValues.EMAIL_MINIMUM_SIZE, max = ConstantValues.EMAIL_MAXIMUM_SIZE, message = ErrorMessage.FIELD_SIZE)
    private String email;

    @NotBlank (message = ErrorMessage.FIELD_NOT_BLANK)
    @Size (min = ConstantValues.PASSWORD_MINIMUM_SIZE, message = ErrorMessage.PASSWORD_MINIMUM_SIZE)
    private String password;

    @NotNull (message = ErrorMessage.FIELD_NOT_NULL)
    @Positive (message = ErrorMessage.POSITIVE_NUMBER_FIELD)
    private Long classId;
}
