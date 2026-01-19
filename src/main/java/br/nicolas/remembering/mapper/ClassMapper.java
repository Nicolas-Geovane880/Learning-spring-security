package br.nicolas.remembering.mapper;

import br.nicolas.remembering.dto.clasS.ClassCreateDTO;
import br.nicolas.remembering.dto.clasS.ClassResponseDTO;
import br.nicolas.remembering.entity.Class;
import br.nicolas.remembering.entity.Student;
import br.nicolas.remembering.entity.Teacher;
import br.nicolas.remembering.enums.Discipline;
import br.nicolas.remembering.enums.Shift;
import jakarta.validation.Valid;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Mapper (componentModel = "spring")
public interface ClassMapper {

    @Mapping (source = "discipline", target = "discipline", qualifiedByName = "getDisciplineByString")
    @Mapping (source = "shift", target = "shift", qualifiedByName = "getShiftByString")
    Class parseToEntity (ClassCreateDTO createDTO);

    @Mapping (source = "teacher", target = "teacherName", qualifiedByName = "setTeacherNameIfClassHasOne")
    @Mapping (source = "students", target = "studentsNumber", qualifiedByName = "getClassStudentsNumber")
    @Mapping (source = "discipline", target = "discipline", qualifiedByName = "getDisciplineStr")
    @Mapping (source = "shift", target = "shift", qualifiedByName = "getShiftStr")
    ClassResponseDTO parseToResponse (Class entity);

    @Named (value = "getDisciplineByString")
    static Discipline getDisciplineByString (String disciplineStr) {
        return Discipline.fromString(disciplineStr);
    }

    @Named (value = "getShiftByString")
    static Shift getShiftByString (String shiftStr) {
        return Shift.fromString(shiftStr);
    }

    @Named (value = "setTeacherNameIfClassHasOne")
    static String setTeacherNameIfClassHasOne (Teacher teacher) {
        return teacher != null ? teacher.getName() : "No teacher";
    }

    @Named (value = "getClassStudentsNumber")
    static int getClassStudentsNumber (List<Student> students) {
        return students.size();
    }

    @Named (value = "getShiftStr")
    static String getShiftStr (Shift shift) {
        return shift.getShiftStr();
    }

    @Named (value = "getDisciplineStr")
    static String getDisciplineStr (Discipline discipline) {
        return discipline.getDisciplineStr();
    }
}
