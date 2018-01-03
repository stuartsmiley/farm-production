package com.finca_la_caprichosa.graphing;

import com.finca_la_caprichosa.dto.Sample;
import com.finca_la_caprichosa.service.SampleService;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Created by ssmiley on 12/24/17.
 */
@RequestScoped
@Path("/samples")
public class Samples {

    @Inject
    private SampleService service;

    @GET
    @Path("/{id}/{months}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Sample> fetchSamples(@PathParam("id") String id, @PathParam("months") String months) {
        return service.samplesForGoatAndMonths(Long.valueOf(id), Integer.valueOf(months));
    }
}
