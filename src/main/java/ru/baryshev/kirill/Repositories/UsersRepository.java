package ru.baryshev.kirill.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.baryshev.kirill.models.Users;

@Repository
public interface UsersRepository extends JpaRepository <Users,Long> {
}
