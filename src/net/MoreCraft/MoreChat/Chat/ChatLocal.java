package net.MoreCraft.MoreChat.Chat;

import java.util.HashSet;

import net.MoreCraft.MoreChat.MoreChat;
import net.MoreCraft.MoreChat.Utilites.Colorizer;
import net.MoreCraft.MoreChat.Utilites.Messaging;
import net.MoreCraft.MoreChat.Utilites.User;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatLocal implements Listener {
	MoreChat plugin;

	public ChatLocal(MoreChat instance) {
		plugin = instance;
	}

	@EventHandler(priority = EventPriority.LOW)
	public boolean onPlayerChat(AsyncPlayerChatEvent event) {
		Player player = event.getPlayer();
		
		// Config
		plugin.ChatRange = plugin.getConfig().getDouble("Chat.Local.Range");
		plugin.NotifyRange = plugin.getConfig().getBoolean("Chat.Local.NotifyRange");
		plugin.MsgRange = plugin.getConfig().getString("Chat.Local.Message");
		plugin.LocalFormat = plugin.getConfig().getString("Chat.Local.Format");
		plugin.LocalFormat = plugin.LocalFormat.replace("&", "§");
		plugin.LocalFormat = plugin.LocalFormat.replace("[(]", "【");
		plugin.LocalFormat = plugin.LocalFormat.replace("[)]", "】‘");
		plugin.LocalFormat = plugin.LocalFormat.replace("[Name]", player.getName());
		plugin.LocalFormat = plugin.LocalFormat.replace("[DisplayName]", player.getDisplayName());
		plugin.LocalFormat = plugin.LocalFormat.replace("[Msg]", event.getMessage());
		plugin.LocalFormat = plugin.LocalFormat.replace("[MsgColor]", Colorizer.parseColors(event.getMessage()));
		plugin.LocalFormat = plugin.LocalFormat.replace("[Prefix]", plugin.getPrefix(player));
		plugin.LocalFormat = plugin.LocalFormat.replace("[Suffix]", plugin.getSuffix(player));

		// Config End

		for (Player p : new HashSet<Player>(event.getRecipients())) {
			if ((player.getWorld() != p.getWorld()) || (getRange(User.getPlayer(player.getName()), p.getLocation()))) {
				event.getRecipients().remove(p);
			}
		}

		event.setFormat(plugin.LocalFormat);

		if (event.getRecipients().size() == 1) {
			if ((plugin.NotifyRange))
				Messaging.Sender(player.getName(), plugin.MsgRange);
			event.setCancelled(true);
		}
		return false;
	}

	private Boolean getRange(Player player, Location location) {
		if (player.getLocation().equals(location))
			return false;
		if (player.getLocation().distanceSquared(location) > plugin.ChatRange * 100) {
			return true;
		}
		return false;
	}
}