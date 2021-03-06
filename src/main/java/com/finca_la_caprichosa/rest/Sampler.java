package com.finca_la_caprichosa.rest;

import com.finca_la_caprichosa.model.Sample;
import com.finca_la_caprichosa.service.SampleService;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by ssmiley on 7/4/17.
 */
@Path("/sample")
@RequestScoped
public class Sampler extends AbstractRestController<Sample> {

    @Inject
    private SampleService service;

    @Inject
    private Validator validator;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createSample(Sample sample) {
        Response.ResponseBuilder builder = null;

        try {
            validateEntity(sample);
            Sample persistent = service.saveSample(sample);
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
