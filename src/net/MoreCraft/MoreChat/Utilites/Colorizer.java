package net.MoreCraft.MoreChat.Utilites;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class Colorizer {

	public static String parseColors(String parsed) {
		String Color = ChatColor.translateAlternateColorCodes('&', parsed);
		return Color;
	}

	public static String parseColors2(Player player, String parsed) {
		if (player.hasPermission("MoreChat.User.Chat.Color")) {
			String Color = ChatColor.translateAlternateColorCodes('&', parsed);
			return Color;
		}
		return parsed;
	}
}
