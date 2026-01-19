package br.nicolas.remembering.exceptions;

public class InvalidRequestValueException extends RuntimeException {

    public InvalidRequestValueException(String message) {
        super(message);
    }
}
