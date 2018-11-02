package com.inc.slon.dao.impl;

import com.inc.slon.dao.FreightStatusDao;
import com.inc.slon.model.FreightStatus;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class FreightStatusDaoImpl implements FreightStatusDao {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public FreightStatus findByStatusName(String statusName) {
        return entityManager.createQuery(
                "SELECT s FROM FreightStatus s WHERE s.status = :statusName",FreightStatus.class)
                .setParameter("statusName", statusName)
                .getSingleResult();
    }

    @Override
    public List<FreightStatus> statusList() {
        CriteriaQuery<FreightStatus> criteriaQuery = entityManager.getCriteriaBuilder().createQuery(FreightStatus.class);
        @SuppressWarnings("unused")
        Root<FreightStatus> root = criteriaQuery.from(FreightStatus.class);
        return entityManager.createQuery(criteriaQuery).getResultList();
    }

    @Override
    public void add(FreightStatus freightStatus) {
        entityManager.persist(freightStatus);
    }
}
