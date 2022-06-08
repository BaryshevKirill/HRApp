package ru.baryshev.kirill.services;

import lombok.extern.log4j.Log4j2;
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
@Log4j2
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
                    if (it.getIsActual()) {
                        coleguesControlPointDto.setControlPoints(it.getControlPointStatusId().getDef(), it.getComment(), it.getControllPointId());
                    }
                }
        );
        return coleguesControlPointDto;
    }

    public void saveControlPointInfo(SaveInfoControlPointDto saveInfoControlPointDto) {
        ControlPointsEntity controlPointsEntity = controlPointsRepository.findById(saveInfoControlPointDto.getControlPointId())
                .orElseThrow(NoSuchElementException::new);
        ControlPointStatusesEntity byDef = controlPointStatusesRepository.findByDef(saveInfoControlPointDto.getStatus());
        ColleguesInfoEntity colleguesInfoEntity = colleguesInfoRepository.findById(saveInfoControlPointDto.getColegueId())
                .orElseThrow(NoSuchElementException::new);

        try {
            ControlPointsHistoriesEntity byControllPointIdAndCollegueInfoId = controlPointHistoryRepository
                    .findByControllPointIdAndCollegueInfoIdAndIsActual(controlPointsEntity, colleguesInfoEntity, true)
                    .orElseThrow(() -> new NoSuchElementException(String.format("Отсутствует более ранняя запись с control_point_id = %s и colegue_info_id = %s", controlPointsEntity.getId(), colleguesInfoEntity.getId())));

            if (byControllPointIdAndCollegueInfoId.getComment().equals(saveInfoControlPointDto.getComment())
                    && byControllPointIdAndCollegueInfoId.getControlPointStatusId().getDef().equals(saveInfoControlPointDto.getStatus())) {
                log.info("Nothing to change for: " + saveInfoControlPointDto);
                return;
            }

            byControllPointIdAndCollegueInfoId.setIsActual(!byControllPointIdAndCollegueInfoId.getIsActual());
            byControllPointIdAndCollegueInfoId.setUpdateDate(new Date());
            controlPointHistoryRepository.save(byControllPointIdAndCollegueInfoId);
        } catch (NoSuchElementException ex) {
            ex.printStackTrace();
        }

        ControlPointsHistoriesEntity entity = new ControlPointsHistoriesEntity();
        entity.setControllPointId(controlPointsEntity);
        entity.setCollegueInfoId(colleguesInfoEntity);
        entity.setControlPointStatusId(byDef);
        entity.setComment(saveInfoControlPointDto.getComment());
        entity.setUpdateDate(new Date());
        controlPointHistoryRepository.save(entity);
    }
}
