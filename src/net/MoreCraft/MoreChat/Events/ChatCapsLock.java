package net.MoreCraft.MoreChat.Events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import net.MoreCraft.MoreChat.MoreChat;
import net.MoreCraft.MoreChat.Utilites.Messaging;

public class ChatCapsLock implements Listener {
	
	public MoreChat plugin;
    public ChatCapsLock(MoreChat instance) {
        plugin = instance;
    }
    
    @EventHandler(priority=EventPriority.LOW)
    public boolean onChatCaps(AsyncPlayerChatEvent event) {
    	Player player = event.getPlayer();;
    	
    	int MinLength = plugin.getConfig().getInt("Chat.Blocker.CapsLock.MinLength");
    	int CapsPercentage = plugin.getConfig().getInt("Chat.Blocker.CapsLock.CapsPercentage");
    	
    	if(!player.hasPermission("MoreChat.User.Chat.CapsLock")) {
    		if(plugin.getConfig().getBoolean("Chat.Blocker.CapsLock.Action.Chat")) {
    			if(event.getMessage().length() >= MinLength) {
    				if(getUppercasePercentage(event.getMessage()) > CapsPercentage) {
    					Messaging.Sender(player.getName(), "&cYou can't use " + CapsPercentage + "% of capital letters in the chat.");
    					event.setCancelled(true);
    					return true;
    				}
    			}
    		}
    	}
		return false;
    }
    
    @EventHandler(priority=EventPriority.LOW)
    public boolean onCommandCaps(PlayerCommandPreprocessEvent event) {
    	Player player = event.getPlayer();;
    	
    	int MinLength = plugin.getConfig().getInt("Chat.Blocker.CapsLock.MinLength");
    	int CapsPercentage = plugin.getConfig().getInt("Chat.Blocker.CapsLock.CapsPercentage");
    	
    	if(!player.hasPermission("MoreChat.User.Chat.CapsLock")) {
    		if(plugin.getConfig().getBoolean("Chat.Blocker.CapsLock.Action.Command")) {
    			if(event.getMessage().length() >= MinLength) {
    				if(getUppercasePercentage(event.getMessage()) > CapsPercentage) {
    					Messaging.Sender(player.getName(), "&cYou can't use " + CapsPercentage + "% of capital letters in the chat.");
    					event.setCancelled(true);
    					return true;
    				}
    			}
    		}
    	}
		return false;
    }
    
    public static boolean isUppercase(char c) {
    	return Character.isUpperCase(c);
    }
    
    public static double getUppercasePercentage(String string) {
    	double upperCase = 0.0D;
    	for (char c : string.toCharArray()) {
    		if (isUppercase(c)) {
    			upperCase += 1.0D;
    		}
    	}
    	return upperCase / string.length() * 100.0D;
    }
}
