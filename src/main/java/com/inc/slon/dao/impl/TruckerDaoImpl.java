package com.inc.slon.dao.impl;

import com.inc.slon.dao.TruckerDao;
import com.inc.slon.model.Trucker;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class TruckerDaoImpl implements TruckerDao {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Trucker> truckerList() {
        CriteriaQuery<Trucker> criteriaQuery = entityManager.getCriteriaBuilder().createQuery(Trucker.class);
        @SuppressWarnings("unused")
        Root<Trucker> root = criteriaQuery.from(Trucker.class);
        return entityManager.createQuery(criteriaQuery).getResultList();
    }

    @Override
    public void add(Trucker trucker) {
        entityManager.persist(trucker);
    }
}
