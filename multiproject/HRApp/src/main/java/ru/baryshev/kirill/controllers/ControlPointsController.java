package ru.baryshev.kirill.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.baryshev.kirill.dto.SaveInfoControlPointDto;
import ru.baryshev.kirill.entities.ControlPointsEntity;
import ru.baryshev.kirill.services.ControlPointsHistoriesService;
import ru.baryshev.kirill.services.ControlPointsService;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/ControlPoints")
@CrossOrigin(origins = "http://localhost:4200")
public class ControlPointsController {

    @Autowired
    ControlPointsHistoriesService controlPointsHistoriesService;

    @Autowired
    ControlPointsService controlPointsService;

    @GetMapping("/findByColegueId/{id}")
    public ResponseEntity<?> getControlPointsByColegueId(@PathVariable(value = "id") Long id) {
        try {
            return ResponseEntity.ok(controlPointsHistoriesService.getControlPointByColegueId(id));
        } catch (NoSuchElementException ex) {
            return ResponseEntity.noContent().build();
        }
    }

    @GetMapping("/getAllControlPointsDef")
    public ResponseEntity<?> getAllControlPointsDef() {
        try {
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
        } catch (NoSuchElementException ex) {
            return ResponseEntity.noContent().build();
        }
    }
}
