package com.ticCore.restServices;

import org.glassfish.jersey.media.sse.EventOutput;
import org.glassfish.jersey.media.sse.OutboundEvent;
import org.glassfish.jersey.media.sse.SseBroadcaster;
import org.glassfish.jersey.server.ChunkedOutput;

import java.util.HashMap;
import java.util.Map;

/**Created to handle onClose actions from server or client
 * Created by student on 10/15/16.
 */
public class TicBroadCaster extends SseBroadcaster {
    private  TicGameEngine ticGameEngine;
    public final  Map<EventOutput, String> EVENT_PLAYER_IDS= new HashMap<EventOutput, String>();

    public void setPlayerId(String playerId) {
        playerId = playerId;
    }

    public TicBroadCaster() {
    }

    /**Takes a game engine and becomes boradcaster for the game engine
     * @param ticGameEngine Engine for which to broadcast for
     */
    public TicBroadCaster(TicGameEngine ticGameEngine) {
        this.ticGameEngine = ticGameEngine;
    }

    /**Handles the events involving midgame disconnection from players
     * @param chunkedOutput SSE tunnel for hwich connection was closed
     */
    @Override
    public void onClose(ChunkedOutput<OutboundEvent> chunkedOutput) {
        super.onClose(chunkedOutput);
        if (!EVENT_PLAYER_IDS.containsKey(chunkedOutput))
            return;
        String playerId = EVENT_PLAYER_IDS.get(chunkedOutput);
        this.remove(chunkedOutput);
        ticGameEngine.playerDisconnected(playerId);
    }
}
