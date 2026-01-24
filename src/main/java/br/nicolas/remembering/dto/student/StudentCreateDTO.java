package br.nicolas.remembering.dto.student;

import jakarta.validation.constraints.*;
import lombok.Builder;
import lombok.Getter;

@Builder @Getter
public class StudentCreateDTO {

    @NotNull (message = "The student name can not be null")
    @NotBlank(message = "The student name can not be blank")
    @Size(min = 5, max = 75, message = "The student name length has to be between 5 and 75")
    private String name;

    @Email(message = "The student email has be valid")
    @NotNull (message = "The student email can not be null")
    @NotBlank (message = "The student email can not be blank")
    @Size (min = 12, max = 50, message = "The student email length has to be between 12 and 50")
    private String email;

    @NotNull (message = "The student password can not be null")
    @NotBlank (message = "The student password can not be blank")
    @Size (min = 8, message = "The student password has as minimum 8 chars of length")
    private String password;

    @NotNull (message = "The class id can not be null")
    @NotBlank (message = "The class id can not be blank")
    @Positive (message = "The class id has to be a positive number")
    private Long classId;
}
