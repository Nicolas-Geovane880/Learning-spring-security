package br.nicolas.remembering.mapper;

import br.nicolas.remembering.dto.student.StudentCreateDTO;
import br.nicolas.remembering.dto.student.StudentResponseDTO;
import br.nicolas.remembering.entity.Student;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Mapper (componentModel = "spring")
public interface StudentMapper {

    Student parseToEntity (StudentCreateDTO createDTO);

    @Mapping (source = "isPassed", target = "isPassed", qualifiedByName = "setIsPassedStatus")
    @Mapping (source = "grades", target = "grades", qualifiedByName = "parseStringGradesToList")
    StudentResponseDTO parseToResponse (Student entity);

    @Named (value = "setIsPassedStatus")
    static String setIsPassedStatus (Boolean isPassed) {
        if (isPassed == null) return "In revision";
        if (isPassed) return "Passed";
        else return "Fail";
    }

    @Named (value = "parseStringGradesToList")
    static List<Double> parseStringGradesToList (String grades) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        if (grades != null) {
            return objectMapper.readValue(grades, new TypeReference<>() {});
        }
        return null;
    }
}
