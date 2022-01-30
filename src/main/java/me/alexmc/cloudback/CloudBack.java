package me.alexmc.cloudback;

import lombok.Getter;
import me.alexmc.cloudback.commands.CloudCommand;
import me.alexmc.cloudback.listeners.ConnectionListener;
import me.alexmc.cloudback.objects.CloudServer;
import me.alexmc.cloudback.objects.TextFile;
import me.alexmc.cloudback.utils.Fields;
import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.plugin.Plugin;

import java.util.concurrent.TimeUnit;

@Getter
public final class CloudBack extends Plugin {
    private static CloudBack instance;
    private TextFile fileConfig;

    @Override
    public void onEnable() {
        instance = this;
        fileConfig = new TextFile(this, "config.yml");
        getProxy().getPluginManager().registerCommand(this, new CloudCommand(this));
        getProxy().getPluginManager().registerListener(this, new ConnectionListener(this));
        checkServers();
    }

    private void checkServers() {
        getProxy().getScheduler().schedule(this, () -> {
            CloudServer.getCloudServers().clear();
            for (String serverName : Fields.LOBBIES_LIST.getStringList()) {
                ServerInfo serverInfo = getProxy().getServerInfo(serverName);

                if (serverInfo == null) {
                    continue;
                }

                serverInfo.ping((result, error) -> {
                    if (error == null) {
                        new CloudServer(serverInfo);
                    }
                });
            }
        }, 0, 5, TimeUnit.SECONDS);
    }

    @Override
    public void onDisable() {
        // TODO: ShutDown
    }

    public static CloudBack getInstance() {
        return instance;
    }
}
