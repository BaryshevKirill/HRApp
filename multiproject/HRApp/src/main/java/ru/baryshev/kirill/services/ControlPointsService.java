package ru.baryshev.kirill.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.baryshev.kirill.dto.ColeguesControlPointDto;
import ru.baryshev.kirill.entities.ColleguesInfoEntity;
import ru.baryshev.kirill.entities.ControlPointsEntity;
import ru.baryshev.kirill.entities.ControlPointsHistoriesEntity;
import ru.baryshev.kirill.repositories.ControlPointHistoryRepository;
import ru.baryshev.kirill.repositories.ControlPointsRepository;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ControlPointsService {

    @Autowired
    ControlPointsRepository controlPointsRepository;

    public List<ControlPointsEntity> getControlPoints() {
//        List<ControlPointsEntity> all = controlPointsRepository.findAll();
        return controlPointsRepository.findAll();
    }

}
