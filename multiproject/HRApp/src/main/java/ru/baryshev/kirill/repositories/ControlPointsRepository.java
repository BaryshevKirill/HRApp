package ru.baryshev.kirill.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.baryshev.kirill.entities.ColleguesInfoEntity;
import ru.baryshev.kirill.entities.ControlPointsEntity;
import ru.baryshev.kirill.entities.ControlPointsHistoriesEntity;

import java.util.List;
import java.util.Optional;

@Repository
public interface ControlPointsRepository extends JpaRepository<ControlPointsEntity, Long> {

//    Optional<List<ControlPointsEntity>> ();
//    Optional<List<ControlPointsHistoriesEntity>> findByCollegueInfoId(Long id);

//    @Query(value = "SELECT t FROM collegues_info t WHERE t.user_id = :userId")
//    List<ColleguesInfoEntity> findAllByUserId(UserEntity userEntity);
//    List<ColleguesInfoEntity> findAllByUserId(@Param("userId") Long userId);
}
