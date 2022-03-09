package ru.baryshev.kirill.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.baryshev.kirill.dto.ColeguesControlPointDto;
import ru.baryshev.kirill.dto.SaveInfoControlPointDto;
import ru.baryshev.kirill.entities.ColleguesInfoEntity;
import ru.baryshev.kirill.entities.ControlPointStatusesEntity;
import ru.baryshev.kirill.entities.ControlPointsEntity;
import ru.baryshev.kirill.entities.ControlPointsHistoriesEntity;
import ru.baryshev.kirill.repositories.ColleguesInfoRepository;
import ru.baryshev.kirill.repositories.ControlPointHistoryRepository;
import ru.baryshev.kirill.repositories.ControlPointStatusesRepository;
import ru.baryshev.kirill.repositories.ControlPointsRepository;

import java.util.*;

@Service
public class ControlPointsHistoriesService {

    @Autowired
    ControlPointHistoryRepository controlPointHistoryRepository;

    @Autowired
    ControlPointsRepository controlPointsRepository;

    @Autowired
    ControlPointStatusesRepository controlPointStatusesRepository;

    @Autowired
    ColleguesInfoRepository colleguesInfoRepository;

    public ColeguesControlPointDto getControlPointByColegueId(Long id) {
        ColleguesInfoEntity colleguesInfoEntity = new ColleguesInfoEntity();
        colleguesInfoEntity.setId(id);
        List<ControlPointsHistoriesEntity> controlPointsHistoriesEntityList = controlPointHistoryRepository.findByCollegueInfoId(colleguesInfoEntity)
                .orElseThrow(NoSuchElementException::new);

        ColeguesControlPointDto coleguesControlPointDto = new ColeguesControlPointDto();
        coleguesControlPointDto.setColegueId(id);

        controlPointsHistoriesEntityList.forEach((it) -> {
            if(it.getIsActual()) {
                coleguesControlPointDto.setControlPoints(it.getControlPointStatusId().getDef(), it.getComment(), it.getControllPointId());
            }
                }
        );
        return coleguesControlPointDto;
    }

    public void saveControlPointInfo(SaveInfoControlPointDto saveInfoControlPointDto) {
        ControlPointsEntity byId = controlPointsRepository.findById(saveInfoControlPointDto.getControlPointId())
                .orElseThrow(NoSuchElementException::new);
        ControlPointStatusesEntity byDef = controlPointStatusesRepository.findByDef(saveInfoControlPointDto.getStatus());
        ColleguesInfoEntity byId1 = colleguesInfoRepository.findById(saveInfoControlPointDto.getColegueId())
                .orElseThrow(NoSuchElementException::new);

        try {
            ControlPointsHistoriesEntity byControllPointIdAndCollegueInfoId = controlPointHistoryRepository.findByControllPointIdAndCollegueInfoIdAndIsActual(byId, byId1, true)
                    .orElseThrow(() -> new NoSuchElementException(String.format("Отсутствует более ранняя запись с control_point_id = %s и colegue_info_id = %s", byId.getId(), byId1.getId())));
            byControllPointIdAndCollegueInfoId.setIsActual(!byControllPointIdAndCollegueInfoId.getIsActual());
            controlPointHistoryRepository.save(byControllPointIdAndCollegueInfoId);
        } catch (NoSuchElementException ex) {
            ex.printStackTrace();
        }

        ControlPointsHistoriesEntity entity = new ControlPointsHistoriesEntity();
        entity.setControllPointId(byId);
        entity.setCollegueInfoId(byId1);
        entity.setControlPointStatusId(byDef);
        entity.setComment(saveInfoControlPointDto.getComment());
        entity.setUpdateDate(new Date());
        controlPointHistoryRepository.save(entity);
    }


}
