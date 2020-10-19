package ru.baryshev.kirill.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.baryshev.kirill.entities.InterviewInfoEntity;
import ru.baryshev.kirill.repositories.InterviewInfoRepository;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class InterviewInfoService {

    @Autowired
    InterviewInfoRepository interviewInfoRepository;

    public InterviewInfoEntity createInterviewInfo(InterviewInfoEntity interviewInfo) {
        return interviewInfoRepository.save(interviewInfo);
    }

    public InterviewInfoEntity findByUserId(Long userId) {
        return interviewInfoRepository.findByUserId(userId).orElseThrow(() -> new NoSuchElementException("Not found in DB InterviewInfoEntity with userId " + userId));
    }

    public InterviewInfoEntity findById(Long id) {
        return interviewInfoRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Not found in DB InterviewInfoEntity with ID " + id));
    }

    public List<InterviewInfoEntity> findAllByUserId(Long userId) {
        return interviewInfoRepository.findAllByUserId(userId);
    }
}
