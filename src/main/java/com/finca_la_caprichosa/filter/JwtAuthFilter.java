package com.finca_la_caprichosa.filter;

import com.finca_la_caprichosa.jwt.RsaKeyProducer;
import org.jose4j.jwk.RsaJsonWebKey;
import org.jose4j.jwt.JwtClaims;
import org.jose4j.jwt.consumer.InvalidJwtException;
import org.jose4j.jwt.consumer.JwtConsumer;
import org.jose4j.jwt.consumer.JwtConsumerBuilder;

import javax.annotation.Priority;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import java.io.IOException;
import java.security.Principal;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Validates the token.
 */
@Priority(Priorities.AUTHENTICATION)
public class JwtAuthFilter implements ContainerRequestFilter {

    private static final Pattern BEARER = Pattern.compile("^Bearer (.+)$");

    @Override
    public void filter(ContainerRequestContext containerRequestContext) throws IOException {
        String headerAuthorization  = containerRequestContext.getHeaderString("Authorization");
        Matcher matcher = headerAuthorization != null ? BEARER.matcher(headerAuthorization) : BEARER.matcher("");
        if(matcher.matches()) {
            try {
                final String subject = validate(matcher.group(1));
                final SecurityContext securityContext = containerRequestContext.getSecurityContext();
                if (subject != null) {
                    containerRequestContext.setSecurityContext(new SecurityContext() {
                        @Override
                        public Principal getUserPrincipal() {
                            return new Principal() {
                                @Override
                                public String getName() {
                                    return subject;
                                }
                            };
                        }

                        @Override
                        public boolean isUserInRole(String role) {
                            return securityContext.isUserInRole(role);
                        }

                        @Override
                        public boolean isSecure() {
                            return securityContext.isSecure();
                        }

                        @Override
                        public String getAuthenticationScheme() {
                            return securityContext.getAuthenticationScheme();
                        }
                    });
                }
            } catch (InvalidJwtException ex) {
                containerRequestContext.setProperty("auth-failed", true);
                containerRequestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).build());
            }
        } else {
            containerRequestContext.setProperty("auth-failed", true);
            containerRequestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).build());
        }
    }

    private String validate(String jwt) throws InvalidJwtException {
        String subject = null;
        RsaJsonWebKey rsaJsonWebKey = RsaKeyProducer.produce();
        JwtConsumer jwtConsumer = new JwtConsumerBuilder()
                .setRequireSubject()
                .setVerificationKey(rsaJsonWebKey.getKey())
                .build();

        JwtClaims jwtClaims = jwtConsumer.processToClaims(jwt);
        subject = (String) jwtClaims.getClaimValue("sub");
        return subject;
    }
}
