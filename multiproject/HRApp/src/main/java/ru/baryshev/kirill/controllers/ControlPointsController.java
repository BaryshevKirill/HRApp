package ru.baryshev.kirill.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.baryshev.kirill.dto.SaveInfoControlPointDto;
import ru.baryshev.kirill.entities.ControlPointsEntity;
import ru.baryshev.kirill.services.ControlPointsHistoriesService;
import ru.baryshev.kirill.services.ControlPointsService;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/ControlPoints")
@CrossOrigin(origins = "http://localhost:4200")
public class ControlPointsController {

    @Autowired
    ControlPointsHistoriesService controlPointsHistoriesService;

    @Autowired
    ControlPointsService controlPointsService;

    @GetMapping("/findByColegueId/{id}")
//    @GetMapping("/findByColegueId?{id}")
    public ResponseEntity<?> getControlPointsByColegueId(@PathVariable(value = "id")Long id) {
        try {
            return ResponseEntity.ok(controlPointsHistoriesService.getControlPointByColegueId(id));
        } catch (NoSuchElementException ex) {
            return ResponseEntity.noContent().build();
        }
    }

    @GetMapping("/getAllControlPointsDef")
    public ResponseEntity<?> getAllControlPointsDef() {
        try {
//            ResponseEntity<List<ControlPointsEntity>> ok = ResponseEntity.ok(controlPointsService.getControlPoints());
            return ResponseEntity.ok(controlPointsService.getControlPoints());
        } catch (NoSuchElementException ex) {
            return ResponseEntity.noContent().build();
        }
    }

    @PostMapping("/saveControlPointInfo")
    public ResponseEntity<?> saveControlPointInfo(@RequestBody SaveInfoControlPointDto saveInfoControlPointDto) {
        try {
            controlPointsHistoriesService.saveControlPointInfo(saveInfoControlPointDto);
            return ResponseEntity.ok(saveInfoControlPointDto);
        }catch (NoSuchElementException ex) {
            return ResponseEntity.noContent().build();
        }
    }

//    @PostMapping("/create")
//    public ColleguesInfoEntity createInterviewInfo(@Valid @RequestBody ColleguesInfoEntity createUserDto) {
//        interviewInfoService.createInterviewInfo(createUserDto);
//        return createUserDto;
//    }
////    @PostMapping("/create")
////    public ColleguesInfoEntity createInterviewInfo(@Valid @RequestBody ColleguesInfoDto colleguesInfoDto) {
////        return interviewInfoService.createInterviewInfo(colleguesInfoDto);
////    }
//
//    /**
//     *
//     * @param userId
//     * @return
//     */
//    @GetMapping("/findByUserId/{userName}")
//    public List<ColleguesInfoDto> findUserByUserName(@PathVariable(value = "userName") Long userId) {
//        return interviewInfoService.findByUserId(userId);
//    }
//
//    @GetMapping("/findById/{id}")
//    public ColleguesInfoEntity findUserById(@PathVariable(value = "id") Long id) {
//        return interviewInfoService.findById(id);
//    }
//
//    @GetMapping("/findAllByUserId/{userId}")
//    public List<ColleguesInfoEntity> findAllByUserId(@PathVariable(value = "userId") Long userId) {
//        return interviewInfoService.findAllByUserId(userId);
//    }
//
//    @GetMapping("/findAll")
//    public List<ColleguesInfoEntity> findAll() {
//        return interviewInfoService.findAll();
//    }
}
