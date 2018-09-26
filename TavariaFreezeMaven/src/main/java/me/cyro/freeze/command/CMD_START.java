package me.cyro.freeze.command;

import me.cyro.freeze.Freeze;
import me.cyro.freeze.state.gamestates.LobbyState;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CMD_START implements CommandExecutor {


    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if(command.getName().equalsIgnoreCase("start")) {
            if(commandSender instanceof Player) {
                Player p = (Player) commandSender;
                if(p.hasPermission("bw.start")) {
                    if(strings.length == 0) {
                        if(Freeze.getInstance().getGameStateManager().getCurrentGameState() instanceof LobbyState) {
                            LobbyState ls = (LobbyState) Freeze.getInstance().getGameStateManager().getCurrentGameState();
                            if(ls.getLobbyCountdown().isRunning()) {
                                if(!ls.getLobbyCountdown().isStarting()) {
                                    ls.getLobbyCountdown().setSeconds(5);
                                    ls.getLobbyCountdown().setStarting(true);
                                } else {
                                    p.sendMessage(Freeze.getPREFIX() + "§7Das Spiel startet bereits!");
                                }
                            } else {
                                p.sendMessage(Freeze.getPREFIX() + "§7Es fehlen noch Spieler bis zum Start des Countdowns");
                            }
                        } else {
                            p.sendMessage(Freeze.getPREFIX() + "§7Die Lobby-Phase ist bereits beendet!");
                        }
                    } else {
                        p.sendMessage(Freeze.getPREFIX() + "§7Bitte benutze §a/start");
                    }
                } else {
                    p.sendMessage(Freeze.getNoPermission());
                }
            }
        }
        return false;
    }
}
