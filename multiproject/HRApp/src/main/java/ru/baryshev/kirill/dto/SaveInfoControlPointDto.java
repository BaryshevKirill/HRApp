package ru.baryshev.kirill.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SaveInfoControlPointDto{

    Long userId;

    Long colegueId;

    String status;

    String comment;

    Long controlPointId;
}
