package com.finca_la_caprichosa.rest;

import com.finca_la_caprichosa.data.ListsRepository;
import com.finca_la_caprichosa.model.Goat;
import com.finca_la_caprichosa.model.Storage;
import com.finca_la_caprichosa.model.Units;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * Created by ssmiley on 7/3/17.
 */
@RequestScoped
@Path("/lists")
public class ListsService {

    @Inject
    private ListsRepository repository;

    @GET
    @Path("/storage")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Storage> storageOptions() {
        return repository.findStorage();
    }

    @GET
    @Path("/units")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Units> unitsOptions() {
        return repository.findUnits();
    }

    @GET
    @Path("/producers")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Goat> producerOptions() {
        return repository.findProducers();
    }
}
