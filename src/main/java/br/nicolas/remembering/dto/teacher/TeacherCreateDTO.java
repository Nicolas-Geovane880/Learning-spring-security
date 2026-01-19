package br.nicolas.remembering.dto.teacher;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class TeacherCreateDTO {

    @NotNull (message = "The teacher name can not be null")
    @NotBlank (message = "The teacher name can not be blank")
    @Size (min = 5, max = 75, message = "The teacher name length has to be between 5 and 75")
    private String name;

    @Email (message = "The teacher email has be valid")
    @NotNull (message = "The teacher email can not be null")
    @NotBlank (message = "The teacher email can not be blank")
    @Size (min = 12, max = 75, message = "The teacher email length has to be between 12 and 75")
    private String email;

    @NotNull (message = "The teacher password can not be null")
    @NotBlank (message = "The teacher password can not be blank")
    @Size (min = 8, message = "The teacher password has as minimum 8 chars of length")
    private String password;
}
