package ru.baryshev.kirill.controllers.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.baryshev.kirill.entities.UserEntity;
import ru.baryshev.kirill.security.JwtProvider;
import ru.baryshev.kirill.services.UsersService;
//import ru.baryshev.kirill.security.JwtProvider;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private AuthenticationManager authenticationManager;

    private UsersService userService;

    private JwtProvider jwtProvider;

    public AuthController(AuthenticationManager authenticationManager, UsersService userService, JwtProvider jwtProvider) {
        this.authenticationManager = authenticationManager;
        this.userService = userService;
        this.jwtProvider = jwtProvider;
    }

    @PostMapping("/login")
//    public AuthResponse auth(@RequestBody AuthRequest request) {
    public ResponseEntity<?> auth(@RequestBody AuthRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getLogin(), request.getPassword()));
        UserEntity userEntity = userService.findByLogin(request.getLogin());
        String token = jwtProvider.generateToken(userEntity.getUserLogin());
        Map<Object, Object> response = new HashMap<>();
        response.put("login", request.getLogin());
        response.put("token", token);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/logout")
    public void logout(HttpServletRequest request, HttpServletResponse response) {
        SecurityContextLogoutHandler securityContextLogoutHandler = new SecurityContextLogoutHandler();
        securityContextLogoutHandler.logout(request, response, null);
    }

    @PostMapping("/register")
    public String registerUser(@RequestBody @Valid RegistrationRequest registrationRequest) {
        UserEntity u = new UserEntity();
        u.setUserPassword(registrationRequest.getPassword());
        u.setUserLogin(registrationRequest.getLogin());
        u.setUserName(registrationRequest.getUserName());
        userService.createUser(u);
        return "OK";
    }

//    @PostMapping("/auth")
//        public AuthResponse auth(@RequestBody AuthRequest request) {
//        UserEntity userEntity = userService.findByLoginAndPassword(request.getLogin(), request.getPassword());
//        String token = jwtProvider.generateToken(userEntity.getUserLogin());
//        return new AuthResponse(token);
//    return null;
//    }
}