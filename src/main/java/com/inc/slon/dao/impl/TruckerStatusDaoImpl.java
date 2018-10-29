package com.inc.slon.dao.impl;

import com.inc.slon.dao.TruckerStatusDao;
import com.inc.slon.model.TruckerStatus;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class TruckerStatusDaoImpl implements TruckerStatusDao {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<TruckerStatus> truckerStatusList() {
        CriteriaQuery<TruckerStatus> criteriaQuery = entityManager.getCriteriaBuilder().createQuery(TruckerStatus.class);
        @SuppressWarnings("unused")
        Root<TruckerStatus> root = criteriaQuery.from(TruckerStatus.class);
        return entityManager.createQuery(criteriaQuery).getResultList();
    }

    @Override
    public void add(TruckerStatus truckerStatus) {
        entityManager.persist(truckerStatus);
    }

    @Override
    public TruckerStatus findById(String id) {
        return entityManager.find(TruckerStatus.class,Long.valueOf(id));
    }
}
