package ru.baryshev.kirill.controllers.auth;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class RegistrationRequest {

    @NotEmpty
    private String userName;

    @NotEmpty
    private String login;

    @NotEmpty
    private String password;
}