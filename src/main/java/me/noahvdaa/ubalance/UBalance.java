package me.noahvdaa.ubalance;

import de.leonhard.storage.Config;
import de.leonhard.storage.LightningBuilder;
import de.leonhard.storage.internal.settings.ConfigSettings;
import de.leonhard.storage.internal.settings.DataType;
import de.leonhard.storage.internal.settings.ReloadSettings;
import me.noahvdaa.ubalance.command.UBalanceCommand;
import me.noahvdaa.ubalance.listeners.PlayerEvents;
import me.noahvdaa.ubalance.model.TargetServer;
import me.noahvdaa.ubalance.model.VirtualServer;
import me.noahvdaa.ubalance.utils.ScheduledTaskUtils;
import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.plugin.Plugin;
import org.bstats.bungeecord.Metrics;

import java.io.File;
import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class UBalance extends Plugin {

	private static UBalance instance;
	private Config config;
	public Map<String, VirtualServer> virtualServers;
	public Map<String, Integer> targetServerSlots;

	@Override
	public void onEnable() {
		// Store instance for singleton.
		instance = this;

		targetServerSlots = new HashMap<>();

		initializeConfigs();

		// Register events.
		getProxy().getPluginManager().registerListener(this, new PlayerEvents(this));

		// Register commands
		getProxy().getPluginManager().registerCommand(this, new UBalanceCommand(this));

		// Ping servers to check if they're up.
		getProxy().getScheduler().schedule(this, () -> ScheduledTaskUtils.processServerPings(instance), 1, 1, TimeUnit.SECONDS);

		// Register bStats metrics.
		new Metrics(this, 11446);
	}

	public void reloadConfig(){
		config.forceReload();
		setupVirtualServers();
	}

	private void initializeConfigs() {
		// Initialize config.
		config = LightningBuilder
				.fromFile(new File(getDataFolder().getPath() + File.separator + "config.yml"))
				.addInputStreamFromResource("config.yml")
				.setDataType(DataType.SORTED)
				.setReloadSettings(ReloadSettings.MANUALLY)
				.setConfigSettings(ConfigSettings.PRESERVE_COMMENTS)
				.createConfig();

		setupVirtualServers();
	}

	private void setupVirtualServers() {
		virtualServers = new HashMap<>();
		for (String server : config.singleLayerKeySet("Servers")) {
			// Remove virtual server it already exists.
			getProxy().getServers().remove(server);

			// Create new "virtual" server.
			InetSocketAddress socketAddress = new InetSocketAddress("127.0.0.1", 0);
			ServerInfo info = getProxy().constructServerInfo(server, socketAddress, server, false);
			getProxy().getServers().put(server, info);

			List<TargetServer> targetServers = new ArrayList<TargetServer>();

			for (String targetServer : config.singleLayerKeySet("Servers." + server)) {
				int weight = config.getInt("Servers." + server + "." + targetServer + ".weight");
				boolean requiresOnline = config.getBoolean("Servers." + server + "." + targetServer + ".requiresOnline");
				boolean requiresSlotsAvailable = config.getBoolean("Servers." + server + "." + targetServer + ".requireSlotsAvailable");

				targetServers.add(new TargetServer(targetServer, weight, requiresOnline, requiresSlotsAvailable));
			}

			// Store virtual server.
			VirtualServer virtualServer = new VirtualServer(server, targetServers);
			virtualServers.put(server, virtualServer);
		}
	}

	public Config getConfig() {
		return this.config;
	}

	public static UBalance getInstance() {
		return instance;
	}

}
