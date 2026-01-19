package br.nicolas.remembering.mapper;

import br.nicolas.remembering.dto.teacher.TeacherCreateDTO;
import br.nicolas.remembering.dto.teacher.TeacherResponseDTO;
import br.nicolas.remembering.dto.teacher.TeacherUpdateDTO;
import br.nicolas.remembering.entity.Teacher;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper (componentModel = "spring")
public interface TeacherMapper {

    Teacher parseToEntity (TeacherCreateDTO createDTO);

    @Mapping(source = "newName", target = "name")
    @Mapping(source = "newEmail", target = "email")
    @Mapping(source = "newPassword", target = "password")
    Teacher parseToEntity (TeacherUpdateDTO updateDTO);

    TeacherResponseDTO parseToResponse (Teacher entity);
}
