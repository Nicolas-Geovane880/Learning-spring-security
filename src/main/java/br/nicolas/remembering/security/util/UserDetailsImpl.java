package br.nicolas.remembering.security.util;

import org.springframework.security.core.userdetails.UserDetails;

public interface UserDetailsImpl extends UserDetails {

    Long getId ();
}
