package net.MoreCraft.MoreChat.Events;

import java.util.HashMap;

import net.MoreCraft.MoreChat.MoreChat;
import net.MoreCraft.MoreChat.Utilites.*;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class ChatSpam implements Listener {
	
	private static HashMap<String, Long> Cooldowns = new HashMap<String, Long>();
    
    public MoreChat plugin;
    public ChatSpam(MoreChat instance) {
        plugin = instance;
    }
    
    @EventHandler(priority=EventPriority.LOW)
    public boolean onChatSpam(AsyncPlayerChatEvent event) {
    	Player player = event.getPlayer();;
    	if(plugin.getConfig().getBoolean("Chat.Blocker.Spam.Action.Chat")) {
    		if (player.hasPermission("MoreChat.User.Chat.Spam")) {
    			return true;
    		}
    	
    		Long time = Long.valueOf(System.currentTimeMillis());
    		Long lastUse = Cooldowns.get(player.getName());
    	
    		if (lastUse == null) {
    			lastUse = Long.valueOf(0L);
    		}
    	
    		if (lastUse.longValue() + 1000L > time.longValue()) {
    			Messaging.Sender(player.getName(), plugin.getConfig().getString("Chat.Blocker.Spam.Message"));
    			event.setCancelled(true);
    		}
    		
    		Cooldowns.remove(player.getName());
    		Cooldowns.put(player.getName(), time);
    	}
		return false;
    }
    
    @EventHandler(priority=EventPriority.LOW)
    public boolean onCommandSpam(PlayerCommandPreprocessEvent event) {
    	Player player = event.getPlayer();;
    	if(plugin.getConfig().getBoolean("Chat.Blocker.Spam.Action.Command")) {
    		if (player.hasPermission("MoreChat.User.Chat.Spam")) {
    			return true;
    		}
    	
    		Long time = Long.valueOf(System.currentTimeMillis());
    		Long lastUse = Cooldowns.get(player.getName());
    	
    		if (lastUse == null) {
    			lastUse = Long.valueOf(0L);
    		}
    	
    		if (lastUse.longValue() + 1000L > time.longValue()) {
    			Messaging.Sender(player.getName(), plugin.getConfig().getString("Chat.Blocker.Spam.Message"));
    			event.setCancelled(true);
    		}
    		
    		Cooldowns.remove(player.getName());
    		Cooldowns.put(player.getName(), time);
    	}
		return false;
    }
}