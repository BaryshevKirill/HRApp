package ru.baryshev.kirill.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.baryshev.kirill.models.Role;

@Repository
public interface RoleEntityRepository extends JpaRepository<Role, Long> {

    Role findByName(String name);

}
