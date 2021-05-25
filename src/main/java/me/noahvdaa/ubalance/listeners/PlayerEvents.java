package me.noahvdaa.ubalance.listeners;

import me.noahvdaa.ubalance.UBalance;
import me.noahvdaa.ubalance.model.TargetServer;
import me.noahvdaa.ubalance.model.VirtualServer;
import me.noahvdaa.ubalance.utils.ChatUtil;
import me.noahvdaa.ubalance.utils.RandomUtils;
import net.md_5.bungee.api.event.ServerConnectEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

import java.util.List;

public class PlayerEvents implements Listener {

	public UBalance plugin;

	public PlayerEvents(UBalance plugin) {
		this.plugin = plugin;
	}

	@EventHandler
	public void onServerConnect(ServerConnectEvent e) {
		String target = e.getTarget().getName();
		VirtualServer virtualServer = plugin.virtualServers.get(target);
		// Not a virtual server.
		if (virtualServer == null) return;
		// Fetch candidate servers.
		List<TargetServer> candidates = virtualServer.getAvailableTargetServers(plugin, e.getPlayer());

		// No candidates found.
		if(candidates.size() == 0){
			e.getPlayer().disconnect(ChatUtil.colorizeAsComponent(plugin.getConfig().getString("General.NoAvailableServersMessage")));
			return;
		}

		TargetServer selectedServer = RandomUtils.pickServer(candidates);

		Integer slotsAvailable = plugin.targetServerSlots.get(selectedServer.getName());
		// Decrease slots if they exist.
		if (slotsAvailable != null) {
			plugin.targetServerSlots.put(selectedServer.getName(), slotsAvailable - 1);
		}

		// Finally, change target.
		e.setTarget(plugin.getProxy().getServerInfo(selectedServer.getName()));
	}

}
