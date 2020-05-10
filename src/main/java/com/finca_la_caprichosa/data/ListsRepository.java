package com.finca_la_caprichosa.data;

import com.finca_la_caprichosa.model.Goat;
import com.finca_la_caprichosa.model.Storage;
import com.finca_la_caprichosa.model.Units;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by ssmiley on 6/30/17.
 */
@ApplicationScoped
public class ListsRepository {

    @Inject
    private EntityManager em;

    public List<Units> findUnits() {
        Query query = em.createNamedQuery("Units.active");
        return query.getResultList();
    }

    public List<Storage> findStorage() {
        Query query = em.createNamedQuery("Storage.active");
        return query.getResultList();
    }

    public List<Goat> findProducers() {
        Query query = em.createNamedQuery("Goat.inProduction");
        return query.getResultList();
    }
}
