package ru.baryshev.kirill.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Data
@Table(name = "probation_statuses")
@AllArgsConstructor
@NoArgsConstructor
public class ProbationStatusesEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private String def;

    @Column(name = "navi_date", insertable = false)
    @JsonIgnore
    private final Date naviDate = new Date();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProbationStatusesEntity that = (ProbationStatusesEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(def, that.def);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, def);
    }
}
