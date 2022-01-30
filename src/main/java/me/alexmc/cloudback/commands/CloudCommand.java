package me.alexmc.cloudback.commands;

import com.google.common.collect.Maps;
import me.alexmc.cloudback.CloudBack;
import me.alexmc.cloudback.utils.Fields;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.plugin.Command;

import java.util.Map;

public class CloudCommand extends Command {
    private final Map<String, SubCommand> subCommandMap = Maps.newHashMap();

    public CloudCommand(CloudBack plugin) {
        super("cloud");

        subCommandMap.put("reload", new ReloadSubCommand(plugin));
        // TODO: subCommandMap.put("list", new ListSubCommand(plugin));
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if (!sender.hasPermission("cloudback.use")) {
            Fields.NO_PERM.send(sender);
            return;
        }

        if (args.length == 0) {
            Fields.NO_ARGS.send(sender);
            return;
        }

        if (!subCommandMap.containsKey(args[0].toLowerCase())) {
            Fields.UNKNOWN_ARG.send(sender);
            return;
        }

        SubCommand subCommand = subCommandMap.get(args[0].toLowerCase());
        if (!sender.hasPermission(subCommand.getPermission())) {
            Fields.NO_PERM.send(sender);
            return;
        }

        subCommand.perform(sender, args);
    }
}
