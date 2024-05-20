package net.MoreCraft.MoreChat.Chat;

import net.MoreCraft.MoreChat.MoreChat;
import net.MoreCraft.MoreChat.Utilites.*;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandChatInfo implements CommandExecutor {

	MoreChat plugin;

	public CommandChatInfo(MoreChat instance) {
		plugin = instance;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		if (!(sender instanceof Player)) {
			Logger.info("ERROR: Command used only in-game!");
			return true;
		}
		
		Player player = (Player) sender;

		if (sender.hasPermission("MoreChat.User.Chat.Info")) {
			if (cmd.getName().equalsIgnoreCase("information")) {

				if (args.length == 0) {

					Messaging.Sender(player.getName(), "&cError");
					Messaging.Sender(player.getName(), "&cUse: /" + commandLabel + " <message>");
					return true;
				}
				
				String format = plugin.getConfig().getString("Chat.Info.Format");

				User.broadcastMessage(Variable.Format(player, format, args));
				return true;
			}
		}
		Messaging.Sender(sender, "&cYou don't have permissions!");
		return true;
	}
}