package com.rost.security;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.rost.models.Person;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter //Необходимо для получения данных аутентифицированного пользователя.
public class PersonDetails implements UserDetails {
    private final Person principal;
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(principal.getRole().name()));
    }

    @Override
    public String getPassword() {
        return principal.getPassword();
    }

    @Override
    public String getUsername() {
        return principal.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true; //stub
    }

    @Override
    public boolean isAccountNonLocked() {
        return true; //stub
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; //stub
    }

    @Override
    public boolean isEnabled() {
        return true; //stub
    }
}
