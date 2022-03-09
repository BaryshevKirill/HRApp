package ru.baryshev.kirill.repositories;

import org.hibernate.annotations.SQLUpdate;
import org.hibernate.annotations.Where;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.baryshev.kirill.entities.ColleguesInfoEntity;
import ru.baryshev.kirill.entities.ControlPointsEntity;
import ru.baryshev.kirill.entities.ControlPointsHistoriesEntity;

import java.util.List;
import java.util.Optional;

@Repository
public interface ControlPointHistoryRepository extends JpaRepository<ControlPointsHistoriesEntity, Long> {

    Optional<List<ControlPointsHistoriesEntity>> findByCollegueInfoId(ColleguesInfoEntity collegueInfoId);

//    @Override
//    @Where(clause = "where ")
//    Optional<ControlPointsHistoriesEntity> findById(Long aLong);

//    @Where(clause = "is_actual = false")
//    Optional<List<ControlPointsHistoriesEntity>> findByControllPointIdAndCollegueInfoId(ControlPointsEntity controllPointId, ColleguesInfoEntity collegueInfoId);
    Optional<ControlPointsHistoriesEntity> findByControllPointIdAndCollegueInfoIdAndIsActual(ControlPointsEntity controllPointId, ColleguesInfoEntity collegueInfoId, Boolean isActual);


    //    @Modifying
//    @Query("update control_point_histories cph set cph.is_active = false, cph.update_date = current_date where cph.id = :id")
//    @SQLUpdate("update ControlPointHistoryRepository cph set cph.is_active = false, cph.update_date = current_date where cph.id = :id")
//    void updateIsActive(@Param("id") Long id);
//    Optional<List<ControlPointsHistoriesEntity>> findByCollegueInfoId(Long id);

//    @Query(value = "SELECT t FROM collegues_info t WHERE t.user_id = :userId")
//    List<ColleguesInfoEntity> findAllByUserId(UserEntity userEntity);
//    List<ColleguesInfoEntity> findAllByUserId(@Param("userId") Long userId);
}
