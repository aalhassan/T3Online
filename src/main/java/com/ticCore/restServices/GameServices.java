package com.ticCore.restServices;

import org.glassfish.jersey.media.sse.SseFeature;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;


@ApplicationPath("gameServer")
public class GameServices extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> classes = new HashSet<Class<?>>();
        classes.add(SseFeature.class);

        return classes;
    }

}
