package br.nicolas.remembering.security.util;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Component (value = "securityRoleFilterUtil")
public class SecurityRoleFilterUtil {

    public boolean isIdOwner (Authentication authentication, Long id) {
        if (authentication == null || id == null) {
            return false;
        }

        Object principal = authentication.getPrincipal();

        if (!(principal instanceof UserDetailsImpl user)) {
            return false;
        }

        return user.getId().equals(id);
    }
}
