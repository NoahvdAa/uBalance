package me.noahvdaa.ubalance.utils;

import me.noahvdaa.ubalance.UBalance;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.TextComponent;

public class ChatUtil {

	public static String colorize(String input) {
		return ChatColor.translateAlternateColorCodes('&', input);
	}

	public static BaseComponent colorizeAsComponent(String input) {
		return new TextComponent(colorize(input));
	}

	public static BaseComponent colorizeAsPrefixedComponent(UBalance plugin, String input) {
		return colorizeAsComponent("&8[&2u&aBalance&8] " + input);
	}

}
