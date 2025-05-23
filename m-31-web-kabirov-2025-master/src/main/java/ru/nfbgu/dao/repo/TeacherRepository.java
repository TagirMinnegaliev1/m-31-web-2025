package ru.nfbgu.dao.repo;

import org.springframework.data.domain.Limit;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.nfbgu.dao.model.TeacherModel;

import java.util.List;
import java.util.UUID;

public interface TeacherRepository extends JpaRepository<TeacherModel, UUID> {
    List<TeacherModel> findByOrderByNameAsc(Limit limit);
}
