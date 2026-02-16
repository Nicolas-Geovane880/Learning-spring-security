package br.nicolas.remembering.dto.teacher;

import br.nicolas.remembering.constant.ConstantValues;
import br.nicolas.remembering.constant.ErrorMessage;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;

@Builder @Getter
public class TeacherCreateDTO {

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
}
