package br.nicolas.remembering.enums;

import br.nicolas.remembering.exception.DisciplineNotFoundException;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

@Getter
@AllArgsConstructor
public enum Discipline {

    MATH ("Math"),
    ENGLISH ("English"),
    HISTORY ("History"),
    CHEMICAL ("Chemical"),
    PHYSIC ("Physic"),
    ADVOCACY ("Advocacy"),
    COMPUTER_SCIENCE ("Computer Science"),
    SOFTWARE_ENGINEER ("Software Engineer"),
    MEDICINE ("Medicine"),
    CIVIL_ENGINEER ("Civil Engineer"),
    MECHANICAL_ENGINEER ("Mechanical Engineer");

    private final String disciplineStr;

    public static Discipline fromString (String disciplineStr) {
        for (Discipline d : Discipline.values()) {
            if (d.getDisciplineStr().equalsIgnoreCase(disciplineStr) || d.name().equalsIgnoreCase(disciplineStr)) {
                return d;
            }
        }

        throw new DisciplineNotFoundException(disciplineStr);
    }

    public static List<String> getAllDisciplineOptions () {
        List<String> optionsEnumValue = Arrays.stream(Discipline.values()).map(Enum::name).toList();
        List<String> optionsStrValue = Arrays.stream(Discipline.values()).map(Discipline::getDisciplineStr).toList();

        return Stream.concat(optionsStrValue.stream(), optionsEnumValue.stream()).toList();
    }
}
