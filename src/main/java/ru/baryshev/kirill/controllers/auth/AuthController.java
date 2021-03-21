package ru.baryshev.kirill.controllers.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.baryshev.kirill.entities.UserEntity;
import ru.baryshev.kirill.services.UsersService;
import ru.baryshev.kirill.security.JwtProvider;

import javax.validation.Valid;

@RestController
public class AuthController {

    @Autowired
    private UsersService userService;

    @Autowired
    private JwtProvider jwtProvider;

    @PostMapping("/register")
    public String registerUser(@RequestBody @Valid RegistrationRequest registrationRequest) {
        UserEntity u = new UserEntity();
        u.setUserPassword(registrationRequest.getPassword());
        u.setUserLogin(registrationRequest.getLogin());
        u.setUserName(registrationRequest.getUserName());
        userService.createUser(u);
        return "OK";
    }

    @PostMapping("/auth")
        public AuthResponse auth(@RequestBody AuthRequest request) {
        UserEntity userEntity = userService.findByLoginAndPassword(request.getLogin(), request.getPassword());
        String token = jwtProvider.generateToken(userEntity.getUserLogin());
        return new AuthResponse(token);
    }
}