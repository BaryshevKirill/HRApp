package ru.baryshev.kirill.objects;

import lombok.AllArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Setter
public class Users {

    /**
     * User name
     */
    private final String userName;
    private final String userPassword;
}
