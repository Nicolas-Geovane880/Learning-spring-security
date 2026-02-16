package br.nicolas.remembering.dto.student;

import br.nicolas.remembering.constant.ConstantValues;
import br.nicolas.remembering.constant.ErrorMessage;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.*;

@Builder @Getter @Setter
public class StudentUpdateDTO {

    @Size(min = ConstantValues.NAME_MINIMUM_SIZE, max = ConstantValues.NAME_MAXIMUM_SIZE, message = ErrorMessage.FIELD_SIZE)
    private String name;

    @Email(message = ErrorMessage.EMAIL_INVALID, regexp = ConstantValues.EMAIL_REGEX)
    @Size (min = ConstantValues.EMAIL_MINIMUM_SIZE, max = ConstantValues.EMAIL_MAXIMUM_SIZE, message = ErrorMessage.FIELD_SIZE)
    private String email;

    @Size (min = ConstantValues.PASSWORD_MINIMUM_SIZE, message = ErrorMessage.PASSWORD_MINIMUM_SIZE)
    private String password;

    @Positive(message = ErrorMessage.POSITIVE_NUMBER_FIELD)
    private Long classId;
}
