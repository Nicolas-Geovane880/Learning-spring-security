package br.nicolas.remembering.global_handler;

import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.stream.Collectors;

@SuperBuilder  @Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class InvalidFieldsRequestExceptionDetails extends RequestDetailsException {

    private Map<String, String> fieldError;

    public static InvalidFieldsRequestExceptionDetails createDetails (HttpStatus status,
                                                      Exception e,
                                                      WebRequest request) {

        Map<String, String> fieldsError = ((MethodArgumentNotValidException) e).getFieldErrors().stream()
                .collect(Collectors.toMap(FieldError::getField,
                        f -> f.getDefaultMessage() == null ? "Invalid field" : f.getDefaultMessage()));

        String method = request.getDescription(false).replace("uri=", "");
        String path = ((ServletWebRequest) request).getRequest().getMethod();

        return InvalidFieldsRequestExceptionDetails.builder()
                .statusCode(status.value())
                .method(method)
                .path(path)
                .fieldError(fieldsError)
                .timestamp(LocalDateTime.now())
                .build();
    }
}
