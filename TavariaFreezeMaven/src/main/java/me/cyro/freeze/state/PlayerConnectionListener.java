package me.cyro.freeze.state;

import me.cyro.freeze.Freeze;
import me.cyro.freeze.state.gamestates.LobbyState;
import me.cyro.freeze.state.manager.GameStateManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerConnectionListener implements Listener {

    private GameStateManager gameStateManager;


    public PlayerConnectionListener(GameStateManager gameStateManager) {
        this.gameStateManager = gameStateManager;


    }
    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();
        if(gameStateManager.getCurrentGameState().equals(LobbyState.class)) {
           LobbyState lobbyState = (LobbyState) gameStateManager.getCurrentGameState();
            Freeze.getInstance().getPlayers().add(p);
            //Hier folgt einer Permission Abfrage.
            p.setDisplayName(ChatColor.GRAY + p.getName());
            e.setJoinMessage(Freeze.getPREFIX() + "§e" + p.getDisplayName() + "§7hat das §eSpiel §abetreten§8. [§3" + Freeze.getInstance().getPlayers().size() + "§7/ §e" + LobbyState.MAX_PLAYERS);
            if(Freeze.getInstance().getPlayers().size() >= LobbyState.MIN_PLAYERS) {
                //LobbyCounter Starten.
                if(!lobbyState.getLobbyCountdown().isRunning()) {
                    if(lobbyState.getLobbyCountdown().isIdling())
                        lobbyState.getLobbyCountdown().cancelIdle();
                        lobbyState.getLobbyCountdown().run();

                }
            }
        }
    }
    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        Player p = e.getPlayer();
        if(gameStateManager.getCurrentGameState() instanceof  LobbyState) {
            LobbyState lobbyState = (LobbyState) gameStateManager.getCurrentGameState();
            Freeze.getInstance().getPlayers().remove(p);
            e.setQuitMessage(Freeze.getPREFIX() + "§e" + p.getDisplayName() + "§7hat das §eSpiel §cverlassen§8. [§3" + Freeze.getInstance().getPlayers().size() + "§7/ §e" + LobbyState.MAX_PLAYERS);
                if(Freeze.getInstance().getPlayers().size() < LobbyState.MIN_PLAYERS) {
                    //LobbyCounter Stoppen.
                    if(lobbyState.getLobbyCountdown().isRunning()) {
                        lobbyState.getLobbyCountdown().cancel();
                        if(!lobbyState.getLobbyCountdown().isIdling())
                            lobbyState.getLobbyCountdown().idle();
                    }

                }
        }
    }

}
