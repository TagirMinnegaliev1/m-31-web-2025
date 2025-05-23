package ru.nfbgu.dao.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.nfbgu.dao.DisciplineStorage;
import ru.nfbgu.dao.model.DisciplineModel;
import ru.nfbgu.dao.repo.DisciplineRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class DisciplineStorageImpl implements DisciplineStorage {
    private final DisciplineRepository disciplineRepository;
    @PersistenceContext
    EntityManager em;

    @Override
    @Transactional
    public void insert(DisciplineModel discipline) {
        em.merge(discipline);
    }

    @Override
    public List<DisciplineModel> getAllDisciplines() {
        String jpql = """
                select d from DisciplineModel d
                """;
        return em.createQuery(jpql, DisciplineModel.class).getResultList();
    }

    @Override
    public Optional<DisciplineModel> getDisciplineById(String id) {
        return Optional.ofNullable(em.find(DisciplineModel.class, id));
    }

    @Override
    @SneakyThrows
    public List<DisciplineModel> getDisciplinesByTeacherId(UUID teacherId) {
        return disciplineRepository.findAllByTeacherId(teacherId);
    }
}
