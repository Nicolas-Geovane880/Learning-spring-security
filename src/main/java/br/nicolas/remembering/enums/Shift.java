package br.nicolas.remembering.enums;

import br.nicolas.remembering.exceptions.ShiftNotFoundException;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Shift {

    MORNING ("Morning"),
    AFTERNOON ("Afternoon"),
    EVENING ("Evening");

    private final String shiftStr;

    public static Shift fromString (String shiftStr) {
        for (Shift s : Shift.values()) {
            if (s.name().equals(shiftStr.toUpperCase())) return s;
        }

        throw new ShiftNotFoundException("Shift '%s' not found".formatted(shiftStr));
    }
}
