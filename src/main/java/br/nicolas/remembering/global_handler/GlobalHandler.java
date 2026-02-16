package br.nicolas.remembering.global_handler;

import br.nicolas.remembering.constant.ErrorMessage;
import br.nicolas.remembering.enums.Discipline;
import br.nicolas.remembering.enums.Shift;
import br.nicolas.remembering.exception.*;
import br.nicolas.remembering.util.SuggestionUtil;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import lombok.AllArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.authorization.AuthorizationDeniedException;
import org.springframework.validation.method.MethodValidationException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

@RestControllerAdvice
@AllArgsConstructor
public class GlobalHandler {
    
    private MessageSource messageSource;

    @ExceptionHandler (Exception.class)
    public ResponseEntity<RequestDetailsException> handleGenericException (Exception e,
                                                                           WebRequest request) {

        RequestDetailsException details = RequestDetailsException.createDetails(HttpStatus.BAD_REQUEST, request);

        e.printStackTrace();

        return new ResponseEntity<>(translateAndSetMessageAndTitle
                (e.getMessage(), details, ErrorMessage.INTERNAL_ERROR),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler (AuthorizationDeniedException.class)
    public ResponseEntity<RequestDetailsException> handleAuthorizationDeniedException (AuthorizationDeniedException e,
                                                                                       WebRequest request) {

        RequestDetailsException details = RequestDetailsException.createDetails(HttpStatus.BAD_REQUEST, request);

        return new ResponseEntity<>(translateAndSetOnlyTitle
                (details, ErrorMessage.USER_NOT_ID_OWNER),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler (DisciplineNotFoundException.class)
    public ResponseEntity<RequestDetailsException> handleDisciplineNotFoundException (DisciplineNotFoundException e,
                                                                                      WebRequest request) {

        RequestDetailsException details = RequestDetailsException.createDetails(HttpStatus.BAD_REQUEST, request);

        String discipline = e.getMessage();
        String disciplineSuggestion = SuggestionUtil.suggest(discipline, Discipline.getAllDisciplineOptions());
        String message = "Invalid discipline '%s' ".formatted(discipline);

        if (disciplineSuggestion != null) {
            message = message + "(Did you mean '%s'?)".formatted(disciplineSuggestion);
        }

        details.setMessage(message);

        return new ResponseEntity<>(translateAndSetOnlyTitle
                (details, ErrorMessage.OPERATION_INTERRUPTED),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler (ShiftNotFoundException.class)
    public ResponseEntity<RequestDetailsException> handleShiftNotFoundException (ShiftNotFoundException e,
                                                                                 WebRequest request) {

        RequestDetailsException details = RequestDetailsException.createDetails(HttpStatus.BAD_REQUEST, request);

        String shift = e.getMessage();
        List<String> validOptions = Arrays.stream(Shift.values()).map(Enum::name).toList();
        String shiftSuggestion = SuggestionUtil.suggest(shift, validOptions);
        String message = "Invalid shift '%s' ".formatted(shift);

        if (shiftSuggestion != null) {
            message = message + "(Did you mean '%s'?)".formatted(shiftSuggestion);
        }

        details.setMessage(message);

        return new ResponseEntity<>(translateAndSetOnlyTitle
                (details, ErrorMessage.OPERATION_INTERRUPTED),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler (MethodArgumentNotValidException.class)
    public ResponseEntity<RequestDetailsException> handleMethodArgumentNotValidException (MethodArgumentNotValidException e,
                                                                                                       WebRequest request) {

        InvalidFieldsRequestExceptionDetails details = InvalidFieldsRequestExceptionDetails.createDetails(HttpStatus.BAD_REQUEST, e, request);

        return new ResponseEntity<>(translateAndSetOnlyTitle
                (details, ErrorMessage.OPERATION_INTERRUPTED),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler (InvalidGradesException.class)
    public ResponseEntity<RequestDetailsException> handleInvalidGradesException (InvalidGradesException e,
                                                                                 WebRequest request) {

        RequestDetailsException details = RequestDetailsException.createDetails(HttpStatus.BAD_REQUEST, request);

        return new ResponseEntity<>(translateAndSetMessageAndTitle
                (e.getMessage(), details, ErrorMessage.OPERATION_INTERRUPTED),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler (NoSuchElementException.class)
    public ResponseEntity<RequestDetailsException> handleNoSuchElementException (NoSuchElementException e,
                                                                                 WebRequest request) {

        RequestDetailsException details = RequestDetailsException.createDetails(HttpStatus.BAD_REQUEST, request);

        return new ResponseEntity<>(translateAndSetMessageAndTitle
                (e.getMessage(), details, ErrorMessage.OPERATION_INTERRUPTED),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler (EmailAlreadyInUseException.class)
    public ResponseEntity<RequestDetailsException> handleEmailAlreadyInUseException (EmailAlreadyInUseException e,
                                                                                     WebRequest request) {

        RequestDetailsException details = RequestDetailsException.createDetails(HttpStatus.BAD_REQUEST, request);

        return new ResponseEntity<>(translateAndSetMessageAndTitle
                (e.getMessage(), details, ErrorMessage.OPERATION_INTERRUPTED),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler (ClassExceedStudentsNumberException.class)
    public ResponseEntity<RequestDetailsException> handleClassExceedStudentsNumberException (ClassExceedStudentsNumberException e,
                                                                                             WebRequest request) {

        RequestDetailsException details = RequestDetailsException.createDetails(HttpStatus.BAD_REQUEST, request);

        return new ResponseEntity<>(translateAndSetMessageAndTitle
                (e.getMessage(), details, ErrorMessage.OPERATION_INTERRUPTED),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler (HttpMessageNotReadableException.class)
    public ResponseEntity<RequestDetailsException> handleHttpMessageNotReadableException (HttpMessageNotReadableException e,
                                                                                          WebRequest request) {

        RequestDetailsException details = RequestDetailsException.createDetails(HttpStatus.BAD_REQUEST, request);

        String message = null;

        if (e.getMessage() != null && e.getMessage().contains("Required request body is missing")) {
            message = ErrorMessage.REQUEST_BODY_IS_REQUIRED;
        }
        else if (e.getCause() instanceof JsonParseException) {
            message = ErrorMessage.JSON_INVALID_FORMAT;
        }

        return new ResponseEntity<>(translateAndSetMessageAndTitle
                (message, details, ErrorMessage.OPERATION_INTERRUPTED),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler (TeacherOverpassClassLimitException.class)
    public ResponseEntity<RequestDetailsException> handleTeacherOverpassClassLimitException (TeacherOverpassClassLimitException e,
                                                                                             WebRequest request) {

        RequestDetailsException details = RequestDetailsException.createDetails(HttpStatus.BAD_REQUEST, request);

        return new ResponseEntity<>(translateAndSetMessageAndTitle
                (e.getMessage(), details, ErrorMessage.OPERATION_INTERRUPTED),
                HttpStatus.BAD_REQUEST);
    }

    private RequestDetailsException translateAndSetMessageAndTitle (String message, RequestDetailsException detailsException, String title) {
        String translatedMessage = messageSource.getMessage(message, null, message, LocaleContextHolder.getLocale());
        String translatedTitle = messageSource.getMessage(title, null, title, LocaleContextHolder.getLocale());
        detailsException.setMessage(translatedMessage);
        detailsException.setTitle(translatedTitle);

        return detailsException;
    }

    private RequestDetailsException translateAndSetOnlyTitle (RequestDetailsException detailsException, String title) {
        String translatedTitle = messageSource.getMessage(title, null, title, LocaleContextHolder.getLocale());
        detailsException.setTitle(translatedTitle);

        return detailsException;
    }
}
