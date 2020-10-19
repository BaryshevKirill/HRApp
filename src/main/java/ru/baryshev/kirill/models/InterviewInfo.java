package ru.baryshev.kirill.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "interview_info")
@Getter
@Setter
public class InterviewInfo implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @ManyToOne
//    @JoinColumn(name = "user_id")
//    private Users userId;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "colleague_name")
    private String colleagueName;

    @Column(name = "colleague_start_date")
    private Date startDate;

    @Column(name = "days_before_interview")
    private Integer daysBeforeInterview;

    @Column(name = "interview_date")
    private Date interviewDate;

    @Column(name = "navi_date", insertable = false)
    private Date naviDate;
}
