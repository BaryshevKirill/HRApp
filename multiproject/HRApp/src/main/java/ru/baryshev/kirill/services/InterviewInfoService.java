package ru.baryshev.kirill.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.baryshev.kirill.dto.interviewinfo.ColleguesInfoDto;
import ru.baryshev.kirill.dto.users.UsersDto;
import ru.baryshev.kirill.entities.ColleguesInfoEntity;
import ru.baryshev.kirill.entities.UserEntity;
import ru.baryshev.kirill.repositories.ColleguesInfoRepository;
import ru.baryshev.kirill.repositories.DepartmentsRepository;
import ru.baryshev.kirill.repositories.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@Transactional
public class InterviewInfoService {

    @Autowired
    ColleguesInfoRepository colleguesInfoRepository;
    //
//    @Autowired
//    RoleRepository roleRepository;
//
    @Autowired
    UserRepository userRepository;
    //
    @Autowired
    DepartmentsRepository departmentsRepository;

    public ColleguesInfoEntity createInterviewInfo(ColleguesInfoEntity interviewInfo) {
        return colleguesInfoRepository.save(interviewInfo);
    }

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


    public List<ColleguesInfoDto> findByUserId(Long userId) {
        UserEntity userEntity = new UserEntity();
        userEntity.setId(userId);
        List<ColleguesInfoEntity> colleguesInfoEntity = colleguesInfoRepository.findByUserId(userEntity)
                .orElseThrow(() -> new NoSuchElementException("Not found in DB ColleguesInfoEntity with userId " + userId));
        List<ColleguesInfoDto> colleguesInfoDto = new ArrayList<>();

        colleguesInfoEntity.forEach((it) -> {
                    ColleguesInfoDto item = new ColleguesInfoDto();
                    item.setId(it.getId());
                    item.setUserId(UsersDto.CONVERTER.from(it.getUserId()));
                    item.setColleagueName(it.getColleagueName());
                    item.setPositionId(it.getPositionId());
                    item.setDepartmentId(it.getDeparmentId());
                    item.setProbationaryPeriodFrom(it.getProbationaryPeriodFrom());
                    item.setProbationaryPeriodTo(it.getProbationaryPeriodTo());
                    colleguesInfoDto.add(item);
                }
        );

        return colleguesInfoDto;
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
