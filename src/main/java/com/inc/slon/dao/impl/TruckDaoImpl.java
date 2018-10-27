package com.inc.slon.dao.impl;

import com.inc.slon.dao.TruckDao;
import com.inc.slon.model.Truck;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Repository
public class TruckDaoImpl implements TruckDao {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void add(Truck truck) {
        entityManager.persist(truck);
    }

    @Override
    public List<Truck> truckList() {
        CriteriaQuery<Truck> criteriaQuery = entityManager.getCriteriaBuilder().createQuery(Truck.class);
        @SuppressWarnings("unused")
        Root<Truck> root = criteriaQuery.from(Truck.class);
        return entityManager.createQuery(criteriaQuery).getResultList();
    }

    @Override
    public void removeAllById(String[] ids) {
        List<Long> listId = new ArrayList<>();
        for (String id : ids) {
            listId.add(Long.valueOf(id));
        }
        int isSuccessful = entityManager.createQuery("DELETE FROM Truck t WHERE t.id IN (:ids)")
                .setParameter("ids", listId).executeUpdate();
        // if (isSuccessful == 0 ) throw OptimisticLockException..
    }
}
