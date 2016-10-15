package com.ticCore.restServices;

/** A game engine that knows how to start, end, process game logic
 *  Created by student on 10/14/16.
 */
public interface GameEngine {
    /**
     * Starts the game engine
     */
    public void startGame();

    /**Gets inputs and process game outcome based on inputs
     * @param playerId sender of inputs
     * @param inputs Actual inputs
     * @param <T>
     */
    public <T>  void processInputs(String playerId, T inputs);


    /**Updates players of game outcomes
     */
    public  void sendGameState();


    /**Called to inform engine of player disconnecting
     * @param playerId id of player who disconnected
     */
    public void playerDisconnected(String playerId);
}
