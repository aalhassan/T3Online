package com.ticCore.restServices;
import org.glassfish.jersey.media.sse.SseFeature;
import org.glassfish.jersey.server.ResourceConfig;

import java.util.HashSet;

import java.util.Set;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;


@ApplicationPath("webServices")
public class SSEAppService extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> classes = new HashSet<Class<?>>();
        classes.add(SimpleSSE.class);
        classes.add(SseFeature.class);
        classes.add(CtoFService.class);
        return classes;
    }

}
