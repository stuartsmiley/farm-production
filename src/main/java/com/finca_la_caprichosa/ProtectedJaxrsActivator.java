package com.finca_la_caprichosa;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

/**
 * For /auth/token.
 */
@ApplicationPath("auth")
public class ProtectedJaxrsActivator extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> classSet = new HashSet<>();
        classSet.add(AuthResource.class);
        return classSet;
    }

}
