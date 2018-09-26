package me.cyro.freeze.state.gamestates;

import me.cyro.freeze.Freeze;
import me.cyro.freeze.state.manager.GameState;
import org.bukkit.Bukkit;

public class IngameState extends GameState {

    @Override
    public void start() {
        Bukkit.broadcastMessage(Freeze.getPREFIX() + "§aDer Countdown wurde beendet!");
        Bukkit.broadcastMessage(Freeze.getPREFIX() + "§7Alle Spieler werden auf die Map Teleportiert.");


    }

    @Override
    public void stop() {

    }
}
