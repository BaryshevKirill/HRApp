package ru.baryshev.kirill.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.baryshev.kirill.entities.UserEntity;
import ru.baryshev.kirill.repositories.RoleRepository;
import ru.baryshev.kirill.repositories.UserRepository;
import ru.baryshev.kirill.dto.users.CreateUserDto;
import ru.baryshev.kirill.dto.users.UsersDto;

import java.util.NoSuchElementException;

@Service
@Transactional
public class UsersService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public UsersDto createUser(CreateUserDto createUserDto) {
        UserEntity usersEntity;
        usersEntity = UserEntity.CONVERTER.from(createUserDto);
        usersEntity.setUserRole(roleRepository.findByName("ROLE_USER").getId());
        usersEntity.setUserPassword(passwordEncoder.encode(createUserDto.getUserPassword()));
        usersEntity = userRepository.save(usersEntity);
        return UsersDto.CONVERTER.from(usersEntity);
    }

    public CreateUserDto findByName(String userName) {
        return CreateUserDto.CONVERTER.from(userRepository
                .findByUserName(userName)
                .orElseThrow(() -> new NoSuchElementException("Not found in DB users with name " + userName)));
    }

    public UsersDto findById(Long userId) {
        return UsersDto.CONVERTER.from(userRepository
                .findById(userId)
                .orElseThrow(() -> new NoSuchElementException("Not found in DB users with ID " + userId)));
    }

    public CreateUserDto findByLogin(String login) {
        return CreateUserDto.CONVERTER.from(userRepository
                .findByUserLogin(login));
    }

    public CreateUserDto findByLoginAndPassword(String login, String password) {

        CreateUserDto userEntity = findByLogin(login);
        if (userEntity != null) {
            if (passwordEncoder.matches(password, userEntity.getUserPassword())) {
                return userEntity;
            }
        }
        return null;
    }
}
