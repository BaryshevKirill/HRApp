package ru.baryshev.kirill.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Repository;
import ru.baryshev.kirill.entities.RoleEntity;
//TODO переписать все через Optional
@Repository
public interface RoleRepository extends JpaRepository<RoleEntity, Long> {

    RoleEntity findByName(String name);

//    RoleEntity findById(Long id);

}
