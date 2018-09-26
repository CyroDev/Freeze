package me.cyro.freeze.teams;

import me.cyro.freeze.Freeze;
import me.cyro.freeze.state.gamestates.IngameState;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class TeamControlListener implements Listener {

    @EventHandler
    public void PlayerChatEven(AsyncPlayerChatEvent e) {
        Player p = e.getPlayer();
        e.setCancelled(true);
        if(Freeze.getInstance().getGameStateManager().getCurrentGameState() instanceof IngameState) {
            if(!e.getMessage().startsWith("@a")) {
                TeamManager teamManager = Freeze.getInstance().getTeamManager();
                Team team = teamManager.getPlayTeam(p);
                for(Player current : Freeze.getInstance().getPlayers()) {
                    if(teamManager.getPlayTeam(current) == team)
                        current.sendMessage(team.getChatColor() + p.getDisplayName() + "§8» §f" + e.getMessage());
                }

            } else {
                TeamManager teamManager = Freeze.getInstance().getTeamManager();
                Team team = teamManager.getPlayTeam(p);
                String message = e.getMessage().replace("@a", "");
                Bukkit.broadcastMessage("§8[§7@all§8] " + team.getChatColor() + p.getDisplayName() + "§8» §f" + message);


            }
        } else {
            //Hier wird noch eine Permission abfrage stattfinden.
            Bukkit.broadcastMessage(p.getDisplayName() + "§8» §f" + e.getMessage());

        }

    }


}
