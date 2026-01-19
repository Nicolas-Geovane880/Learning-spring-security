package br.nicolas.remembering.exceptions.details_global;

import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import java.time.LocalDate;

@SuperBuilder @AllArgsConstructor @NoArgsConstructor @Getter @Setter
public class RequestDetailsException {

    protected int statusCode;

    protected String title;

    protected String message;

    protected String method;

    protected String path;

    protected LocalDate timestamp;

    public RequestDetailsException createDetails(HttpStatus status,
                                                 String title,
                                                 Exception e,
                                                 WebRequest request) {

        String method = request.getDescription(false).replace("uri=", "");
        String path = ((ServletWebRequest) request).getRequest().getMethod();

        return RequestDetailsException.builder()
                .statusCode(status.value())
                .title(title)
                .message(e.getMessage())
                .method(method)
                .path(path)
                .timestamp(LocalDate.now())
                .build();
    }
}
