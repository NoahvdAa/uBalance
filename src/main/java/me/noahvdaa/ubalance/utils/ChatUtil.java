package me.noahvdaa.ubalance.utils;

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

}
