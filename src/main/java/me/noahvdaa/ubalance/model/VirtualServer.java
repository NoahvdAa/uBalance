package me.noahvdaa.ubalance.model;

import me.noahvdaa.ubalance.UBalance;
import net.md_5.bungee.api.connection.ProxiedPlayer;

import java.util.ArrayList;
import java.util.List;

public class VirtualServer {

	private String name;
	private List<TargetServer> targetServers;

	public VirtualServer(String name, List<TargetServer> targetServers) {
		this.name = name;
		this.targetServers = targetServers;
	}

	public String getName() {
		return this.name;
	}

	public List<TargetServer> getTargetServers() {
		return this.targetServers;
	}

	public List<TargetServer> getAvailableTargetServers(UBalance plugin, ProxiedPlayer player) {
		List<TargetServer> servers = new ArrayList<>();

		for (TargetServer targetServer : targetServers) {
			Integer slotsAvailable = plugin.targetServerSlots.get(targetServer.getName());
			if (targetServer.getRequireOnline() && slotsAvailable == null) continue;
			if (targetServer.getRequireSlotsAvailable() && (slotsAvailable == null || slotsAvailable < 1)) continue;
			servers.add(targetServer);
		}

		return servers;
	}

}
