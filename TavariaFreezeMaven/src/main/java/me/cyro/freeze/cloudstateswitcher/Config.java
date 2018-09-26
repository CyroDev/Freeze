package me.cyro.freeze.cloudstateswitcher;


import me.cyro.freeze.Freeze;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class Config {

    public static File getFile() {
        return new File("plugins/Freeze", "cloudswitcher.yml");
    }
    public static FileConfiguration getFileConfig() {
        return YamlConfiguration.loadConfiguration(getFile());
    }
    public static void setStandartConfig() {
        FileConfiguration cfg = getFileConfig();
        cfg.options().copyDefaults(true);
        cfg.addDefault("Map", "Hier wird die Map eingetragen, auf der das Spiel stattfindet.");
        try {
            cfg.save(getFile());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    public static void read() {
        FileConfiguration cfg = getFileConfig();
        Freeze.INGAME_MAP = cfg.getString("Map");
    }
}
