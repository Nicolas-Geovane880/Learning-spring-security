package br.nicolas.remembering.mapper;

import br.nicolas.remembering.dto.classes.ClassCreateDTO;
import br.nicolas.remembering.dto.classes.ClassResponseDTO;
import br.nicolas.remembering.dto.classes.ClassUpdateDTO;
import br.nicolas.remembering.entity.Class;
import br.nicolas.remembering.entity.Student;
import br.nicolas.remembering.entity.Teacher;
import br.nicolas.remembering.enums.Discipline;
import br.nicolas.remembering.enums.Shift;
import br.nicolas.remembering.service.ClassService;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Mapper (componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public abstract class ClassMapper {

    // ----- Mapping createDTO to Class entity -----
    @Mapping (source = "discipline", target = "discipline", qualifiedByName = "getDisciplineByString")
    @Mapping (source = "shift", target = "shift", qualifiedByName = "getShiftByString")
    public abstract Class parseToEntity (ClassCreateDTO createDTO);

    // ----- Parsing Class to response -----
    @Mapping (source = "teacher", target = "teacherName", qualifiedByName = "setTeacherNameIfClassHasOne")
    @Mapping (source = "discipline", target = "discipline", qualifiedByName = "getDisciplineStr")
    @Mapping (source = "shift", target = "shift", qualifiedByName = "getShiftStr")
    public abstract ClassResponseDTO parseToResponse (Class entity);

    // ----- Update methods (using Dirty Checking) -----
    public abstract void updateEntityFromDTO (ClassUpdateDTO updateDTO, @MappingTarget Class classes);

    // ----- Named methods -----
    @Named (value = "getDisciplineByString")
    protected static Discipline getDisciplineByString (String disciplineStr) {
        return Discipline.fromString(disciplineStr);
    }

    @Named (value = "getShiftByString")
    protected static Shift getShiftByString (String shiftStr) {
        return Shift.fromString(shiftStr);
    }

    @Named (value = "setTeacherNameIfClassHasOne")
    protected static String setTeacherNameIfClassHasOne (Teacher teacher) {
        return teacher != null ? teacher.getName() : "Class without teacher";
    }

    @Named (value = "getShiftStr")
    protected static String getShiftStr (Shift shift) {
        return shift.getShiftStr();
    }

    @Named (value = "getDisciplineStr")
    protected static String getDisciplineStr (Discipline discipline) {
        return discipline.getDisciplineStr();
    }
}
