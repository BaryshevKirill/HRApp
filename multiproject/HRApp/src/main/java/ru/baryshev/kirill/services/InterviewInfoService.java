package ru.baryshev.kirill.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.baryshev.kirill.dto.interviewinfo.ColleguesInfoDto;
import ru.baryshev.kirill.dto.users.UsersDto;
import ru.baryshev.kirill.entities.ColleguesInfoEntity;
import ru.baryshev.kirill.entities.ProbationStatusesEntity;
import ru.baryshev.kirill.entities.UserEntity;
import ru.baryshev.kirill.enums.ProbationStatusesEnum;
import ru.baryshev.kirill.repositories.ColleguesInfoRepository;
import ru.baryshev.kirill.repositories.DepartmentsRepository;
import ru.baryshev.kirill.repositories.UserRepository;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
public class InterviewInfoService {

    @Autowired
    ColleguesInfoRepository colleguesInfoRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    DepartmentsRepository departmentsRepository;

    public ColleguesInfoEntity createInterviewInfo(ColleguesInfoEntity interviewInfo) {
        return colleguesInfoRepository.save(interviewInfo);
    }

    public ColleguesInfoEntity updateInterviewInfo(ColleguesInfoEntity dto) {
        return colleguesInfoRepository.save(dto);
    }

//    public ColleguesInfoEntity updateInterviewInfo(UpdateColegueInfoDto dto) {
//        ColleguesInfoEntity value = colleguesInfoRepository.findById(dto.getId()).orElseThrow(NoSuchElementException::new);
//
//        return colleguesInfoRepository.save(interviewInfo);
//    }

//    public ColleguesInfoEntity createInterviewInfo(ColleguesInfoDto colleguesInfoDto) {
//        UserEntity userEntity = userRepository.findById(colleguesInfoDto.getUserId())
//                .orElseThrow(()->new NoSuchElementException(String.format("ALLO userId = %s nety", colleguesInfoDto.getUserId())));
//
//
//
//        DepartmentsEntity departmentsEntity = departmentsRepository.getOne(colleguesInfoDto.getDeparmentId());
//        ColleguesInfoEntity entity = new ColleguesInfoEntity();
//        return interviewInfoRepository.save(interviewInfo);
//    }


    public List<ColleguesInfoDto> findByUserId(Long userId, String selectedStates, String searchText) {
        List<ProbationStatusesEntity> collect;
        UserEntity userEntity = new UserEntity();
        userEntity.setId(userId);

        try {
            if (selectedStates == null || selectedStates.isEmpty()) {
                collect = Arrays.stream(ProbationStatusesEnum.values()).map((it) -> {
                    return new ProbationStatusesEntity(
                            it.getId(),
                            it.name(),
                            it.getDefRus()
                    );
                }).collect(Collectors.toList());
            } else {
                collect = Arrays.stream(selectedStates.split(","))
                        .map((it) -> {
                                    ProbationStatusesEnum probationStatusesEnum = ProbationStatusesEnum.valueOf(it);
                                    return new ProbationStatusesEntity(
                                            probationStatusesEnum.getId(),
                                            probationStatusesEnum.name(),
                                            probationStatusesEnum.getDefRus()
                                    );
                                }
                        )
                        .collect(Collectors.toList());
            }
            List<ColleguesInfoEntity> colleguesInfoEntity = colleguesInfoRepository.findByUserIdAndProbationStatusIdIn(userEntity, collect)
                    .orElseThrow(() -> new NoSuchElementException("Not found in DB ColleguesInfoEntity with userId " + userId));
            List<ColleguesInfoDto> colleguesInfoDto = new ArrayList<>();
            colleguesInfoEntity.forEach((it) -> {
                        ColleguesInfoDto item = new ColleguesInfoDto();
                        item.setId(it.getId());
                        item.setUserId(UsersDto.CONVERTER.from(it.getUserId()));
                        item.setColleagueName(it.getColleagueName());
                        item.setPositionId(it.getPositionId());
                        item.setDepartmentId(it.getDeparmentId());
                        item.setProbationStatusId(it.getProbationStatusId());
                        item.setProbationaryPeriodFrom(it.getProbationaryPeriodFrom());
                        item.setProbationaryPeriodTo(it.getProbationaryPeriodTo());
                        colleguesInfoDto.add(item);
                    }
            );
            return colleguesInfoDto;
        } catch (NoSuchElementException ex) {
            return Collections.emptyList();
        }
    }

    public ColleguesInfoEntity findById(Long id) {
        return colleguesInfoRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Not found in DB ColleguesInfoEntity with ID " + id));
    }

    public List<ColleguesInfoEntity> findAllByUserId(Long userId) {
        UserEntity userEntity = userRepository.findById(userId)
                .orElseThrow(() -> new NoSuchElementException(""));
        return colleguesInfoRepository.findAllByUserId(userEntity);
    }

    public List<ColleguesInfoEntity> findAll() {
        return colleguesInfoRepository.findAll();
    }
}
