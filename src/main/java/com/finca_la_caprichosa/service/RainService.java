package com.finca_la_caprichosa.service;

import com.finca_la_caprichosa.dto.Rainfall;
import com.finca_la_caprichosa.model.Rain;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.Date;
import java.util.List;

/**
 * Fetch some rainfall data.
 */
@Stateless
public class RainService {

    @Inject
    private EntityManager em;

    public Rain saveRain(Rain rain) {
        Rain saved = null;
        if (rain.getId() == null) {
            em.persist(rain);
            saved = rain;
        } else {
            saved = em.merge(rain);
        }
        return rain;
    }


    public List<Rain> rainfallForPerion(Date startInclusive, Date endInclusive) {
       return em.createNamedQuery("Rain.byDateRange")
               .setParameter("start", startInclusive)
               .setParameter("end", endInclusive)
               .getResultList();
    }

    public List<Rainfall> totals() {
        return em.createNamedQuery("Rain.totals").getResultList();
    }
}
