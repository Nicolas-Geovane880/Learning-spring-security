package br.nicolas.remembering.exception;

public class InvalidRequestValueException extends RuntimeException {

    public InvalidRequestValueException(String message) {
        super(message);
    }
}
