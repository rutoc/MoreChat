package net.MoreCraft.MoreChat.Events;

import net.MoreCraft.MoreChat.MoreChat;
import net.MoreCraft.MoreChat.Utilites.*;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class ChatDisable implements Listener {
    
    public MoreChat plugin;
    public ChatDisable(MoreChat instance) {
        plugin = instance;
    }
    
    @EventHandler
    public boolean onChatDisable(AsyncPlayerChatEvent event) {
    	Player player = event.getPlayer();;
    	
    	if(!player.hasPermission("MoreChat.User.Chat.Disable.Ignore")) {
    		if (plugin.getConfig().getBoolean("Chat.Disable.Action.Chat")) {
    			Messaging.Sender(player.getName(), "&cOops, the chat is currently disabled!");
    			event.setCancelled(true);
    		}
    	}
		return false;
    }
    
    @EventHandler
    public boolean onCommandDisable(PlayerCommandPreprocessEvent event) {
    	Player player = event.getPlayer();;
    	
    	for(String CommandList : plugin.getConfig().getStringList("Chat.Blocker.Mute.Action.Block.List")) {
    		
    		String[] args = event.getMessage().split(" ");
    		
    		if(args[0].equalsIgnoreCase("/" + CommandList)) {
    			if(!player.hasPermission("MoreChat.User.Chat.Disable.Ignore")) {
    				if (plugin.getConfig().getBoolean("Chat.Disable.Action.Command")) {
    					Messaging.Sender(player.getName(), "&cOops, the chat is currently disabled!");
    					event.setCancelled(true);
    				}
    			}
    		}
    	}
		return false;
    }
}

