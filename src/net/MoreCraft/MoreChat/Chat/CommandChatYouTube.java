package net.MoreCraft.MoreChat.Chat;

import net.MoreCraft.MoreChat.MoreChat;
import net.MoreCraft.MoreChat.Utilites.*;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandChatYouTube implements CommandExecutor {
	
	MoreChat plugin;

	public CommandChatYouTube(MoreChat instance) {
		plugin = instance;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
        if (!(sender instanceof Player)) {
        	Logger.info("ERROR: Command used only in-game!");
            return true;
        }
        Player player = (Player) sender;
		if(sender.hasPermission("MoreChat.User.Chat.YouTube")){
			if(cmd.getName().equalsIgnoreCase("youtube")) {
				if(args.length == 0) {
					Messaging.Sender(player.getName(), "&cError");
					Messaging.Sender(player.getName(), "&cUse: /" + commandLabel + " <message>");
					return true;
	    		}
				
	    		plugin.YouTubeFormat = plugin.getConfig().getString("Chat.YouTube.Format");
	    		plugin.YouTubeFormat = plugin.YouTubeFormat.replace("&", "§");
				plugin.YouTubeFormat = plugin.YouTubeFormat.replace("[(]", "【");
				plugin.YouTubeFormat = plugin.YouTubeFormat.replace("[)]", "】");
	    			
	    		plugin.YouTubeFormat = plugin.YouTubeFormat.replace("[Name]", player.getName());
	    		plugin.YouTubeFormat = plugin.YouTubeFormat.replace("[DisplayName]", player.getDisplayName());
	    		plugin.YouTubeFormat = plugin.YouTubeFormat.replace("[Msg]", Colorizer.parseColors(Util.getFinalArg(args, 0, 1)));
	    		plugin.YouTubeFormat = plugin.YouTubeFormat.replace("[MsgColor]", Colorizer.parseColors(Util.getFinalArg(args, 0, 1)));
	    		plugin.YouTubeFormat = plugin.YouTubeFormat.replace("[Prefix]", plugin.getPrefix(player));
	    		plugin.YouTubeFormat = plugin.YouTubeFormat.replace("[Suffix]", plugin.getSuffix(player));
	    		User.broadcastMessage(plugin.YouTubeFormat);
	    		return true;
			}
		}
		Messaging.Sender(sender, "&cYou don't have permissions!");
		return true;
	}
}