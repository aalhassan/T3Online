package com.ticCore.restServices;

import org.glassfish.jersey.media.sse.EventOutput;
import org.glassfish.jersey.media.sse.OutboundEvent;
import org.glassfish.jersey.media.sse.SseBroadcaster;
import org.glassfish.jersey.media.sse.SseFeature;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by student on 10/8/16.
 */


@Path("/moves")
public class GameServer {
    Map<String, SseBroadcaster> broadcasters = new HashMap<String, SseBroadcaster>();
    Map<String, GameEngine> currentGames = new HashMap<String, GameEngine>();

    @Path("/newGame")
    @GET
    @Produces(SseFeature.SERVER_SENT_EVENTS)
    public EventOutput registerForNewGame(@Context HttpServletRequest request) {
        final HttpSession session = request.getSession();
        final EventOutput eventOutput = new EventOutput();
        new Thread(new Runnable() {
            public void run() {
                if (session.getAttribute("email") == null) {
                    try {
                        eventOutput.close();
                    } catch (IOException ioClose) {
                        throw new RuntimeException(
                                "Error when closing the event output.", ioClose);
                    }
                }

                try {
                    for (int i = 0; i < 10; i++) {
                        try {
                            Thread.sleep(2000);
                        } catch (Exception ex) {

                        }

                        final OutboundEvent.Builder eventBuilder
                                = new OutboundEvent.Builder();
                        //eventBuilder.name("message-to-client");
                        eventBuilder.data(String.class,
                                "Hello world " + i + "!");
                        final OutboundEvent event = eventBuilder.build();
                        eventOutput.write(event);
                    }
                } catch (IOException e) {
                    throw new RuntimeException(
                            "Error when writing the event.", e);
                } finally {
                    try {
                        eventOutput.close();
                    } catch (IOException ioClose) {
                        throw new RuntimeException(
                                "Error when closing the event output.", ioClose);
                    }
                }
            }
        }).start();
        return eventOutput;
    }


}
