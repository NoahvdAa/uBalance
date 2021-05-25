package me.noahvdaa.ubalance.utils;

import me.noahvdaa.ubalance.UBalance;
import me.noahvdaa.ubalance.model.TargetServer;
import me.noahvdaa.ubalance.model.VirtualServer;
import net.md_5.bungee.api.config.ServerInfo;

import java.util.ArrayList;
import java.util.List;

public class ScheduledTaskUtils {

	public static void processServerPings(UBalance plugin) {
		List<String> alreadyPinging = new ArrayList<>();

		for (VirtualServer virtualServer : plugin.virtualServers.values()) {
			for (TargetServer targetServer : virtualServer.getTargetServers()) {
				if (alreadyPinging.contains(targetServer.getName())) continue;
				// Don't ping again.
				alreadyPinging.add(targetServer.getName());

				ServerInfo serverInfo = plugin.getProxy().getServerInfo(targetServer.getName());
				// Server doesn't exist.
				if (serverInfo == null) {
					plugin.targetServerSlots.remove(targetServer.getName());
					continue;
				}

				serverInfo.ping((serverPing, throwable) -> {
					// Null is used when the target server is offline.
					Integer slots = null;

					// The server is online.
					if (serverPing != null) {
						slots = serverPing.getPlayers().getMax() - serverPing.getPlayers().getOnline();
					}

					plugin.targetServerSlots.put(targetServer.getName(), slots);
				});
			}
		}
	}

}
