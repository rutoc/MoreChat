package net.MoreCraft.MoreChat.Chat;

import net.MoreCraft.MoreChat.MoreChat;
import net.MoreCraft.MoreChat.Utilites.*;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandChatGlobal implements CommandExecutor {
	
	MoreChat plugin;
	public CommandChatGlobal(MoreChat instance) {
		plugin = instance;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
        if (!(sender instanceof Player)) {
        	Logger.info("ERROR: Command used only in-game!");
            return true;
        }
        Player player = (Player) sender;
		if(sender.hasPermission("MoreChat.User.Chat.Global")){
			if(cmd.getName().equalsIgnoreCase("global")) {
				if(args.length == 0) {
					Messaging.Sender(player.getName(), "&cError");
					Messaging.Sender(player.getName(), "&cUse: /" + commandLabel + " <message>");
					return true;
	    		}
				
				plugin.GlobalFormat = plugin.getConfig().getString("Chat.Global.Format");
				plugin.GlobalFormat = plugin.GlobalFormat.replace("&", "§");
				plugin.GlobalFormat = plugin.GlobalFormat.replace("[(]", "【");
				plugin.GlobalFormat = plugin.GlobalFormat.replace("[)]", "】");
	    		plugin.GlobalFormat = plugin.GlobalFormat.replace("[Name]", player.getName());
	    		plugin.GlobalFormat = plugin.GlobalFormat.replace("[DisplayName]", player.getDisplayName());
	    		plugin.GlobalFormat = plugin.GlobalFormat.replace("[Msg]", Util.getFinalArg(args, 0, 1));
	    		plugin.GlobalFormat = plugin.GlobalFormat.replace("[MsgColor]", Colorizer.parseColors(Util.getFinalArg(args, 0, 1)));
				plugin.GlobalFormat = plugin.GlobalFormat.replace("[Prefix]", plugin.getPrefix(player));
				plugin.GlobalFormat = plugin.GlobalFormat.replace("[Suffix]", plugin.getSuffix(player));
				User.broadcastMessage(plugin.GlobalFormat);
	    		return true;
			}
		}
		Messaging.Sender(sender, "&cYou don't have permissions!");
		return true;
	}
}