package ru.baryshev.kirill.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "collegues_info")
public class ColleguesInfoEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY/*, cascade = CascadeType.ALL*/)
    @JoinColumn(name = "user_id", nullable = false, referencedColumnName = "id")
    private UserEntity userId;

    @Column(name = "colleague_name")
    private String colleagueName;

    @OneToOne
    @JoinColumn(name = "position_id")
    private PositionsEntity positionId;

    @OneToOne
    @JoinColumn(name = "department_id")
    private DepartmentsEntity deparmentId;

    @Column(name = "probationary_period_from")
    @JsonFormat(pattern="dd.MM.yyyy")
    private Date probationaryPeriodFrom;

    @Column(name = "probationary_period_to")
    @JsonFormat(pattern="dd.MM.yyyy")
    private Date probationaryPeriodTo;

    @Column(name = "navi_date", insertable = false)
    private Date naviDate;
}
