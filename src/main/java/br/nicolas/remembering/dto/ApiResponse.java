package br.nicolas.remembering.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude (JsonInclude.Include.NON_EMPTY)
public class ApiResponse<T> {

    private T data;

    private Map<String, Object> meta = new HashMap<>();

    public ApiResponse<T> addMeta (String messageKey, Object objectValue) {
        this.meta.putIfAbsent(messageKey, objectValue);
        return this;
    }

    public ApiResponse<T> setData (T data) {
        this.data = data;
        return this;
    }
}
