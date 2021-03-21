package ru.baryshev.kirill.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Entity
@Getter
@Setter
@Table(name = "control_point_histories")
public class ControlPointsHistoriesEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "control_point_id")
    private ControlPointsEntity controllPointId;

    @OneToOne
    @JoinColumn(name = "collegues_info_id")
    private ColleguesInfoEntity colleguesInfoId;

    @OneToOne
    @JoinColumn(name = "control_point_status_id")
    private ControlPointStatusesEntity controlPointStatusId;

    @Column(name = "comment")
    private String comment;

    @Column (name = "update_date")
    private Date updateDate;

    @Column (name = "navi_date", insertable = false)
    private Date naviDate;
}
