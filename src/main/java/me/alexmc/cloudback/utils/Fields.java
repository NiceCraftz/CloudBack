package me.alexmc.cloudback.utils;

import me.alexmc.cloudback.CloudBack;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.TextComponent;

import java.util.List;

public enum Fields {
    LOBBIES_LIST("lobbies"),
    SERVER_DOWN("server-down"),
    NO_LOBBIES("no-lobbies"),
    RELOADED("reloaded"),
    NO_ARGS("no-args"),
    UNKNOWN_ARG("unknown-arg"),
    NO_PERM("no-perm"),
    LOADED_SERVER("loaded-lobby");

    private final CloudBack plugin = CloudBack.getInstance();
    private final String path;

    Fields(String path) {
        this.path = path;
    }

    public List<String> getStringList() {
        return plugin.getFileConfig().getConfig().getStringList(path);
    }

    public String getString() {
        return plugin.getFileConfig().getConfig().getString(path);
    }

    public String getFormattedString() {
        return ChatColor.translateAlternateColorCodes('&', getString());
    }

    public void send(CommandSender commandSender, Object... obj) {
        commandSender.sendMessage(new TextComponent(String.format(getFormattedString(), obj)));
    }
}
