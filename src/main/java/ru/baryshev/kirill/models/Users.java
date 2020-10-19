package ru.baryshev.kirill.models;

import lombok.Getter;
import lombok.Setter;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import ru.baryshev.kirill.dto.users.CreateUserDto;

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
@Table(name = "users")
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "user_login")
    private String userLogin;

    @Column(name = "user_password")
    private String userPassword;

    @Column(name = "user_role_id")
    private Long userRole;

    @Column(name = "is_active")
    private Boolean isActive;

    @Column(name = "navi_date", insertable = false)
    private Date naviDate;

//    @OneToMany(mappedBy = "userId"/*, cascade = CascadeType.ALL*/)
//    private List<InterviewInfo> interviewInfo;

    public static Converter CONVERTER = Mappers.getMapper(Converter.class);

    @Mapper
    public interface Converter {
        @Mapping(target = "isActive", source = "isActive")
        @Mapping(target = "userName")
        @Mapping(target = "userLogin")
        @Mapping(target = "userPassword")
        Users from(CreateUserDto createUserDto);
    }
}
