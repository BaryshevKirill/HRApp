package ru.baryshev.kirill.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.baryshev.kirill.entities.ColleguesInfoEntity;
import ru.baryshev.kirill.entities.ProbationStatusesEntity;
import ru.baryshev.kirill.entities.UserEntity;

import java.util.List;
import java.util.Optional;

@Repository
public interface ColleguesInfoRepository extends JpaRepository<ColleguesInfoEntity, Long> {

    Optional<List<ColleguesInfoEntity>> findByUserId(UserEntity userEntity);


    Optional<List<ColleguesInfoEntity>> findByUserIdAndProbationStatusIdIn(
            @Param("userId") UserEntity userEntity,
            @Param("probationStatusId") List<ProbationStatusesEntity> selectedStatesCollect);

//    Optional<ColleguesInfoEntity> findById(UserEntity userEntity);


    //    @Query(value = "SELECT t FROM collegues_info t WHERE t.user_id = :userId")
    List<ColleguesInfoEntity> findAllByUserId(UserEntity userEntity);
//    List<ColleguesInfoEntity> findAllByUserId(@Param("userId") Long userId);
}
