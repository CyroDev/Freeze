package me.cyro.freeze;

import me.cyro.freeze.cloudstateswitcher.Config;
import me.cyro.freeze.command.CMD_START;
import me.cyro.freeze.state.PlayerConnectionListener;
import me.cyro.freeze.state.manager.GameState;
import me.cyro.freeze.state.manager.GameStateManager;
import me.cyro.freeze.teams.TeamControlListener;
import me.cyro.freeze.teams.TeamManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;

public class Freeze extends JavaPlugin {

    private static String SERVER_NAME = "Tavaria.de";
    public static String INGAME_MAP = "Hier wird die Map eingetragen, auf der das Spiel stattfindet.";

    private static String PREFIX = "§8[§bFreeze§8] §7";
    private static String NO_PERMISSION = PREFIX + "§cDazu hast du keine Rechte!";

    private static Freeze instance;
    private ArrayList<Player> players;

    private TeamManager teamManager;
    private GameStateManager gameStateManager;

    @Override
    public void onEnable() {
        super.onEnable();
        Register();
        Init();
        instance = this;
        gameStateManager = new GameStateManager();
        gameStateManager.setGameStates(GameState.LOBBY_STATE);
        teamManager = new TeamManager();

    }

    @Override
    public void onDisable() {
        super.onDisable();

        Config.setStandartConfig();
        Config.read();


    }
    void Init() {
        players = new ArrayList<Player>();

    }
    void Register() {
        PluginManager pm = Bukkit.getPluginManager();

        getCommand("start").setExecutor(new CMD_START());
        pm.registerEvents(new PlayerConnectionListener(gameStateManager), this);
        pm.registerEvents(new TeamControlListener(), this);



    }

    public static String getPREFIX() {
        return PREFIX;
    }

    public static String getNoPermission() {
        return NO_PERMISSION;
    }

    public static Freeze getInstance() {
        return instance;
    }

    public GameStateManager getGameStateManager() {
        return gameStateManager;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public TeamManager getTeamManager() {
        return teamManager;
    }
}
