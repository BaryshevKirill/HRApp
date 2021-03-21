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
@Table(name = "collegues_info")
public class ColleguesInfoEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity userId;

    @Column(name = "colleague_name")
    private String colleagueName;

    @OneToOne
    @JoinColumn(name = "position_id")
    private PositionsEntity positionId;

    @OneToOne
    @JoinColumn(name = "department_id")
    private PositionsEntity deparmentId;

    @Column(name = "probationary_period_from")
    private Date probationaryPeriodFrom;

    @Column(name = "probationary_period_to")
    private Date probationaryPeriodTo;

    @Column(name = "navi_date", insertable = false)
    private Date naviDate;
}
