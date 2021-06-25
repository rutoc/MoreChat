package net.MoreCraft.MoreChat.Utilites;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Messaging {
	
	public static void Sender(String name, String msg) {
		
		Player sender = User.getPlayer(name);
		if (sender == null) {
	        return;
		}
		Sender(sender, Colorizer.parseColors(msg));
	}
	
	public static void Sender(CommandSender sender, String msg) {
		if (sender instanceof Player) {
			Player player = (Player) sender;
			player.sendMessage(Colorizer.parseColors(msg));
		}
	}
}
