package me.cyro.freeze.state.manager;

import me.cyro.freeze.state.gamestates.EndingState;
import me.cyro.freeze.state.gamestates.IngameState;
import me.cyro.freeze.state.gamestates.LobbyState;

public class GameStateManager {

    private GameState[] gameStates = new GameState[3];
    private GameState currentGameState;


    public GameStateManager() {
        gameStates[GameState.LOBBY_STATE] = new LobbyState();
        gameStates[GameState.INGAME_STATE] = new IngameState();
        gameStates[GameState.ENDING_STATE] = new EndingState();
    }
    public void setGameStates(int gameStateIndex) {
        if(currentGameState != null)
            currentGameState.stop();
        currentGameState = gameStates[gameStateIndex];
        currentGameState.start();
    }

    public void stopCurrentGameState() {
        currentGameState.stop();
        currentGameState = null;
    }

    public GameState getCurrentGameState() {
        return currentGameState;
    }
}
