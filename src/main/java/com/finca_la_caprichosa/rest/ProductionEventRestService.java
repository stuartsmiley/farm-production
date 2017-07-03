package com.finca_la_caprichosa.rest;

import com.finca_la_caprichosa.data.ProductionEventRepository;
import com.finca_la_caprichosa.model.ProductionEvent;
import com.finca_la_caprichosa.service.ProductionService;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by ssmiley on 6/28/17.
 */
@Path("/production")
@RequestScoped
public class ProductionEventRestService {

    @Inject
    private ProductionEventRepository repository;

    @Inject
    private Validator validator;

    @Inject
    private ProductionService service;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<ProductionEvent> listAll() {
        return repository.findAll();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createProductionEvent(ProductionEvent productionEvent) {
        Response.ResponseBuilder builder = null;

        try {
            ProductionEvent persistent = service.addProductionEvent(productionEvent);
            builder = Response.ok();
            builder.entity(persistent);
        } catch (Exception e) {
            Map<String, String> responseObj = new HashMap<>();
            responseObj.put("error", e.getMessage());
            builder = Response.status(Response.Status.BAD_REQUEST).entity(responseObj);
        }

        return builder.build();
    }

    // todo: validation
    private void validateProductionEvent(ProductionEvent productionEvent) {
        Set<ConstraintViolation<ProductionEvent>> violations = validator.validate(productionEvent);
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(new HashSet<ConstraintViolation<?>>(violations));
        }
        // TODO if a similar event exists in a similar timeframe
        // throw a new ValidationException
    }

    /**
     * Creates a JAX-RS "Bad Request" response including a map of all violation fields, and their message. This can then be used
     * by clients to show violations.
     *
     * @param violations A set of violations that needs to be reported
     * @return JAX-RS response containing all violations
     */
    private Response.ResponseBuilder createViolationResponse(Set<ConstraintViolation<?>> violations) {
//        log.fine("Validation completed. violations found: " + violations.size());

        Map<String, String> responseObj = new HashMap<>();

        for (ConstraintViolation<?> violation : violations) {
            responseObj.put(violation.getPropertyPath().toString(), violation.getMessage());
        }

        return Response.status(Response.Status.BAD_REQUEST).entity(responseObj);
    }

}
