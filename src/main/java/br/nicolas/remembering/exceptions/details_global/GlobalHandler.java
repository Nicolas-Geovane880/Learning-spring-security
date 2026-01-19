package br.nicolas.remembering.exceptions.details_global;

import br.nicolas.remembering.exceptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class GlobalHandler {

    private final RequestDetailsException requestDetails = new RequestDetailsException();

    @ExceptionHandler (StudentHasNoGradesException.class)
    public ResponseEntity<RequestDetailsException> handleStudentHasNoGradesException (StudentHasNoGradesException e,
                                                                                      WebRequest request) {
        String title = "Operation interrupted";

        return new ResponseEntity<>(requestDetails.createDetails
                (HttpStatus.BAD_REQUEST, title, e, request),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler (UsernameNotFoundException.class)
    public ResponseEntity<RequestDetailsException> handleUsernameNotFoundException (UsernameNotFoundException e,
                                                                                    WebRequest request) {
        String title = "Authentication interrupted because the user was not found";

        return new ResponseEntity<>(requestDetails.createDetails
                (HttpStatus.BAD_REQUEST, title, e, request),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler (InvalidRequestValueException.class)
    public ResponseEntity<RequestDetailsException> handleInvalidRequestValueException (InvalidRequestValueException e,
                                                                                       WebRequest request) {
        String title = "Requested value is invalid";

        return new ResponseEntity<>(requestDetails.createDetails
                (HttpStatus.BAD_REQUEST, title, e, request),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler (Exception.class)
    public ResponseEntity<RequestDetailsException> handleGenericException (Exception e,
                                                                           WebRequest request) {
        String title = "An internal error occurred";

        return new ResponseEntity<>(requestDetails.createDetails
                (HttpStatus.BAD_REQUEST, title, e, request),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler (DisciplineNotFoundException.class)
    public ResponseEntity<RequestDetailsException> handleDisciplineNotFoundException (DisciplineNotFoundException e,
                                                                                      WebRequest request) {
        String title = "Class operation interrupted due an invalid requested value";

        return new ResponseEntity<>(requestDetails.createDetails
                (HttpStatus.BAD_REQUEST, title, e, request),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler (ShiftNotFoundException.class)
    public ResponseEntity<RequestDetailsException> handleShiftNotFoundException (ShiftNotFoundException e,
                                                                                 WebRequest request) {
        String title = "Class operation interrupted due an invalid requested value";

        return new ResponseEntity<>(requestDetails.createDetails
                (HttpStatus.BAD_REQUEST, title, e, request),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler (MethodArgumentNotValidException.class)
    public ResponseEntity<RequestDetailsException> handleMethodArgumentNotValidException (MethodArgumentNotValidException e,
                                                                                          WebRequest request) {

        InvalidFieldsRequestExceptionDetails requestInvalidFieldDetails = new InvalidFieldsRequestExceptionDetails();

        String title = "Creation interrupted due an invalid request";

        return new ResponseEntity<>(requestInvalidFieldDetails.createDetails
                (HttpStatus.BAD_REQUEST, title, e, request),
                HttpStatus.BAD_REQUEST);
    }
}
