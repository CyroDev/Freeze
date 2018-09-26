package me.cyro.freeze.teams;

import org.bukkit.ChatColor;

public enum Team {

    RED("Rot", ChatColor.RED, (short) 14),
    BLUE("Blau", ChatColor.BLUE, (short) 11);

    private String teamName;
    private ChatColor chatColor;
    private short colorID;


    private Team (String teamName, ChatColor chatColor, short colorID) {
        this.teamName = teamName;
        this.chatColor = chatColor;
        this.colorID = colorID;
    }

    public String getTeamName() {
        return chatColor + teamName;
    }

    public ChatColor getChatColor() {
        return chatColor;
    }

    public short getColorID() {
        return colorID;
    }
}
