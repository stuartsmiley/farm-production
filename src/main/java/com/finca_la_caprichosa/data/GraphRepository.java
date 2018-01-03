package com.finca_la_caprichosa.data;

import com.finca_la_caprichosa.model.Goat;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by ssmiley on 12/14/17.
 */
@ApplicationScoped
public class GraphRepository {

    @Inject
    private EntityManager em;

    public List<Goat> findGoats() {
        Query query = em.createNamedQuery("Goat.produced");
        return query.getResultList();
    }
}
