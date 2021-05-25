package me.noahvdaa.ubalance.command.ubalancecommand;

import me.noahvdaa.ubalance.UBalance;
import me.noahvdaa.ubalance.utils.ChatUtil;
import net.md_5.bungee.api.CommandSender;

public class ReloadSubCommand {

	public static void run(UBalance plugin, CommandSender sender) {
		sender.sendMessage(ChatUtil.colorizeAsPrefixedComponent(plugin, "&6Reloading config file, please wait..."));
		plugin.reloadConfig();
		sender.sendMessage(ChatUtil.colorizeAsPrefixedComponent(plugin, "&aReloaded config!"));
	}

}
