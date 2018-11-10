package com.inc.slon.dao.impl;

import com.inc.slon.dao.ArchiveOrderDao;
import com.inc.slon.model.ArchiveOrder;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
@Repository
public class ArchiveOrderDaoImpl implements ArchiveOrderDao {
    @PersistenceContext
    EntityManager entityManager;

    @Override
    public void add(ArchiveOrder archiveOrder) {
        entityManager.persist(archiveOrder);
    }

    @Override
    public List<ArchiveOrder> archiveOrderList() {
        CriteriaQuery<ArchiveOrder> criteriaQuery = entityManager.getCriteriaBuilder().createQuery(ArchiveOrder.class);
        @SuppressWarnings("unused")
        Root<ArchiveOrder> root = criteriaQuery.from(ArchiveOrder.class);
        return entityManager.createQuery(criteriaQuery).getResultList();
    }
}
