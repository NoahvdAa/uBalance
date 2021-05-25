package me.noahvdaa.ubalance.command;

import me.noahvdaa.ubalance.UBalance;
import me.noahvdaa.ubalance.command.ubalancecommand.HelpSubCommand;
import me.noahvdaa.ubalance.command.ubalancecommand.ReloadSubCommand;
import me.noahvdaa.ubalance.utils.ChatUtil;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.plugin.Command;

public class UBalanceCommand extends Command {

	private final UBalance plugin;

	public UBalanceCommand(UBalance plugin) {
		super("ubalance", "", "ub");
		this.plugin = plugin;
	}

	@Override
	public void execute(CommandSender sender, String[] args) {
		String subcommand = "";
		if (args.length != 0 && sender.hasPermission("ubalance.admin")) {
			subcommand = args[0].toLowerCase();
		}
		switch (subcommand) {
			case "help":
				HelpSubCommand.run(plugin, sender);
				break;
			case "reload":
				ReloadSubCommand.run(plugin, sender);
				break;
			case "":
				sender.sendMessage(ChatUtil.colorizeAsPrefixedComponent(plugin, "&eThis server is running &buBalance v" + plugin.getDescription().getVersion() + " &eby &bNoahvdAa&e."));
				if (!sender.hasPermission("ubalance.admin")) {
					sender.sendMessage(ChatUtil.colorizeAsPrefixedComponent(plugin, "&cYou don't have enough permissions to use subcommands."));
				} else {
					sender.sendMessage(ChatUtil.colorizeAsPrefixedComponent(plugin, "&eType &b/ubalance help &efor a list of commands."));
				}
				break;
			default:
				sender.sendMessage(ChatUtil.colorizeAsPrefixedComponent(plugin, "&cUnknown subcommand. Type /ubalance help for help."));
				break;
		}
	}
}
