package ru.baryshev.kirill.RestControllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import ru.baryshev.kirill.Services.UsersService;

@RestController
public class UserController {

    @Autowired
    UsersService usersService;


}
