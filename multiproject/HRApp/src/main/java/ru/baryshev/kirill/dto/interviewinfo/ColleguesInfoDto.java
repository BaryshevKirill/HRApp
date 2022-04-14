package ru.baryshev.kirill.dto.interviewinfo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.baryshev.kirill.dto.users.UsersDto;
import ru.baryshev.kirill.entities.ColleguesInfoEntity;
import ru.baryshev.kirill.entities.DepartmentsEntity;
import ru.baryshev.kirill.entities.PositionsEntity;
import ru.baryshev.kirill.entities.ProbationStatusesEntity;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ColleguesInfoDto implements Serializable {

    private Long id;

    private UsersDto userId;

    private String colleagueName;
//    private ColleguesInfoEntity colleagueName;

    private PositionsEntity positionId;

    private DepartmentsEntity departmentId;

    private ProbationStatusesEntity probationStatusId;

    @JsonFormat(pattern="dd.MM.yyyy")
    private Date probationaryPeriodFrom;

    @JsonFormat(pattern="dd.MM.yyyy")
    private Date probationaryPeriodTo;



//    public static Converter CONVERTER = Mappers.getMapper(Converter.class);

//    @Mapper
//    public interface Converter {
//        @Mapping(target = "userId", source = )
//        InterviewInfoDto from(ColleguesInfoEntity user);

//        @Mapping(target = "userId", source = "id")
//        @Mapping(target = "userName")
//        @Mapping(target = "userLogin")
//        ColleguesInfoEntity to(InterviewInfoDto user);
//    }
}
