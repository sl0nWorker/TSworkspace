package com.inc.slon.dao.impl;

import com.inc.slon.dao.FreightDao;
import com.inc.slon.model.Freight;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class FreightDaoImpl implements FreightDao {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void add(Freight freight) {
        entityManager.persist(freight);
    }

    @Override
    public List<Freight> freightList() {
        CriteriaQuery<Freight> criteriaQuery = entityManager.getCriteriaBuilder().createQuery(Freight.class);
        @SuppressWarnings("unused")
        Root<Freight> root = criteriaQuery.from(Freight.class);
        return entityManager.createQuery(criteriaQuery).getResultList();
    }

    @Override
    public Freight findByNumber(Integer freightNumber) {
        return entityManager.createQuery(
                "SELECT s FROM Freight s WHERE s.freightNumber= :freightNumber",Freight.class)
                .setParameter("freightNumber", freightNumber)
                .getSingleResult();
    }
}
