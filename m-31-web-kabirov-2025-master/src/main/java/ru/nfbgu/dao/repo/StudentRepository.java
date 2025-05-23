package ru.nfbgu.dao.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nfbgu.dao.model.StudentModel;

import java.util.Optional;
import java.util.UUID;


public interface StudentRepository
        extends JpaRepository<StudentModel, UUID> {
    Optional<StudentModel> findFirstByEmail(String email);
}
