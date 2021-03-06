package com.finca_la_caprichosa;

import com.finca_la_caprichosa.dto.Credentials;
import com.finca_la_caprichosa.jwt.JwtTokenUtility;
import org.jose4j.json.internal.json_simple.JSONObject;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.servlet.ServletContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by ssmiley on 8/19/17.
 */
@Path("/login")
@RequestScoped
public class LoginResource {

    @Inject
    private ServletContext servletContext;
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response auth(Credentials credentials) {
        String user = servletContext.getInitParameter("milker");
        String password = servletContext.getInitParameter("milker.pw");
        if (user.equals(credentials.getEmail())
            && password.equals(credentials.getPassword())) {

            String whatever = JwtTokenUtility.buildJwt(credentials.getEmail());
            JSONObject json = new JSONObject();
            json.put("access_token", whatever);
//             Response response = Response.ok("stuart" + " authenticated")
            Response response = Response.ok(json.toJSONString())
                    .header("jwt", whatever)
                    .build();

            return response;
        }
        return Response.status(Response.Status.UNAUTHORIZED).build();
    }
}
