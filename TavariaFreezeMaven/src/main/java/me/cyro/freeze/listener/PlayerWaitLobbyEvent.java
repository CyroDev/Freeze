package me.cyro.freeze.listener;

import me.cyro.freeze.state.gamestates.EndingState;
import me.cyro.freeze.state.gamestates.LobbyState;
import me.cyro.freeze.state.manager.GameStateManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

public class PlayerWaitLobbyEvent implements Listener {


    private GameStateManager gameStateManager;


    public PlayerWaitLobbyEvent(GameStateManager gameStateManager) {
        this.gameStateManager = gameStateManager;


    }

    @EventHandler
    public void onDamage(EntityDamageEvent e) {
        Player p = (Player) e.getEntity();


        if(gameStateManager.getCurrentGameState() instanceof LobbyState) {
            e.setCancelled(true);
        } else if(gameStateManager.getCurrentGameState() instanceof EndingState) {
            e.setCancelled(true);
        } else {
            e.setCancelled(false);
        }
    }

}
