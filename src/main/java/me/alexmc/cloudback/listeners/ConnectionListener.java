package me.alexmc.cloudback.listeners;

import me.alexmc.cloudback.CloudBack;
import me.alexmc.cloudback.objects.CloudServer;
import me.alexmc.cloudback.utils.Fields;
import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.ServerKickEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

import java.util.Comparator;
import java.util.LinkedList;

public class ConnectionListener implements Listener {
    private final CloudBack plugin;

    public ConnectionListener(CloudBack plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onKick(ServerKickEvent e) {
        ProxiedPlayer proxiedPlayer = e.getPlayer();
        e.setCancelled(true);

        LinkedList<CloudServer> cloudLobbies = new LinkedList<>(CloudServer.getCloudServers());
        cloudLobbies.remove(cloudLobbies.get());

        cloudLobbies.sort(Comparator.reverseOrder());

        try {
            ServerInfo serverInfo = cloudLobbies.get(0).getServerInfo();
            e.setCancelServer(serverInfo);
            Fields.SERVER_DOWN.send(proxiedPlayer);
        } catch (IndexOutOfBoundsException ignored) {
            Fields.NO_LOBBIES.send(proxiedPlayer);
        }
    }
}
