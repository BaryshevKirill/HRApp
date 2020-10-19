package ru.baryshev.kirill.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.baryshev.kirill.dto.users.FullUserDto;
import ru.baryshev.kirill.entities.UserEntity;
import ru.baryshev.kirill.repositories.RoleRepository;
import ru.baryshev.kirill.repositories.UserRepository;
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

    public UsersDto createUser(FullUserDto fullUserDto) {
        UserEntity usersEntity;
        usersEntity = UserEntity.CONVERTER.from(fullUserDto);
        usersEntity.setUserRole(roleRepository.findByName("ROLE_USER").getId());
        usersEntity.setUserPassword(passwordEncoder.encode(fullUserDto.getUserPassword()));
        usersEntity = userRepository.save(usersEntity);
        return UsersDto.CONVERTER.from(usersEntity);
    }

    public FullUserDto findByName(String userName) {
        return FullUserDto.CONVERTER.from(userRepository
                .findByUserName(userName)
                .orElseThrow(() -> new NoSuchElementException("Not found in DB users with name " + userName)));
    }

    public UsersDto findById(Long userId) {
        return UsersDto.CONVERTER.from(userRepository
                .findById(userId)
                .orElseThrow(() -> new NoSuchElementException("Not found in DB users with ID " + userId)));
    }

    public FullUserDto findByLogin(String login) {
        return FullUserDto.CONVERTER.from(userRepository
                .findByUserLogin(login));
    }

    public FullUserDto findByLoginAndPassword(String login, String password) {

        FullUserDto userEntity = findByLogin(login);
        if (userEntity != null) {
            if (passwordEncoder.matches(password, userEntity.getUserPassword())) {
                return userEntity;
            }
        }
        return null;
    }
}
