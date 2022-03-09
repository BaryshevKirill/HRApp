package ru.baryshev.kirill.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.baryshev.kirill.entities.DepartmentsEntity;
import ru.baryshev.kirill.entities.UserEntity;

import java.util.Optional;

@Repository
public interface DepartmentsRepository extends JpaRepository<DepartmentsEntity, Long> {

    Optional<DepartmentsEntity> findByDef(String def);

    Optional<DepartmentsEntity> findById(Long id);
}
