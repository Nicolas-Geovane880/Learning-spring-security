package br.nicolas.remembering.exceptions.details_global;

import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDate;
import java.util.stream.Collectors;

@SuperBuilder @AllArgsConstructor @NoArgsConstructor @Getter @Setter
public class InvalidFieldsRequestExceptionDetails extends RequestDetailsException {

    private String fieldError;

    @Override
    public RequestDetailsException createDetails (HttpStatus status,
                                                      String title,
                                                      Exception e,
                                                      WebRequest request) {

        String fieldsAndDefaultMessages = ((MethodArgumentNotValidException) e).getFieldErrors().stream()
                .map(f -> f.getField() + "[" + f.getDefaultMessage() + "]").collect(Collectors.joining(" - "));

        String method = request.getDescription(false).replace("uri=", "");
        String path = ((ServletWebRequest) request).getRequest().getMethod();

        return InvalidFieldsRequestExceptionDetails.builder()
                .statusCode(status.value())
                .title(title)
                .message("Some field(s) is/are invalid")
                .method(method)
                .path(path)
                .fieldError(fieldsAndDefaultMessages)
                .timestamp(LocalDate.now())
                .build();
    }
}
