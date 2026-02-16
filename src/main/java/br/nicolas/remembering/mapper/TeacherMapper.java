package br.nicolas.remembering.mapper;

import br.nicolas.remembering.dto.teacher.TeacherCreateDTO;
import br.nicolas.remembering.dto.teacher.TeacherResponseDTO;
import br.nicolas.remembering.dto.teacher.TeacherUpdateDTO;
import br.nicolas.remembering.entity.Teacher;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

@Mapper (componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public abstract class TeacherMapper {

    @Autowired
    protected PasswordEncoder passwordEncoder;

    // ----- Mapping createDTO to Student entity (and encoding the password) -----
    @Mapping (source = "password", target = "password", qualifiedByName = "encodePassword")
    public abstract Teacher parseToEntity (TeacherCreateDTO createDTO);

    // ----- Parsing teacher to response
    public abstract TeacherResponseDTO parseToResponse (Teacher entity);

    // ----- Update methods (using Dirty Checking) -----
    public abstract void updateEntityFromDTO (TeacherUpdateDTO updateDTO, @MappingTarget Teacher teacher);

    @AfterMapping
    protected void handlePasswordEncoder (TeacherUpdateDTO updateDTO, @MappingTarget Teacher teacher) {
        String rawPassword = updateDTO.getPassword();

        if (rawPassword != null && !updateDTO.getPassword().isBlank()) {
            teacher.setPassword(passwordEncoder.encode(rawPassword));
        }
    }

    // ----- Named methods -----
    @Named (value = "encodePassword")
    protected String encodePassword (String rawPassword) {
        return passwordEncoder.encode(rawPassword);
    }
}
