package ru.baryshev.kirill.security;

import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

@Component
public class JwtConfig extends SecurityConfigurerAdapter <DefaultSecurityFilterChain, HttpSecurity> {

    private final JwtFilter jwtFilter;

    public JwtConfig(JwtFilter jwtFilter) {
        this.jwtFilter = jwtFilter;
    }

    @Override
    public void configure(HttpSecurity builder) throws Exception {
        builder.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
        super.configure(builder);
    }
}
