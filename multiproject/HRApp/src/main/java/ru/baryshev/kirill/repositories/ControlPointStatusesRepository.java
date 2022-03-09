package ru.baryshev.kirill.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.baryshev.kirill.entities.ControlPointStatusesEntity;
import ru.baryshev.kirill.entities.ControlPointsEntity;

@Repository
public interface ControlPointStatusesRepository extends JpaRepository<ControlPointStatusesEntity, Long> {

    ControlPointStatusesEntity findByDef(String def);

//    Optional<List<ControlPointsEntity>> ();
//    Optional<List<ControlPointsHistoriesEntity>> findByCollegueInfoId(Long id);

//    @Query(value = "SELECT t FROM collegues_info t WHERE t.user_id = :userId")
//    List<ColleguesInfoEntity> findAllByUserId(UserEntity userEntity);
//    List<ColleguesInfoEntity> findAllByUserId(@Param("userId") Long userId);
}
