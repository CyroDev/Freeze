package me.cyro.freeze.state.gamestates;

import me.cyro.freeze.Freeze;
import me.cyro.freeze.state.countdown.LobbyCountdown;
import me.cyro.freeze.state.manager.GameState;
import me.cyro.freeze.teams.Team;
import me.cyro.freeze.teams.TeamManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.Random;

public class LobbyState extends GameState {

    public static final int MIN_PLAYERS = 1,
                            MAX_PLAYERS = 12;

    private LobbyCountdown lobbyCountdown;



    @Override
    public void start() {
        Bukkit.getConsoleSender().sendMessage("§aLOBBY_STATE hat begonnen.");
        lobbyCountdown = new LobbyCountdown();
        lobbyCountdown.idle();

    }

    @Override
    public void stop() {
        TeamManager teamManager = Freeze.getInstance().getTeamManager();
        for(Player current : Freeze.getInstance().getPlayers()) {
            if(teamManager.getPlayTeam(current) == null) {
                teamManager.setPlayerTeam(current, Team.values()[new Random().nextInt(Team.values().length)]);
                current.sendMessage(Freeze.getPREFIX() + "§7Du wurdest einem Team zugewiesen.");
                current.setDisplayName(teamManager.getPlayTeam(current).getChatColor() + current.getName());

            }
            current.sendMessage(Freeze.getPREFIX() + "§7Du bist nun in Team " + teamManager.getPlayTeam(current).getTeamName() + " §7.");

        }

    }

    public LobbyCountdown getLobbyCountdown() {
        return lobbyCountdown;
    }
}
