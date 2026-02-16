package br.nicolas.remembering.security.model_wrapper;

import br.nicolas.remembering.entity.Student;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.List;

@AllArgsConstructor
public class StudentDetailsImpl implements UserDetailsImpl {

    private Student student;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_" + student.getRole()));
    }

    @Override
    public String getPassword() { return student.getPassword(); }

    @Override
    public String getUsername() { return student.getEmail(); }

    @Override
    public Long getId() { return student.getId(); }
}
