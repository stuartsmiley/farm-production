package com.finca_la_caprichosa.graphing;

import com.finca_la_caprichosa.dto.Rainfall;
import com.finca_la_caprichosa.service.RainService;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * Get all rainfall.
 */
@RequestScoped
@Path("/rainfall")
public class RainfallTotals {
    @Inject
    private RainService service;

    @GET
    @Path("/totals")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Rainfall> fetchRainfall() {
        return service.totals();
    }
}
