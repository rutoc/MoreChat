package net.MoreCraft.MoreChat.Chat;

import net.MoreCraft.MoreChat.MoreChat;
import net.MoreCraft.MoreChat.Utilites.*;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandChatWarning implements CommandExecutor {
	
	MoreChat plugin;

	public CommandChatWarning(MoreChat instance) {
		plugin = instance;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
        if (!(sender instanceof Player)) {
        	Logger.info("ERROR: Command used only in-game!");
            return true;
        }
        Player player = (Player) sender;
		if(sender.hasPermission("MoreChat.User.Chat.Warning")){
			if(cmd.getName().equalsIgnoreCase("warning")) {
				if(args.length == 0) {
					Messaging.Sender(player.getName(), "&cError");
					Messaging.Sender(player.getName(), "&cUse: /" + commandLabel + " <message>");
					return true;
	    		}
				
	    		plugin.WarningFormat = plugin.getConfig().getString("Chat.Warning.Format");
	    		plugin.WarningFormat = plugin.WarningFormat.replace("&", "§");
				plugin.WarningFormat = plugin.WarningFormat.replace("[(]", "【");
				plugin.WarningFormat = plugin.WarningFormat.replace("[)]", "】");
	    		plugin.WarningFormat = plugin.WarningFormat.replace("[Name]", player.getName());
	    		plugin.WarningFormat = plugin.WarningFormat.replace("[DisplayName]", player.getDisplayName());
	    		plugin.WarningFormat = plugin.WarningFormat.replace("[Msg]", Util.getFinalArg(args, 0, 1));
	    		plugin.WarningFormat = plugin.WarningFormat.replace("[MsgColor]", Colorizer.parseColors(Util.getFinalArg(args, 0, 1)));
	    		plugin.WarningFormat = plugin.WarningFormat.replace("[Prefix]", plugin.getPrefix(player));
	    		plugin.WarningFormat = plugin.WarningFormat.replace("[Suffix]", plugin.getSuffix(player));
	    		User.broadcastMessage(plugin.WarningFormat);
	    		return true;
			}
		}
		Messaging.Sender(sender, "&cYou don't have permissions!");
		return true;
	}
}