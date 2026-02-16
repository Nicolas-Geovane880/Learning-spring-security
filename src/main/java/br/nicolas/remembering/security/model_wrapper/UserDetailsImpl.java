package br.nicolas.remembering.security.model_wrapper;

import org.springframework.security.core.userdetails.UserDetails;

public interface UserDetailsImpl extends UserDetails {

    Long getId ();
}
