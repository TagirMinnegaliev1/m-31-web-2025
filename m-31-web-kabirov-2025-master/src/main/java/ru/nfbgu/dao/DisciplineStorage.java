package ru.nfbgu.dao;

import ru.nfbgu.dao.model.DisciplineModel;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface DisciplineStorage {
    void insert(DisciplineModel discipline);

    List<DisciplineModel> getAllDisciplines();

    Optional<DisciplineModel> getDisciplineById(String id);

    List<DisciplineModel> getDisciplinesByTeacherId(UUID teacherId);
}
