package ru.baryshev.kirill.controllers.auth;

import lombok.Data;

@Data
public class AuthRequest {
    private String login;
    private String password;
}