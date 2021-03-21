package ru.baryshev.kirill.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import ru.baryshev.kirill.entities.UserEntity;
import ru.baryshev.kirill.services.UsersService;

@Component
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UsersService usersService;

    @Override
    public CustomUserDetails loadUserByUsername(String userLogin) throws UsernameNotFoundException {
        UserEntity userEntity = usersService.findByLogin(userLogin);
        return CustomUserDetails.fromUserEntityToCustomUserDetails(userEntity);
    }
}