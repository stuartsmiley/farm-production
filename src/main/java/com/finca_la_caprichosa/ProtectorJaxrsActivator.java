package com.finca_la_caprichosa;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by ssmiley on 8/19/17.
 */
@ApplicationPath("token")
public class ProtectorJaxrsActivator extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> classSet = new HashSet<>();
        classSet.add(LoginResource.class);
        return classSet;
    }
}
