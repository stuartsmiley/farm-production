package com.finca_la_caprichosa.graphing;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by ssmiley on 12/24/17.
 */
@ApplicationPath("/api")
public class GraphingActivator extends Application {
    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> classSet = new HashSet<>();
        classSet.add(Samples.class);
        return classSet;
    }

}
