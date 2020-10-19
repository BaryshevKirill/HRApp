package ru.baryshev.kirill.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.baryshev.kirill.Repositories.InterviewInfoRepository;
import ru.baryshev.kirill.models.InterviewInfo;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class InterviewInfoService {

    @Autowired
    InterviewInfoRepository interviewInfoRepository;

    public InterviewInfo createInterviewInfo(InterviewInfo interviewInfo) {
        return interviewInfoRepository.save(interviewInfo);
    }

    public InterviewInfo findByUserId(Long userId) {
        return interviewInfoRepository.findByUserId(userId).orElseThrow(() -> new NoSuchElementException("Not found in DB InterviewInfo with userId " + userId));
    }

    public InterviewInfo findById(Long id) {
        return interviewInfoRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Not found in DB InterviewInfo with ID " + id));
    }

    public List<InterviewInfo> findAllByUserId(Long userId) {
        return interviewInfoRepository.findAllByUserId(userId);
    }
}
