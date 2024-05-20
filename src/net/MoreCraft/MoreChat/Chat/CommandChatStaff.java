package net.MoreCraft.MoreChat.Chat;

import net.MoreCraft.MoreChat.MoreChat;
import net.MoreCraft.MoreChat.Utilites.*;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandChatStaff implements CommandExecutor {
	
	MoreChat plugin;

	public CommandChatStaff(MoreChat instance) {
		plugin = instance;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
        if (!(sender instanceof Player)) {
        	Logger.info("ERROR: Command used only in-game!");
            return true;
        }
        
        Player player = (Player) sender;
        
        if(player.hasPermission("MoreChat.User.Chat.Staff")) {
        	if(cmd.getName().equalsIgnoreCase("chatstaff")) {
        		
        		if(args.length == 0) {
        			
					Messaging.Sender(player.getName(), "&cError");
					Messaging.Sender(player.getName(), "&cUse: /" + commandLabel + " <message>");
        			return true;
        		}
        		
				String format = plugin.getConfig().getString("Chat.Staff.Format");
        		
        		sendStaff(player, Variable.Format(player, format, args));
        		return true;
        	}
        }
		Messaging.Sender(sender, "&cYou don't have permissions!");
		return true;
	}
	
	public static int sendStaff(Player player, String message) {
		
		int i = 0;
		
		for (Player staff : Bukkit.getOnlinePlayers()) {
			
			if(staff.hasPermission("MoreChat.User.Chat.Staff")) {
				
				staff.sendMessage(message);
				
				i++;
			}
		}
		return i;
	}
}