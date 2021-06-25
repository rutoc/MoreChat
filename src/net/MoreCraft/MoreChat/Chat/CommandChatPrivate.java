package net.MoreCraft.MoreChat.Chat;

import net.MoreCraft.MoreChat.MoreChat;
import net.MoreCraft.MoreChat.Utilites.*;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandChatPrivate implements CommandExecutor {
	
	MoreChat plugin;

	public CommandChatPrivate(MoreChat instance) {
		plugin = instance;
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		if (!(sender instanceof Player)) {
        	Logger.info("ERROR: Command used only in-game!");
			return true;
		}
		
		Player player = (Player) sender;
		if (cmd.getName().equalsIgnoreCase("private")) {
			if (!player.hasPermission("MoreChat.User.Chat.Private")) {
				Messaging.Sender(sender, "&cYou don't have permissions!");
				return true;
			}
			
			StringBuilder x = new StringBuilder();
			int i;
			for (i = 1; i < args.length; i++) {
				x.append(args[i] + " ");
				}
			
			if (args.length > 1) {
				
				//Config
				plugin.ColorNamePrivate = plugin.getConfig().getString("Chat.Private.Color.Name").replace("&", "§");
				plugin.ColorMessagePrivate = plugin.getConfig().getString("Chat.Private.Color.Message").replace("&", "§");
				//Config End
				Player target = User.getPlayer(args[0]);
				if (target != null) {
					Messaging.Sender(player, plugin.ColorNamePrivate + "[Você -> " + target.getDisplayName() + ""+ ChatColor.GRAY +"] " + plugin.ColorMessagePrivate + x.toString().trim());
					Messaging.Sender(target, plugin.ColorNamePrivate + "[" + player.getDisplayName() + ""+ ChatColor.GRAY +" -> Você] " + plugin.ColorMessagePrivate + x.toString().trim());
					return true;
					
					} else {
						Messaging.Sender(player.getName(), "&cOops, player not found!");
						return true;
					}
				}
			}
		Messaging.Sender(player, "&cError");
		Messaging.Sender(player, "&cUse: /" + commandLabel + " <player> <message>");
		return true;
	}
}