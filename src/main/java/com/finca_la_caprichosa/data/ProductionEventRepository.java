package com.finca_la_caprichosa.data;

import com.finca_la_caprichosa.model.ProductionEvent;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * Created by ssmiley on 6/26/17.
 */
@ApplicationScoped
public class ProductionEventRepository {

    @Inject
    private EntityManager em;

    public ProductionEvent findById(Long id) {
        return em.find(ProductionEvent.class, id);
    }

    public List<ProductionEvent> findAll() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<ProductionEvent> criteria = cb.createQuery(ProductionEvent.class);
        Root<ProductionEvent> productionEvent = criteria.from(ProductionEvent.class);
        criteria.select(productionEvent).orderBy(cb.desc(productionEvent.get("created")));
        return em.createQuery(criteria).getResultList();
    }
}
