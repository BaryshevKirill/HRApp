package ru.baryshev.kirill.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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

//    TODO наверно лучше этоубрать хачем тут роль сделать енум
    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public UsersDto createUser(UserEntity usersEntity) {
//        usersEntity.setUserRole(roleRepository.findByName("ROLE_USER").getId());
        usersEntity.setRoleEntity(roleRepository.findByName("ROLE_USER"));
        usersEntity.setUserPassword(passwordEncoder.encode(usersEntity.getUserPassword()));
        usersEntity.setUserPassword(usersEntity.getUserPassword());
        usersEntity = userRepository.save(usersEntity);
        return UsersDto.CONVERTER.from(usersEntity);
    }

    public UserEntity findByName(String userName) {
        return userRepository
                .findByUserName(userName)
                .orElseThrow(() -> new NoSuchElementException("Not found in DB users with name " + userName));
    }

    public UsersDto findById(Long userId) {
        return UsersDto.CONVERTER.from(userRepository
                .findById(userId)
                .orElseThrow(() -> new NoSuchElementException("Not found in DB users with ID " + userId)));
    }

    public UserEntity findByLogin(String login) {
        return userRepository
                .findByUserLogin(login);
    }

    public UserEntity findByLoginAndPassword(String login, String password) {

        UserEntity userEntity = findByLogin(login);
        if (userEntity != null) {
            if (passwordEncoder.matches(password, userEntity.getUserPassword())) {
                return userEntity;
            }
        }
        return null;
    }
}
