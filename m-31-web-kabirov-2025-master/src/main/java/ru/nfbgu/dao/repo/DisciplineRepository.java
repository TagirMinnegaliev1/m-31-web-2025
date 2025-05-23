package ru.nfbgu.dao.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nfbgu.dao.model.DisciplineModel;

import java.util.List;
import java.util.UUID;

public interface DisciplineRepository extends JpaRepository<DisciplineModel, UUID> {
    List<DisciplineModel> findAllByTeacherId(UUID teacherId);
}
