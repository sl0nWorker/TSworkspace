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
    public CountryMap getCountryMap() {
        CriteriaQuery<CountryMap> criteriaQuery = entityManager.getCriteriaBuilder().createQuery(CountryMap.class);
        @SuppressWarnings("unused")
        Root<CountryMap> root = criteriaQuery.from(CountryMap.class);
        List<CountryMap> countryMapList = entityManager.createQuery(criteriaQuery).getResultList();
        if (countryMapList.size() != 0) {
            return countryMapList.get(0);
        } else {
            return null;
        }
    }
}
