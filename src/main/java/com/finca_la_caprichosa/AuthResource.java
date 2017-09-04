package com.finca_la_caprichosa;

import com.finca_la_caprichosa.jwt.JwtTokenUtility;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

/**
 * Created by ssmiley on 7/9/17.
 */
@Path("token")
public class AuthResource {

    @Context
    SecurityContext securityContext;

    @GET
    @Produces("text/plain")
    public Response auth() {
          String authenticatedUser = securityContext.getUserPrincipal().getName();
          Response response = Response.ok(authenticatedUser + " authenticated")
                  .header("jwt", JwtTokenUtility.buildJwt(authenticatedUser))
                  .build();
          return response;
    }


}
