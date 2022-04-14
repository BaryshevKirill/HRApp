package ru.baryshev.kirill.controllers.auth;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.*;
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
@Log4j2
@CrossOrigin(origins = "http://localhost:4200")
public class AuthController {

    private final AuthenticationManager authenticationManager;

    private final UsersService userService;

    private final JwtProvider jwtProvider;

    public AuthController(AuthenticationManager authenticationManager, UsersService userService, JwtProvider jwtProvider) {
        this.authenticationManager = authenticationManager;
        this.userService = userService;
        this.jwtProvider = jwtProvider;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest request) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getLogin(), request.getPassword()));
            UserEntity userEntity = userService.findByLogin(request.getLogin());
            String token = jwtProvider.generateToken(userEntity.getUserLogin(), userEntity.getRoleEntity().getName());
            Map<Object, Object> response = new HashMap<>();

//            TODO переделать с объектом
            response.put("userName", userEntity.getUserName());
            response.put("userId", userEntity.getId());
            response.put("userRole", userEntity.getRoleEntity());
            response.put("token", token);

            log.info("Login controller send: " + response);
            return ResponseEntity.ok(response);
        } catch (AuthenticationException e) {
//            return new ResponseEntity<>("Ivalid login/password", HttpStatus.FORBIDDEN);
            ResponseEntity<String> body = ResponseEntity
                    .status(HttpStatus.FORBIDDEN)
                    .body("Ivalid login/password");
            return ResponseEntity
                    .status(HttpStatus.FORBIDDEN)
                    .body("Ivalid login/password");
        }
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
}