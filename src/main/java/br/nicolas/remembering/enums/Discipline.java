package br.nicolas.remembering.enums;

import br.nicolas.remembering.exceptions.DisciplineNotFoundException;
import lombok.AllArgsConstructor;
import lombok.Getter;

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
            if (d.name().equals(disciplineStr.toUpperCase().replace(" ", "_"))) return d;
        }

        throw new DisciplineNotFoundException("Discipline '%s' not found".formatted(disciplineStr));
    }
}
