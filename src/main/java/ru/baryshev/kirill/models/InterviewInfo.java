package ru.baryshev.kirill.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "interview_info")
@Getter
@Setter
@AllArgsConstructor
public class InterviewInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users userId;

    @Column(name = "colleague_name", insertable = false)
    private String colleagueName;

    @Column(name = "colleague_start_date", insertable = false)
    private Date startDate;

    @Column(name = "days_before_interview", insertable = false)
    private Integer daysBeforeInterview;

    @Column(name = "interview_date", insertable = false)
    private Date interviewDate;

    @Column(name = "navi_date", insertable = false)
    private Date naviDate;
}
