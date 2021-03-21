package ru.baryshev.kirill.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.baryshev.kirill.entities.ColleguesInfoEntity;
import ru.baryshev.kirill.services.InterviewInfoService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/InterviewInfo")
public class InterviewInfoController {

    @Autowired
    InterviewInfoService interviewInfoService;

    /**
     * Создание новой записи о собесе
     * @param createUserDto
     * @return
     */
    @PostMapping("/create")
    public ColleguesInfoEntity createInterviewInfo(@Valid @RequestBody ColleguesInfoEntity createUserDto) {
        interviewInfoService.createInterviewInfo(createUserDto);
        return createUserDto;
    }

    /**
     *
     * @param userId
     * @return
     */
    @GetMapping("/findByUserId/{userId}")
    public ColleguesInfoEntity findUserByUserName(@PathVariable(value = "userName") Long userId) {
        return interviewInfoService.findByUserId(userId);
    }

    @GetMapping("/findById/{id}")
    public ColleguesInfoEntity findUserById(@PathVariable(value = "id") Long id) {
        return interviewInfoService.findById(id);
    }

    @GetMapping("/findAllByUserId/{userId}")
    public List<ColleguesInfoEntity> findAllByUserId(@PathVariable(value = "userId") Long userId) {
        return interviewInfoService.findAllByUserId(userId);
    }
}
