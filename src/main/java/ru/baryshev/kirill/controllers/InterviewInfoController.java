package ru.baryshev.kirill.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.baryshev.kirill.entities.InterviewInfoEntity;
import ru.baryshev.kirill.services.InterviewInfoService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/InterviewInfoEntity")
public class InterviewInfoController {

    @Autowired
    InterviewInfoService interviewInfoService;

    @PostMapping("/create")
    public InterviewInfoEntity createInterviewInfo(@Valid @RequestBody InterviewInfoEntity createUserDto) {
        interviewInfoService.createInterviewInfo(createUserDto);
        return createUserDto;
    }

    @GetMapping("/findByUserId/{userId}")
    public InterviewInfoEntity findUserByUserName(@PathVariable(value = "userName") Long userId) {
        return interviewInfoService.findByUserId(userId);
    }

    @GetMapping("/findById/{id}")
    public InterviewInfoEntity findUserById(@PathVariable(value = "id") Long id) {
        return interviewInfoService.findById(id);
    }

    @GetMapping("/findAllByUserId/{userId}")
    public List<InterviewInfoEntity> findAllByUserId(@PathVariable(value = "userId") Long userId) {
        return interviewInfoService.findAllByUserId(userId);
    }
}
