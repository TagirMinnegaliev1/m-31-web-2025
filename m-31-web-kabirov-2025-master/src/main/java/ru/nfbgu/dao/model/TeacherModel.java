package ru.nfbgu.dao.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "teacher")
public class TeacherModel {
    @Id
    UUID id;

    String name;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "teacher", fetch = FetchType.LAZY)
    List<DisciplineModel> disciplines;
}
