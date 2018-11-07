package com.inc.slon.dao.impl;

import com.inc.slon.dao.CountryMapDao;
import com.inc.slon.model.City;
import com.inc.slon.model.CountryMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class CountyMapDaoImpl implements CountryMapDao {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public void add(CountryMap countryMap) {
        entityManager.persist(countryMap);
    }

    @Override
    public List<CountryMap> countryMapList() {
        CriteriaQuery<CountryMap> criteriaQuery = entityManager.getCriteriaBuilder().createQuery(CountryMap.class);
        @SuppressWarnings("unused")
        Root<CountryMap> root = criteriaQuery.from(CountryMap.class);
        return entityManager.createQuery(criteriaQuery).getResultList();
    }

    @Override
    public int distance(City cityFrom, City cityTo) {
        return entityManager.createQuery(
                "SELECT s FROM CountryMap s WHERE s.cityFrom= :cityFrom AND s.cityTo=:cityTo",CountryMap.class)
                .setParameter("cityFrom", cityFrom).setParameter("cityTo",cityTo)
                .getSingleResult().getDistance();
    }
}
