package com.finca_la_caprichosa.util;

import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * This class uses CDI to alias Java EE resources, such as the persistence context, to CDI beans
 */
public class Resources {

    @Produces
    @PersistenceContext
    private EntityManager em;
    
}
