package com.example.letstrip.service;

import com.example.letstrip.entity.Festival;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import java.util.List;

@Service
public class FestivalService {

    @Autowired
    private EntityManager entityManager;

    @Transactional
    public Page<Festival> getFestivalsByFilters(List<String> areas, List<String> months, String name, Pageable pageable) {
        StringBuilder queryBuilder = new StringBuilder("SELECT f FROM Festival f WHERE 1=1 ");

        if (areas != null && !areas.isEmpty()) {
            queryBuilder.append("AND f.area LIKE CONCAT(:area, '%') ");
        }

        if (months != null && !months.isEmpty()) {
            queryBuilder.append("AND f.month IN :months ");
        }

        if (name != null && !name.trim().isEmpty()) {
            queryBuilder.append("AND LOWER(f.name) LIKE LOWER(:name) ");
        }

        TypedQuery<Festival> typedQuery = entityManager.createQuery(queryBuilder.toString(), Festival.class);

        // Set parameters only if present
        if (areas != null && !areas.isEmpty()) {
            typedQuery.setParameter("area", areas.get(0));  // Assuming areas is a list and we are using the first area
        }
        if (months != null && !months.isEmpty()) {
            typedQuery.setParameter("months", months);
        }
        if (name != null && !name.trim().isEmpty()) {
            typedQuery.setParameter("name", "%" + name + "%");
        }

        // Pagination
        typedQuery.setFirstResult((int) pageable.getOffset());
        typedQuery.setMaxResults(pageable.getPageSize());

        List<Festival> results = typedQuery.getResultList();

        // Count Query
        long count = getCountForQuery(areas, months, name);

        return new PageImpl<>(results, pageable, count);
    }

    private long getCountForQuery(List<String> areas, List<String> months, String name) {
        StringBuilder countQueryBuilder = new StringBuilder("SELECT COUNT(f) FROM Festival f WHERE 1=1 ");

        if (areas != null && !areas.isEmpty()) {
            countQueryBuilder.append("AND f.area LIKE CONCAT(:area, '%') ");
        }

        if (months != null && !months.isEmpty()) {
            countQueryBuilder.append("AND f.month IN :months ");
        }

        if (name != null && !name.trim().isEmpty()) {
            countQueryBuilder.append("AND LOWER(f.name) LIKE LOWER(:name) ");
        }

        TypedQuery<Long> typedCountQuery = entityManager.createQuery(countQueryBuilder.toString(), Long.class);

        if (areas != null && !areas.isEmpty()) {
            typedCountQuery.setParameter("area", areas.get(0));  // Assuming areas is a list and we are using the first area
        }
        if (months != null && !months.isEmpty()) {
            typedCountQuery.setParameter("months", months);
        }
        if (name != null && !name.trim().isEmpty()) {
            typedCountQuery.setParameter("name", "%" + name + "%");
        }

        return typedCountQuery.getSingleResult();
    }

}
