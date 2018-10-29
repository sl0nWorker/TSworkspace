package com.inc.slon.dao.impl;

import com.inc.slon.dao.TruckerDao;
import com.inc.slon.model.Trucker;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Repository
public class TruckerDaoImpl implements TruckerDao {
    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private Logger log;

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

    @Override
    public void removeAllById(String[] ids) {
        List<Long> listId = new ArrayList<>();
        for (String id : ids) {
            listId.add(Long.valueOf(id));
        }
        int isSuccessful = entityManager.createQuery("DELETE FROM Trucker t WHERE t.id IN (:ids)")
                .setParameter("ids", listId).executeUpdate();
        // if (isSuccessful == 0 ) throw OptimisticLockException..
        if (isSuccessful == 0)
            log.error("(TruckerDaoImpl, removeAllById) String[] ids: " + listId.toString());
    }

    @Override
    public Trucker findById(Long idTrucker) {
        return entityManager.find(Trucker.class, idTrucker);
    }

    @Override
    public void update(Trucker updateTrucker) {
        entityManager.merge(updateTrucker);
    }
}
