package ru.baryshev.kirill.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.baryshev.kirill.entities.ControlPointsEntity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
//public class ControlPointDto implements Serializable {
//
//    private Long id;
//
//    private String status;
//
//    private String comment;
//
//    private Long colegueId;
//
//    private Long controlPointId;
//}
public class ColeguesControlPointDto implements Serializable {

    private Long colegueId;

    private List<ControlPoints> controlPoints = new ArrayList<>();

    @AllArgsConstructor
    @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
    public class ControlPoints implements Serializable {

        private String status;

        private String comment;

        private ControlPointsEntity controlPointInfo;
    }

    public void setControlPoints(String status, String comment, ControlPointsEntity controlPoints) {
        ControlPoints elem = new ControlPoints(status,comment,controlPoints);
        this.controlPoints.add(elem);
    }
}


