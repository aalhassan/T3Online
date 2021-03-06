package com.ticCore.restServices;

import com.ticCore.beans.TicState;
import com.ticCore.controllers.MainController;
import org.glassfish.jersey.media.sse.EventOutput;
import org.glassfish.jersey.media.sse.OutboundEvent;
import org.glassfish.jersey.media.sse.SseFeature;

import javax.inject.Singleton;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**Main Game Server. A singleton that handles request to start new game, join an existing game<br/>
 *  and server-client communication
 * Created by student on 10/8/16.
 */

@Singleton
@Path("moves")
public class GameServer {
    private Map<String, GameEngine> currentGames = new HashMap<String, GameEngine>();
    private AtomicInteger connectionIdx = new AtomicInteger(0);
    private static final String GAME_SESSION = "GameSession";

    public Map<String, GameEngine> getCurrentGames() {
        return currentGames;
    }

    public AtomicInteger getConnectionIdx() {
        return connectionIdx;
    }

    /** Handles request to start a new game i.e when player clicks start new game
     * @param request Current request of player from context of the rest service
     * @return Chunked output object that client usess to recieve the SSE
     */
    @Path("newGame")
    @GET
    @Produces(SseFeature.SERVER_SENT_EVENTS)
    public EventOutput registerForNewGame(@Context HttpServletRequest request) {
        final EventOutput eventOutput = new EventOutput();
        final HttpSession session = request.getSession();
        String playerId = MainController.getSessVal(session, "logged_in_email");
        if (playerId == null)
            return null;
        registerPlayer(playerId, eventOutput);
        return eventOutput;
    }

    /**
     * @param gameSession Represents the current game for which message/inputs were recieved
     * @param inputs player inputs
     * @param request Current request of player from context of the rest service
     */
    @Path("{gameSession}/{inputs}")
    @POST
    public void messageReceived (@PathParam("gameSession") String gameSession, @PathParam("inputs") String inputs,
            @Context HttpServletRequest request) {
        HttpSession session = request.getSession();
        String playerId = MainController.getSessVal(session, "logged_in_email");
        if (gameSession == null || inputs == null || inputs.length() != 2 ||
                !this.currentGames.containsKey(gameSession) || playerId == null)
            return;
        int[] rowCol = new int[2];
        rowCol[0]= Character.getNumericValue(inputs.charAt(0));
        rowCol[1]= Character.getNumericValue(inputs.charAt(1));
        TicGameEngine playerGame =  (TicGameEngine) this.currentGames.get(gameSession);
        playerGame.processInputs(playerId, rowCol);

    }

    /**Broascasts game state to players at the request of a game engine
     * @param gameSession Session of current game for which broadcast is being made
     * @param message message to be broadcasted
     * @return Status of the broadcast
     */
    @Path("broadcast/{gameSession}")
    @POST
    @Consumes(MediaType.APPLICATION_XML)
    @Produces (MediaType.APPLICATION_XML)
    public String broadcastMessage(@PathParam("gameSession") String gameSession, TicState message) {
        if (!this.currentGames.containsKey(gameSession))
            return "No game session found";
        OutboundEvent.Builder eventBuilder = new OutboundEvent.Builder();
        OutboundEvent event = eventBuilder.mediaType(MediaType.APPLICATION_XML_TYPE)
                .data(TicState.class, message)
                .build();
        TicBroadCaster broadcaster = ((TicGameEngine) this.currentGames.get(gameSession)).TIC_BROADCASTER;
        broadcaster.broadcast(event);
        return "Message '" + message + "' has been broadcast.";
    }

    /**Does player matchmaking, odd connected players host, even connected joins
     * @param playerId Id of player to match
     * @param eventOutput each player's SSE tunnel object
     */
    private void registerPlayer (String playerId, EventOutput eventOutput) {
        int curPlayerIdx = connectionIdx.incrementAndGet();
        String gameSession;
        TicBroadCaster ticBroadCaster;
        TicGameEngine ticGameEngine;

        //Odd numbered connected players set create new broadcasters and game engine
        if (curPlayerIdx % 2 == 1) {
            gameSession = GAME_SESSION + curPlayerIdx;
            ticGameEngine = new TicGameEngine(gameSession, this);
            ticBroadCaster = ticGameEngine.TIC_BROADCASTER;
            ticBroadCaster.add(eventOutput);
            ticBroadCaster.EVENT_PLAYER_IDS.put(eventOutput, playerId);
            this.currentGames.put(gameSession, ticGameEngine);
            ticGameEngine.setPlayerPlayingX(playerId);
        }
        //Even numbered connected players join existing game session and use session's engines
        else if (curPlayerIdx % 2 == 0){
            gameSession = GAME_SESSION + (curPlayerIdx -1);
            if (!currentGames.containsKey(gameSession))
                return;
            ticGameEngine = (TicGameEngine) this.currentGames.get(gameSession);
            ticBroadCaster = ticGameEngine.TIC_BROADCASTER;
            ticBroadCaster.add(eventOutput);
            ticBroadCaster.EVENT_PLAYER_IDS.put(eventOutput,playerId);
            ticGameEngine.setPlayerPlayingO(playerId);
        }
    }
}