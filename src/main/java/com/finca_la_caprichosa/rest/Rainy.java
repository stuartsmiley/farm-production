package com.finca_la_caprichosa.rest;

import com.finca_la_caprichosa.model.Rain;
import com.finca_la_caprichosa.service.RainService;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by ssmiley on 4/25/18.
 */
@Path("/rain")
@RequestScoped
public class Rainy extends AbstractRestController<Rain> {

    @Inject
    private RainService service;

    @Inject
    private Validator validator;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createRain(Rain rain) {
        Response.ResponseBuilder builder = null;

        try {
            Rain persistent = service.saveRain(rain);
            builder = Response.ok();
            builder.entity(persistent);
        } catch (ConstraintViolationException e) {
            builder = createViolationResponse(e.getConstraintViolations());
        } catch (Exception e) {
            Map<String, String> responseObj = new HashMap<>();
            responseObj.put("error", e.getMessage());
            builder = Response.status(Response.Status.BAD_REQUEST).entity(responseObj);
        }
        return builder.build();
    }
}
