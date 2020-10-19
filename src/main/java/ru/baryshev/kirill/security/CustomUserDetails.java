package ru.baryshev.kirill.security;

import com.sun.org.apache.xpath.internal.operations.Bool;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import ru.baryshev.kirill.dto.users.CreateUserDto;
import ru.baryshev.kirill.models.Users;

import java.util.Collection;
import java.util.Collections;

public class CustomUserDetails implements UserDetails {

    private String login;
    private String password;
    private Collection<? extends GrantedAuthority> grantedAuthorities;
    private Boolean isActive;

    public static CustomUserDetails fromUserEntityToCustomUserDetails(CreateUserDto userEntity) {
        CustomUserDetails c = new CustomUserDetails();
        c.login = userEntity.getUserLogin();
        c.password = userEntity.getUserPassword();
        c.grantedAuthorities = Collections.singletonList(new SimpleGrantedAuthority(userEntity.getUserRole()));
        c.isActive = userEntity.getIsActive();
        return c;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return grantedAuthorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return isActive;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}