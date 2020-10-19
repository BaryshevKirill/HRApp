package ru.baryshev.kirill.RestControllers;

import lombok.Data;

@Data
public class AuthRequest {
    private String login;
    private String password;
}