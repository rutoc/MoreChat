package net.MoreCraft.MoreChat.Events;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import net.MoreCraft.MoreChat.MoreChat;
import net.MoreCraft.MoreChat.Utilites.Messaging;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class ChatSpy implements Listener {
    
    public MoreChat plugin;
    public ChatSpy(MoreChat instance) {
        plugin = instance;
    }
    
    public static List<Player> players = new ArrayList<Player>();
    
	@EventHandler
	public void onCommandSend(PlayerCommandPreprocessEvent event) {
		if(!event.isCancelled()) {
			Iterator<Player> it = players.iterator();
			while(it.hasNext()) {
				Player player = it.next();
				Messaging.Sender(player.getName(), "&a" + event.getPlayer().getName() + "&7" + ": use " + "&c" + event.getMessage());
			}
		}
	}
	@EventHandler
	public void onChatSend(AsyncPlayerChatEvent event) {
		if(!event.isCancelled()) {
			Iterator<Player> it = players.iterator();
			while(it.hasNext()) {
				Player player = it.next();
				Messaging.Sender(player.getName(), "&a" + event.getPlayer().getName() + "&7" + ": type " + "&c" + event.getMessage());
			}
		}
	}
	
	@EventHandler
	public void onLeaveQuit(PlayerQuitEvent event) {
		if(players.contains(event.getPlayer())) {
			players.remove(event.getPlayer());
			return;
		}
	}
}

