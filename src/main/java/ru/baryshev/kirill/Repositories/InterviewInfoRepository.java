package ru.baryshev.kirill.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.baryshev.kirill.models.InterviewInfo;

import java.util.List;
import java.util.Optional;

@Repository
public interface InterviewInfoRepository extends JpaRepository<InterviewInfo, Long> {

    Optional<InterviewInfo> findByUserId(Long userId);

   // @Query("SELECT t FROM interview_info t WHERE t.user_id = ?1")
    List<InterviewInfo> findAllByUserId(Long userId);
}
