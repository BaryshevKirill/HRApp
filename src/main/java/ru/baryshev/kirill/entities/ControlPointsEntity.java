package ru.baryshev.kirill.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "control_points")
public class ControlPointsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String time_def;

    @Column
    private String def;

    @Column
    private Integer daysBeforeInterview;

    @Column(insertable = false)
    private Date naviDate;
}
