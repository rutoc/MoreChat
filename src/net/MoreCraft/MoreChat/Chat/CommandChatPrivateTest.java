package net.MoreCraft.MoreChat.Chat;

import net.MoreCraft.MoreChat.MoreChat;
import net.MoreCraft.MoreChat.Utilites.*;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandChatPrivateTest implements CommandExecutor {
	
	MoreChat plugin;
	public CommandChatPrivateTest(MoreChat instance) {
		plugin = instance;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
        if (!(sender instanceof Player)) {
        	Logger.info("Esse comando pode ser usado somente no jogo");
            return true;
        }
        
        Player player = (Player) sender;
		if(sender.hasPermission("MoreChat.User.Chat.Private")){
			if(cmd.getName().equalsIgnoreCase("private")) {
				if(args.length == 0) {
					Messaging.Sender(player, "&cErro");
					Messaging.Sender(player, "&cUso: /" + commandLabel + " <jogador> <mensagem>");
					return true;
	    		}
				
				plugin.PrivateFormat = plugin.getConfig().getString("Chat.Private.Format");
				plugin.PrivateFormat = plugin.PrivateFormat.replace("&", "§");
				plugin.PrivateFormat = plugin.PrivateFormat.replace("[(]", "【");
				plugin.PrivateFormat = plugin.PrivateFormat.replace("[)]", "】");
				plugin.PrivateFormat = plugin.PrivateFormat.replace("[Name]", player.getName());
				plugin.PrivateFormat = plugin.PrivateFormat.replace("[DisplayName]", player.getDisplayName());
				plugin.PrivateFormat = plugin.PrivateFormat.replace("[TargetName]", User.getPlayer(args[0]).getName());
				plugin.PrivateFormat = plugin.PrivateFormat.replace("[TargetDisplayName]", User.getPlayer(args[0]).getDisplayName());
				plugin.PrivateFormat = plugin.PrivateFormat.replace("[Msg]", Util.getFinalArg(args, 1, 2));
				plugin.PrivateFormat = plugin.PrivateFormat.replace("[MsgColor]", Colorizer.parseColors(Util.getFinalArg(args, 1, 2)));
				plugin.PrivateFormat = plugin.PrivateFormat.replace("[Prefix]", plugin.getPrefix(player));
				plugin.PrivateFormat = plugin.PrivateFormat.replace("[Suffix]", plugin.getSuffix(player));
					
				if(args.length == 1) {
					
					Player target = User.getPlayer(args[0]);
					
					if (target != null) {
					
						Messaging.Sender(target.getName(), plugin.InfoFormat);
						return true;
					}
				}
				Messaging.Sender(player.getName(), "&cEsse jogador nÃ£o pÃ´de ser encontrado");
				return true;
			}
		}
		Messaging.Sender(sender, "&cVocÃª nÃ£o tem permissÃµes");
		return true;
	}
}