package me.alexmc.cloudback.commands;

import net.md_5.bungee.api.CommandSender;

public interface SubCommand {
    String getPermission();

    void perform(CommandSender commandSender, String[] args);
}
