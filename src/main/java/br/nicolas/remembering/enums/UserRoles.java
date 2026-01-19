package br.nicolas.remembering.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UserRoles {

    ADMIN ("admin"),
    TEACHER ("teacher"),
    STUDENT ("student");

    private final String roleStr;
}
