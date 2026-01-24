package br.nicolas.remembering.dto.classes;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import org.hibernate.validator.constraints.Length;

@Getter
@Builder
public class ClassCreateDTO {

    @NotNull(message = "The class discipline can not be null")
    @NotBlank(message = "The class discipline can not be blank")
    private String discipline;

    @NotNull (message = "The class year can not be null")
    @NotBlank (message = "The class year can not be blank")
    @Length (min = 2021, max = 2026, message = "The class year has to be between 2021 and 2026")
    private int year;

    @NotNull (message = "The class shift can not be null")
    @NotBlank (message = "The class shift can not be blank")
    private String shift;

    @NotNull (message = "The teacher id can not be null")
    @NotBlank (message = "The teacher id can not be blank")
    @Positive (message = "The teacher id has to be a positive number")
    private Long teacherId;
}
