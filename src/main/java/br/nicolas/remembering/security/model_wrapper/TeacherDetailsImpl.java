package br.nicolas.remembering.security.model_wrapper;

import br.nicolas.remembering.entity.Teacher;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import java.util.Collection;
import java.util.List;

@AllArgsConstructor
public class TeacherDetailsImpl implements UserDetailsImpl {

    private Teacher teacher;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_" + teacher.getRole()));
    }

    @Override
    public String getPassword() {
        return teacher.getPassword();
    }

    @Override
    public String getUsername() {
        return teacher.getEmail();
    }

    public Long getId () {
        return teacher.getId();
    }
}
