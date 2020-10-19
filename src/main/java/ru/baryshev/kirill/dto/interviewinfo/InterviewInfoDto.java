package ru.baryshev.kirill.dto.interviewinfo;

import ru.baryshev.kirill.entities.UserEntity;

import java.util.Date;

public class InterviewInfoDto {

    private UserEntity userId;

    private String colleagueName;

    private Date startDate;

    private Integer daysBeforeInterview;

    private Date interviewDate;

    private Date naviDate;
}
