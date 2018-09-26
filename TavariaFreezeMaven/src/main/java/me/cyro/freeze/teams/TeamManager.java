package me.cyro.freeze.teams;

import org.bukkit.entity.Player;

import java.util.HashMap;

public class TeamManager {

    private HashMap<Player, Team> teamConnector;

    public TeamManager() {
        teamConnector = new HashMap<Player, Team>();
    }

    public Team getPlayTeam(Player player) {
        return teamConnector.containsKey(player) ? teamConnector.get(player) : null;
    }
    public void setPlayerTeam(Player player, Team team) {
        teamConnector.put(player, team);
    }


}
