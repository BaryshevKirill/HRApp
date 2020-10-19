package ru.baryshev.kirill.RestControllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.baryshev.kirill.Services.InterviewInfoService;
import ru.baryshev.kirill.models.InterviewInfo;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/InterviewInfo")
public class InterviewInfoController {

    @Autowired
    InterviewInfoService interviewInfoService;

    @PostMapping("/create")
    public InterviewInfo createInterviewInfo(@Valid @RequestBody InterviewInfo createUserDto) {
        interviewInfoService.createInterviewInfo(createUserDto);
        return createUserDto;
    }

    @GetMapping("/findByUserId/{userId}")
    public InterviewInfo findUserByUserName(@PathVariable(value = "userName") Long userId) {
        return interviewInfoService.findByUserId(userId);
    }

    @GetMapping("/findById/{id}")
    public InterviewInfo findUserById(@PathVariable(value = "id") Long id) {
        return interviewInfoService.findById(id);
    }

    @GetMapping("/findAllByUserId/{userId}")
    public List<InterviewInfo> findAllByUserId(@PathVariable(value = "userId") Long userId) {
        return interviewInfoService.findAllByUserId(userId);
    }
}
