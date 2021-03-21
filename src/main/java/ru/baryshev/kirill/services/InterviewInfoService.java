package ru.baryshev.kirill.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.baryshev.kirill.entities.ColleguesInfoEntity;
import ru.baryshev.kirill.repositories.InterviewInfoRepository;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class InterviewInfoService {

    @Autowired
    InterviewInfoRepository interviewInfoRepository;

    public ColleguesInfoEntity createInterviewInfo(ColleguesInfoEntity interviewInfo) {
        return interviewInfoRepository.save(interviewInfo);
    }

    public ColleguesInfoEntity findByUserId(Long userId) {
        return interviewInfoRepository.findByUserId(userId).orElseThrow(() -> new NoSuchElementException("Not found in DB ColleguesInfoEntity with userId " + userId));
    }

    public ColleguesInfoEntity findById(Long id) {
        return interviewInfoRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Not found in DB ColleguesInfoEntity with ID " + id));
    }

    public List<ColleguesInfoEntity> findAllByUserId(Long userId) {
        return interviewInfoRepository.findAllByUserId(userId);
    }
}
