package com.inc.slon.dao.impl;

import com.inc.slon.dao.TruckerHistoryShiftDao;
import com.inc.slon.model.TruckerHistoryShift;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;


@Repository
public class TruckerHistoryShiftDaoImpl implements TruckerHistoryShiftDao {
    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private Logger log;

    @Override
    public void add(TruckerHistoryShift truckerHistoryShift) {
        entityManager.persist(truckerHistoryShift);
    }

    @Override
    public TruckerHistoryShift lastTruckerHistoryShiftByTruckerId(Long id) {

        List<TruckerHistoryShift> truckerHistoryShiftList = entityManager.createQuery(
                "SELECT s FROM TruckerHistoryShift s WHERE s.truckerId= :id",TruckerHistoryShift.class)
                .setParameter("id", id)
                .getResultList();
        log.info("list size: " + truckerHistoryShiftList.size());
        TruckerHistoryShift truckerHistoryShiftLast = null;
        Calendar max = new GregorianCalendar(0,0,0,0,0,0);
        for (TruckerHistoryShift truckerHistoryShift: truckerHistoryShiftList) {
            if (truckerHistoryShift.getShiftStatusTime().after(max)){
                max = truckerHistoryShift.getShiftStatusTime();
                truckerHistoryShiftLast = truckerHistoryShift;
            }
        }
        log.info("max shift date: " + max);
        log.info("truckerHistoryShift: " + truckerHistoryShiftLast);
        return truckerHistoryShiftLast;
    }
}
