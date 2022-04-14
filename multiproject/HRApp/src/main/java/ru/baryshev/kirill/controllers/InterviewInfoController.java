package ru.baryshev.kirill.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.baryshev.kirill.dto.interviewinfo.ColleguesInfoDto;
import ru.baryshev.kirill.dto.request.UpdateColegueInfoDto;
import ru.baryshev.kirill.entities.ColleguesInfoEntity;
import ru.baryshev.kirill.services.InterviewInfoService;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/InterviewInfo")
@CrossOrigin(origins = "http://localhost:4200")
public class InterviewInfoController {

    @Autowired
    InterviewInfoService interviewInfoService;

    /**
     * Создание новой записи о собесе
     * //     * @param createUserDto
     *
     * @return
     */
    @PostMapping("/create")
    public ResponseEntity<?> createInterviewInfo(@Valid @RequestBody ColleguesInfoEntity createUserDto) {
        try {
            interviewInfoService.createInterviewInfo(createUserDto);
            return ResponseEntity.status(HttpStatus.OK).body(createUserDto);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }

    /**
     * @param userId
     * @return
     */
    @GetMapping("/findByUserId/{userName}")
    public ResponseEntity<List<ColleguesInfoDto>> findUserByUserName(
            @PathVariable(value = "userName") Long userId,
            @RequestParam(value = "selectedStates", required = false) String selectedStates) {
        List<ColleguesInfoDto> list = interviewInfoService.findByUserId(userId, selectedStates);
        if (list.size() == 0) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(list);
        }
        return ResponseEntity.status(HttpStatus.OK).body(list);
    }

    @PostMapping("/updateColegueInfo")
    public ResponseEntity<ColleguesInfoEntity> updateInterviewInfo(@Valid @RequestBody ColleguesInfoEntity dto) {
        ColleguesInfoEntity newColegueInfo = interviewInfoService.updateInterviewInfo(dto);
        return ResponseEntity.ok(newColegueInfo);
    }

//    @GetMapping("/findByUserId/{userName}")
//    public List<ColleguesInfoDto> findUserByUserName(@PathVariable(value = "userName") Long userId) {
//        return interviewInfoService.findByUserId(userId);
//    }

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
