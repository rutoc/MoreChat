package net.MoreCraft.MoreChat.Events;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.MoreCraft.MoreChat.MoreChat;
import net.MoreCraft.MoreChat.Utilites.*;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class ChatColorizer implements Listener {
	
	MoreChat plugin;
	public ChatColorizer(MoreChat instance) {
		plugin = instance;
		}
	
	@EventHandler
	public void onColorChat(AsyncPlayerChatEvent event) {
		Player player = event.getPlayer();;
		String in = event.getMessage();
		
		if (player.hasPermission("MoreChat.User.Chat.Color")){
			event.setMessage(in);
		}
		else {
				Pattern Color = Pattern.compile("&");
				Matcher matcher = Color.matcher(in);
				if (matcher.find()) {
					event.setCancelled(true);
					Messaging.Sender(player.getName(), "&cYou were not allowed to use colors!");
				}
			}
		}
	
	@EventHandler
	public void onColorCommand(PlayerCommandPreprocessEvent event) {
		Player player = event.getPlayer();;
		String in = event.getMessage();
		if (player.hasPermission("MoreChat.User.Chat.Color")){
			event.setMessage(in);
		}
		else {
				Pattern Color = Pattern.compile("&");
				Matcher matcher = Color.matcher(in);
				if (matcher.find()) {
					event.setCancelled(true);
					Messaging.Sender(player.getName(), "&cYou were not allowed to use colors!");
				}
			}
		}
	}