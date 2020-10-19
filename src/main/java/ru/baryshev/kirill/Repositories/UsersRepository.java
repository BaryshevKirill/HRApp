package ru.baryshev.kirill.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.baryshev.kirill.models.Users;

import java.util.Optional;

@Repository
public interface UsersRepository extends JpaRepository<Users, Long> {

    Optional<Users> findByUserName(String userName);

    Users findByUserLoginAndUserPassword(@Param("UserLogin") String login, @Param("UserPassword") String password);

    Users findByUserLogin(@Param("UserLogin") String login);
}
