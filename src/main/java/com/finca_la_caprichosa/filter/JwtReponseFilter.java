package com.finca_la_caprichosa.filter;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Puts the token back into the response header.
 */
public class JwtReponseFilter implements ContainerResponseFilter {

    private static final Pattern SPLITTER = Pattern.compile(" ");
    @Override
    public void filter(ContainerRequestContext containerRequestContext,
                       ContainerResponseContext containerResponseContext) throws IOException {
        Boolean failed = (Boolean) containerRequestContext.getProperty("auth-failed");
        if (failed == null || !failed)  {
            String[] auth = SPLITTER.split(containerRequestContext.getHeaderString("Authorization"));
            List<Object> jwt = new ArrayList<>();
            jwt.add(auth[1]);
            containerResponseContext.getHeaders().put("jwt", jwt);
        }

    }
}
