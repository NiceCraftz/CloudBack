package me.alexmc.cloudback.commands;

import me.alexmc.cloudback.CloudBack;
import me.alexmc.cloudback.utils.Fields;
import net.md_5.bungee.api.CommandSender;

public class ReloadSubCommand implements SubCommand {
    private final CloudBack plugin;

    public ReloadSubCommand(CloudBack plugin) {
        this.plugin = plugin;
    }

    @Override
    public String getPermission() {
        return "cloudback.reload";
    }

    @Override
    public void perform(CommandSender commandSender, String[] args) {
        plugin.getFileConfig().reload();
        Fields.RELOADED.send(commandSender);
    }
}
