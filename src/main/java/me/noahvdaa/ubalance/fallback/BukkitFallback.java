package me.noahvdaa.ubalance.fallback;

import org.bukkit.plugin.java.JavaPlugin;

public class BukkitFallback extends JavaPlugin {

	@Override
	public void onEnable() {
		for (int i = 0; i < 5; i++) {
			getLogger().warning("uBalance is a BungeeCord plugin! You don't have to install it on your Spigot subservers!");
		}
		getServer().getPluginManager().disablePlugin(this);
	}

}
