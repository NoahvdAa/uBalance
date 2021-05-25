package me.noahvdaa.ubalance.command.ubalancecommand;

import me.noahvdaa.ubalance.UBalance;
import me.noahvdaa.ubalance.utils.ChatUtil;
import net.md_5.bungee.api.CommandSender;

public class HelpSubCommand {

	public static void run(UBalance plugin, CommandSender sender) {
		sender.sendMessage(ChatUtil.colorizeAsPrefixedComponent(plugin, "&eAvailable subcommands:"));
		sender.sendMessage(ChatUtil.colorizeAsPrefixedComponent(plugin, "&b/ubalance help &e- Show this list of commands."));
		sender.sendMessage(ChatUtil.colorizeAsPrefixedComponent(plugin, "&b/ubalance reload &e- Reload the config file."));
	}

}
