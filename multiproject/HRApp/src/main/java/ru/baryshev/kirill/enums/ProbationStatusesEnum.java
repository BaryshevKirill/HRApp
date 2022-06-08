package ru.baryshev.kirill.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ProbationStatusesEnum {
    PASSED(2L, "Пройден"),
    FAILED(3L,"Не пройден"),
    IN_PROGRESS(1L,"В процессе");

    final Long id;
    final String defRus;
}
