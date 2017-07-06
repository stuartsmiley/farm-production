package com.finca_la_caprichosa.service;

import com.finca_la_caprichosa.model.ProductionEvent;
import com.finca_la_caprichosa.model.Sample;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;

/**
 * Created by ssmiley on 6/26/17.
 * The @Stateless annotation eliminates the need for manual transaction demarcation
 */
@Stateless
public class ProductionService {

    @Inject
    private EntityManager em;

    public ProductionEvent addProductionEvent(ProductionEvent productionEvent) {
        em.persist(productionEvent);
        return productionEvent;
    }


}
