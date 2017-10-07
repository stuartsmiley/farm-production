package com.finca_la_caprichosa.service;

import com.finca_la_caprichosa.model.Sample;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;

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
}
