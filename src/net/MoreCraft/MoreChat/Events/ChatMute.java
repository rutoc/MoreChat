	package net.MoreCraft.MoreChat.Events;

import java.io.File;

import net.MoreCraft.MoreChat.MoreChat;
import net.MoreCraft.MoreChat.Utilites.*;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class ChatMute implements Listener {
    
    public MoreChat plugin;
    public ChatMute(MoreChat instance) {
        plugin = instance;
    }
    
    @EventHandler(priority = EventPriority.HIGH)
    public void onChatMute(AsyncPlayerChatEvent event) {
    	Player player = event.getPlayer();;
    	
    	File file = new File("plugins" + File.separator + "MoreChat" + File.separator + "Data.dat");
    	FileConfiguration Config = YamlConfiguration.loadConfiguration(file);
    	
    	if(!player.hasPermission("MoreChat.User.Chat.Mute.Ignore")) {
    		if (Config.getBoolean(player.getName() + "_Muted")) {
    			Messaging.Sender(player.getName(), "&cYou're silenced!");
    			event.setCancelled(true);
    		}
    	}
    }
    
    @EventHandler(priority = EventPriority.HIGH)
    public void onCommandMute(PlayerCommandPreprocessEvent event) {
    	Player player = event.getPlayer();;
    	
    	for(String CommandList : plugin.getConfig().getStringList("Chat.Blocker.Mute.Action.Block.List")) {
    		
    		String[] args = event.getMessage().split(" ");
    		
    		if(args[0].equalsIgnoreCase("/" + CommandList)) {
    			
    			File file = new File("plugins" + File.separator + "MoreChat" + File.separator + "Data.dat");
    			FileConfiguration Config = YamlConfiguration.loadConfiguration(file);
    			
    			if(!player.hasPermission("MoreChat.User.Chat.Mute.Ignore")) {
    				if (Config.getBoolean(player.getName() + "_Muted")) {
    					Messaging.Sender(player.getName(), "&cYou're silenced!");
    					event.setCancelled(true);
    				}
    			}
    		}
    	}
    }
}