package br.nicolas.remembering.dto.teacher;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class TeacherUpdateDTO {

    private String newName;

    private String newEmail;

    private String newPassword;
}
