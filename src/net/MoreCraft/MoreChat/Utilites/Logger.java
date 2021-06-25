package net.MoreCraft.MoreChat.Utilites;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;

public class Logger {
	
	public static void info(String args0) {
		CommandSender sender = Bukkit.getConsoleSender();
		sender.sendMessage(Colorizer.parseColors(args0));
	}
}
