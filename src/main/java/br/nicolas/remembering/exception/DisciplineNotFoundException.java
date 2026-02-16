package br.nicolas.remembering.exception;

public class DisciplineNotFoundException extends RuntimeException {

    public DisciplineNotFoundException(String message) {
        super(message);
    }
}
