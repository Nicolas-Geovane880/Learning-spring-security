package br.nicolas.remembering.exceptions;

public class ShiftNotFoundException extends RuntimeException {

    public ShiftNotFoundException(String message) {
        super(message);
    }
}
