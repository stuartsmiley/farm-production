package com.finca_la_caprichosa.service;

import com.finca_la_caprichosa.model.Sample;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.Calendar;
import java.util.List;

/**
 * For adding samples.
 */
@Stateless
public class SampleService {

    @Inject
    private EntityManager em;

    public Sample saveSample(Sample sample) {
        Sample saved = null;
        if (sample.getId() == null) {
            em.persist(sample);
            saved = sample;
        } else {
            saved = em.merge(sample);
        }
        return saved;
    }

    public List<com.finca_la_caprichosa.dto.Sample> samplesForGoatAndMonths(Long goatId, Integer months) {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MONTH, -1 * months);
        return em.createNamedQuery("Sample.byGoatAndMonths")
                .setParameter("months", cal.getTime())
                .setParameter("goat", goatId)
                .getResultList();
    }
}
