package ru.nfbgu.dao.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "student")
public class StudentModel {
    @Id
    UUID id;

    String name;
    String email;

    @Column(name = "password")
    String passHash;

    @Column(name = "group_name")
    String group;

}
