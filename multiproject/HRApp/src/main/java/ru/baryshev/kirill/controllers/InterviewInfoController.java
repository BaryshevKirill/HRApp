package ru.baryshev.kirill.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.baryshev.kirill.dto.interviewinfo.ColleguesInfoDto;
import ru.baryshev.kirill.entities.ColleguesInfoEntity;
import ru.baryshev.kirill.services.InterviewInfoService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/InterviewInfo")
@CrossOrigin(origins = "http://localhost:4200")
public class InterviewInfoController {

    @Autowired
    InterviewInfoService interviewInfoService;

    /**
     * Создание новой записи о собесе
//     * @param createUserDto
     * @return
     */
    @PostMapping("/create")
    public ColleguesInfoEntity createInterviewInfo(@Valid @RequestBody ColleguesInfoEntity createUserDto) {
        interviewInfoService.createInterviewInfo(createUserDto);
        return createUserDto;
    }
//    @PostMapping("/create")
//    public ColleguesInfoEntity createInterviewInfo(@Valid @RequestBody ColleguesInfoDto colleguesInfoDto) {
//        return interviewInfoService.createInterviewInfo(colleguesInfoDto);
//    }

    /**
     *
     * @param userId
     * @return
     */
    @GetMapping("/findByUserId/{userName}")
    public List<ColleguesInfoDto> findUserByUserName(@PathVariable(value = "userName") Long userId) {
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

    @GetMapping("/findAll")
    public List<ColleguesInfoEntity> findAll() {
        return interviewInfoService.findAll();
    }
}
