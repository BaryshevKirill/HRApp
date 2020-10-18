package ru.baryshev.kirill.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;


@Getter
@Setter
@AllArgsConstructor
@Entity
@Table(name = "users")
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "user_name", insertable = false)
    private String userName;

    @Column(name = "user_login", insertable = false)
    private String userLogin;

    @Column(name = "password", insertable = false)
    private String userPassword;

    @Column(name = "is_active", insertable = false)
    private boolean isActive;

    @Column(name = "navi_date", insertable = false)
    private Date naviDate;

    @OneToMany(mappedBy = "userId", cascade = CascadeType.ALL)
//    @Where(clause = "id IN (select MAX(ph.id) from products_hist ph WHERE ph.is_visible = true group by ph.product_id)")
    private List<InterviewInfo> interviewInfo;
}
