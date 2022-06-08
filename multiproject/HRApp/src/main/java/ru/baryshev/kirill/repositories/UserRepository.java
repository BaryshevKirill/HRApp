package ru.baryshev.kirill.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.baryshev.kirill.entities.UserEntity;

import java.util.Optional;

//TODO переписать все через Optional

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findByUserName(String userName);

    UserEntity findByUserLoginAndUserPassword(@Param("UserLogin") String login, @Param("UserPassword") String password);

    UserEntity findByUserLogin(@Param("UserLogin") String login);
}
