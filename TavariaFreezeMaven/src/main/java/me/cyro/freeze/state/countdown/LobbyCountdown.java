package me.cyro.freeze.state.countdown;

import me.cyro.freeze.Freeze;
import me.cyro.freeze.state.gamestates.LobbyState;
import me.cyro.freeze.state.manager.GameState;
import org.bukkit.Bukkit;

public class LobbyCountdown extends Countdown {

    private final int RESET_SECONDS = 30;
    private final int IDLE_SECONDS = 30;

    private int idleID;

    private boolean isRunning = false,
                    isIdling = false,
                    isStarting = false;
    private int seconds = 60;

    @Override
    public void run() {
        isRunning = true;

        taksID = Bukkit.getScheduler().scheduleSyncRepeatingTask(Freeze.getInstance(), new Runnable() {
            public void run() {

                switch (seconds) {
                    case 60: case 30: case 15: case 10: case 5: case 4: case 3: case 2:
                        Bukkit.broadcastMessage(Freeze.getPREFIX() + "§7Das §eSpiel §astartet §7in §a" + seconds + " Sekunden§8!");
                        if(seconds == 5) {
                            isStarting = true;
                        }
                        break;
                    case 1:
                        Bukkit.broadcastMessage(Freeze.getPREFIX() + "§7Das §eSpiel §astartet §7in §aeiner Sekunde§8!");
                        break;
                    case 0:
                        Freeze.getInstance().getGameStateManager().setGameStates(GameState.INGAME_STATE);
                        break;

                        default:
                            break;
                }
                seconds--;
            }
        },0, 20 * 1);

    }

    @Override
    public void cancel() {
        isRunning = false;

        Bukkit.getScheduler().cancelTask(taksID);
        seconds = RESET_SECONDS;



    }
    public void idle() {
        isIdling = true;

        idleID = Bukkit.getScheduler().scheduleSyncRepeatingTask(Freeze.getInstance(), new Runnable() {
            public void run() {
                int missingPlayers = LobbyState.MIN_PLAYERS - Freeze.getInstance().getPlayers().size();
                if(missingPlayers != 1)
                    Bukkit.broadcastMessage(Freeze.getPREFIX() + "§7Es fehlen noch §a" + missingPlayers + " Spieler §7bis zum Start.");
                else
                    Bukkit.broadcastMessage(Freeze.getPREFIX() + "§7Es fehlt noch §aein Spieler §7bis zum Start.");
            }
        },IDLE_SECONDS * 20, IDLE_SECONDS * 20 );
    }
    public void cancelIdle() {
        isIdling = false;
        Bukkit.getScheduler().cancelTask(idleID);
    }

    public boolean isRunning() {

        return isRunning;
    }

    public boolean isIdling() {
        return isIdling;
    }

    public void setSeconds(int seconds) {
        this.seconds = seconds;
    }

    public boolean isStarting() {
        return isStarting;
    }

    public void setStarting(boolean starting) {
        isStarting = starting;
    }
}
