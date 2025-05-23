package ru.nfbgu.dao.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.nfbgu.dao.TeacherStorage;
import ru.nfbgu.dao.model.TeacherModel;
import ru.nfbgu.dao.repo.TeacherRepository;
import ru.nfbgu.service.dto.Pageable;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class TeacherStorageImpl implements TeacherStorage {
    private final TeacherRepository teacherRepository;

    @PersistenceContext
    EntityManager em;

    @Override
    @Transactional
    public void insert(TeacherModel teacher) {
        em.merge(teacher);
    }

    @Override
    public List<TeacherModel> getAllTeachers() {
        String jpql = """
                select m from TeacherModel m
                """;
        return em.createQuery(jpql, TeacherModel.class).getResultList();
    }

    @Override
    public Pageable<TeacherModel> getAllTeachersByPage(int pageNumber) {
        int pageSize = 4;

        String sql = """
                select * from teacher t
                order by t.name
                               
                limit :page_size offset :offset
                """;
        Query query = em.createNativeQuery(sql, TeacherModel.class);
        query.setParameter("page_size", pageSize);
        query.setParameter("offset", pageNumber * pageSize);
        List<TeacherModel> data = query.getResultList();

        String countSql = """
                select count(*) from teacher
                """;
        Query countQuery = em.createNativeQuery(countSql, Integer.class);
        int totalItems = (int) countQuery.getSingleResult();
        int totalPages = totalItems / pageSize;
        return new Pageable<>(
                data,
                pageNumber,
                totalItems % pageSize == 0 ? totalPages : totalPages + 1,
                totalItems
        );
    }


    @Override
    public Page<TeacherModel> getAllTeachersByPage2(int pageNumber) {
        int pageSize = 4;
        Sort sort = Sort.by("name");
        org.springframework.data.domain.Pageable pageable =
                PageRequest.of(pageNumber, pageSize, sort);
        return teacherRepository.findAll(pageable);
    }

    @Override
    public Pageable<TeacherModel> getAllTeachersByPage3(int pageNumber) {
        int pageSize = 4;

        String sql = """
                select t from TeacherModel t
                order by t.name
                """;
        TypedQuery<TeacherModel> query = em.createQuery(sql, TeacherModel.class);
        query.setMaxResults(pageSize);
        query.setFirstResult(pageNumber * pageSize);
        List<TeacherModel> data = query.getResultList();

        String countSql = """
                select count(*) from teacher
                """;
        Query countQuery = em.createNativeQuery(countSql, Integer.class);
        int totalItems = (int) countQuery.getSingleResult();
        int totalPages = totalItems / pageSize;
        return new Pageable<>(
                data,
                pageNumber,
                totalItems % pageSize == 0 ? totalPages : totalPages + 1,
                totalItems
        );
    }

    @Override
    public List<TeacherModel> getFirstTeachers(int count) {
        /*org.springframework.data.domain.Pageable pageable = // делает два запроса select and count
                PageRequest.of(0, count);
        teacherRepository.findAll(pageable).getContent();*/

        // teacherRepository.findByOrderByNameAsc(Limit.of(count)); // select only

        String sql = """
                select t from TeacherModel t
                order by t.name
                """;
        TypedQuery<TeacherModel> query = em.createQuery(sql, TeacherModel.class);
        query.setMaxResults(count);

        return query.getResultList();
    }

    @Override
    public Optional<TeacherModel> getTeacherById(UUID id) {
        return teacherRepository.findById(id);
    }
}
