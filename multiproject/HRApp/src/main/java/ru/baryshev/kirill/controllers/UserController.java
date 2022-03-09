package ru.baryshev.kirill.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.baryshev.kirill.entities.UserEntity;
import ru.baryshev.kirill.services.UsersService;
import ru.baryshev.kirill.dto.users.UsersDto;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {

    @Autowired
    UsersService usersService;

    @PostMapping("/create")
    public UserEntity createUser(@Valid @RequestBody UserEntity userEntity) {
        usersService.createUser(userEntity);
        return userEntity;
    }

    @GetMapping("/findByName/{name}")
//    public UsersDto findUserByName(@PathVariable(value = "name") String userName) {
    public List<UsersDto> findUserByName(@PathVariable(value = "name") String userName) {
        return Arrays.asList(UsersDto.CONVERTER.from(usersService.findByName(userName)));
    }

    @GetMapping("/findById/{id}")
    public UsersDto findUserById(@PathVariable(value = "id") Long userId) {
        return usersService.findById(userId);
    }
}
