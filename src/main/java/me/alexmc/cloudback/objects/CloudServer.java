package me.alexmc.cloudback.objects;

import com.google.common.collect.Lists;
import lombok.Getter;
import lombok.Setter;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.config.ServerInfo;

import java.util.List;

@Getter
@Setter
public class CloudServer implements Comparable<CloudServer> {
    private static final List<CloudServer> cloudServers = Lists.newArrayList();

    private final ServerInfo serverInfo;

    public CloudServer(ServerInfo serverInfo) {
        this.serverInfo = serverInfo;
        cloudServers.add(this);
        ProxyServer.getInstance().getLogger().severe("Size of list: " + cloudServers.size());
    }

    @Override
    public int compareTo(CloudServer o) {
        return Integer.compare(getServerInfo().getPlayers().size(), o.getServerInfo().getPlayers().size());
    }

    public static List<CloudServer> getCloudServers() {
        return cloudServers;
    }
}
