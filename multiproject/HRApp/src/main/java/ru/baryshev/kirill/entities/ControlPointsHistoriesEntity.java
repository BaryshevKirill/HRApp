package ru.baryshev.kirill.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.NamedNativeQuery;
import org.hibernate.annotations.SQLUpdate;
import org.hibernate.annotations.Where;

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

//@AllArgsConstructor
//@NoArgsConstructor
@Entity
@Getter
@Setter
@Table(name = "control_point_histories")
//@Where(clause = "is_actual = false")
public class ControlPointsHistoriesEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "control_point_id")
    private ControlPointsEntity controllPointId;

    @ManyToOne
    @JoinColumn(name = "collegue_info_id")
    private ColleguesInfoEntity collegueInfoId;

    @OneToOne
    @JoinColumn(name = "control_point_status_id")
    private ControlPointStatusesEntity controlPointStatusId;

    @Column(name = "comment")
    private String comment;

    @Column (name = "update_date")
    private Date updateDate;

    @Column (name = "navi_date", insertable = false)
    private Date naviDate;

    @Column (name = "is_actual", insertable = false)
    @JsonIgnore
    private Boolean isActual;
}
