package br.nicolas.remembering.global_handler;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import java.time.LocalDate;
import java.time.LocalDateTime;

@SuperBuilder @AllArgsConstructor @NoArgsConstructor @Getter @Setter
@JsonInclude (value = JsonInclude.Include.NON_EMPTY)
public class RequestDetailsException {

    protected int statusCode;

    protected String title;

    protected String message;

    protected String method;

    protected String path;

    protected LocalDateTime timestamp;

    public static RequestDetailsException createDetails(HttpStatus status,
                                                        WebRequest request) {

        String path = request.getDescription(false).replace("uri=", "");
        String method = ((ServletWebRequest) request).getRequest().getMethod();

        return RequestDetailsException.builder()
                .statusCode(status.value())
                .method(method)
                .path(path)
                .timestamp(LocalDateTime.now())
                .build();
    }
}
