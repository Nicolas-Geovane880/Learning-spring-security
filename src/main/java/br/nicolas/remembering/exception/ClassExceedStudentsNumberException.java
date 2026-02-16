package br.nicolas.remembering.exception;

public class ClassExceedStudentsNumberException extends RuntimeException {

    public ClassExceedStudentsNumberException(String message) {
        super(message);
    }
}
