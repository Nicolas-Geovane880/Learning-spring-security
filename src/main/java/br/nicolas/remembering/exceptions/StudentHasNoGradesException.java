package br.nicolas.remembering.exceptions;

public class StudentHasNoGradesException extends RuntimeException {

    public StudentHasNoGradesException(String message) {
        super(message);
    }
}
