package br.nicolas.remembering.mapper;

import br.nicolas.remembering.dto.student.StudentCreateDTO;
import br.nicolas.remembering.dto.student.StudentResponseDTO;
import br.nicolas.remembering.dto.student.StudentUpdateDTO;
import br.nicolas.remembering.entity.Student;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@Mapper (componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public abstract class StudentMapper {

    @Autowired
    protected PasswordEncoder passwordEncoder;

    @Autowired
    protected ObjectMapper objectMapper;

    // ----- Mapping createDTO to Student entity (and encoding the password) -----
    @Mapping (source = "password", target = "password", qualifiedByName = "encodePassword")
    public abstract Student parseToEntity (StudentCreateDTO createDTO);

    // ----- Parsing to Student response -----
    @Mapping (source = "isPassed", target = "isPassed", qualifiedByName = "setIsPassedStatus")
    @Mapping (source = "grades", target = "grades", qualifiedByName = "parseStringGradesToList")
    public abstract StudentResponseDTO parseToResponse (Student entity);

    // ----- Update methods (using Dirty Checking) -----
    public abstract void updateEntityFromDTO (StudentUpdateDTO updateDTO, @MappingTarget Student student);

    @AfterMapping
    protected void handlePasswordEncoder (StudentUpdateDTO updateDTO, @MappingTarget Student student) {
        String rawPassword = updateDTO.getPassword();

        if (rawPassword != null && !updateDTO.getPassword().isBlank()) {
            student.setPassword(passwordEncoder.encode(rawPassword));
        }
    }

    // ----- Named methods -----
    @Named (value = "encodePassword")
    protected String encodePassword (String rawPassword) {
        return passwordEncoder.encode(rawPassword);
    }

    @Named (value = "setIsPassedStatus")
    protected String setIsPassedStatus (Boolean isPassed) {
        if (isPassed == null) return "In revision";
        if (isPassed) return "Passed";
        else return "Fail";
    }

    @Named (value = "parseStringGradesToList")
    protected List<Double> parseStringGradesToList (String grades) throws JsonProcessingException {
        if (grades != null) {
            return this.objectMapper.readValue(grades, new TypeReference<>() {});
        }
        return null;
    }
}
