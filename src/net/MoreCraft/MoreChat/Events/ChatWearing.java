package net.MoreCraft.MoreChat.Events;

import net.MoreCraft.MoreChat.MoreChat;
import net.MoreCraft.MoreChat.Utilites.*;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class ChatWearing implements Listener {
	MoreChat plugin;
	public ChatWearing(MoreChat instance) {
		plugin = instance;
	}
	
	@EventHandler
	public boolean onChatWearing(AsyncPlayerChatEvent event) {
		
		Player player = event.getPlayer();
		
		String[] args = event.getMessage().split(" ");
		
		for(String s : plugin.getConfig().getStringList("Chat.Blocker.Wearing.List")) {
			
			//if(event.getMessage().toLowerCase().contains(s.toLowerCase())) {
			if(args[0].equalsIgnoreCase(s)) {
				
				if(!(player.hasPermission("MoreChat.User.Chat.Wearing.Ignore"))) {
					
					if (plugin.getConfig().getBoolean("Chat.Blocker.Wearing.Action.Chat")) 
						
						Messaging.Sender(player.getName(), plugin.getConfig().getString("Chat.Blocker.Wearing.Message"));
						event.setCancelled(plugin.getConfig().getBoolean("Chat.Blocker.Wearing.Action.Chat"));
						
					return true;
					
				} else {
					
					return true;
				}
			}
		}
		return false;
	}
	
	@EventHandler
	public boolean onCommandWearing(PlayerCommandPreprocessEvent event) {
		
		Player player = event.getPlayer();
		
		String[] args = event.getMessage().split(" ");
		
		for(String s : plugin.getConfig().getStringList("Chat.Blocker.Wearing.List")) {
			
			//if(event.getMessage().toLowerCase().contains(s.toLowerCase())) {
			if(args[0].equalsIgnoreCase(s)) {
				
				if(!(player.hasPermission("MoreChat.User.Chat.Wearing.Ignore"))) {
					
					if (plugin.getConfig().getBoolean("Chat.Blocker.Wearing.Action.Command")) 
						
						Messaging.Sender(player.getName(), plugin.getConfig().getString("Chat.Blocker.Wearing.Message"));
						event.setCancelled(plugin.getConfig().getBoolean("Chat.Blocker.Wearing.Action.Command"));
						
					return true;
					
				} else {
					
					return true;
				}
			}
		}
		return false;
	}
}