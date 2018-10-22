package com.inc.slon.dao.impl;

import com.inc.slon.dao.CityDao;
import com.inc.slon.model.City;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class CityDaoImpl implements CityDao {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void add(City city) {
        entityManager.persist(city);
    }

    @Override
    public List<City> cityList() {
        CriteriaQuery<City> criteriaQuery = entityManager.getCriteriaBuilder().createQuery(City.class);
        @SuppressWarnings("unused")
        Root<City> root = criteriaQuery.from(City.class);
        return entityManager.createQuery(criteriaQuery).getResultList();
    }
}
