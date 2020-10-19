package ru.baryshev.kirill.RestControllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.baryshev.kirill.Services.UsersService;
import ru.baryshev.kirill.dto.users.CreateUserDto;
import ru.baryshev.kirill.dto.users.UsersDto;

import javax.validation.Valid;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UsersService usersService;

    @PostMapping("/create")
    public CreateUserDto createUser(@Valid @RequestBody CreateUserDto createUserDto) {
        usersService.createUser(createUserDto);
        return createUserDto;
    }

    @GetMapping("/findByName/{name}")
    public CreateUserDto findUserByName(@PathVariable(value = "name") String userName) {
        return usersService.findByName(userName);
    }

    @GetMapping("/findById/{id}")
    public UsersDto findUserById(@PathVariable(value = "id") Long userId) {
        return usersService.findById(userId);
    }


}
