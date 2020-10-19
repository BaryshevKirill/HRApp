package ru.baryshev.kirill.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.baryshev.kirill.entities.InterviewInfoEntity;

import java.util.List;
import java.util.Optional;

@Repository
public interface InterviewInfoRepository extends JpaRepository<InterviewInfoEntity, Long> {

    Optional<InterviewInfoEntity> findByUserId(Long userId);

   // @Query("SELECT t FROM interview_info t WHERE t.user_id = ?1")
    List<InterviewInfoEntity> findAllByUserId(Long userId);
}
