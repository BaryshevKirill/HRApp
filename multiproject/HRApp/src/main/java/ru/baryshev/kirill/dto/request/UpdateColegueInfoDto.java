package ru.baryshev.kirill.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.baryshev.kirill.dto.users.UsersDto;
import ru.baryshev.kirill.entities.DepartmentsEntity;
import ru.baryshev.kirill.entities.PositionsEntity;
import ru.baryshev.kirill.entities.ProbationStatusesEntity;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateColegueInfoDto {

    private Long id;

    private UsersDto userId;

    private String colleagueName;

    private Long positionId;

    private Long departmentId;

    private Long probationStatusId;

    @JsonFormat(pattern="dd.MM.yyyy")
    private Date probationaryPeriodFrom;

    @JsonFormat(pattern="dd.MM.yyyy")
    private Date probationaryPeriodTo;
}
