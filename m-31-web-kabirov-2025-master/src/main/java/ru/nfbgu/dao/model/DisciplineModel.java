package ru.nfbgu.dao.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "discipline")
public class DisciplineModel {
    @Id
    UUID id;
    String name;

    @Column(name = "teacher_id")
    UUID teacherId;

    String temp;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "teacher_id", insertable = false, updatable = false)
    TeacherModel teacher;
}
