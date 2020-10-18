package ru.baryshev.kirill.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.baryshev.kirill.Repositories.UsersRepository;

@Service
@Transactional
public class UsersService {

    @Autowired
    UsersRepository usersRepository;

}
