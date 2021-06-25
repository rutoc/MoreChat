package net.MoreCraft.MoreChat.Events;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.MoreCraft.MoreChat.MoreChat;
import net.MoreCraft.MoreChat.Utilites.User;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class ChatBlocker implements Listener {
	MoreChat plugin;

	public ChatBlocker(MoreChat instance) {
		plugin = instance;
	}

	// Blocker ip chat local
	@EventHandler
	public void onChatIp(AsyncPlayerChatEvent event) {
		
		Player player = event.getPlayer();
		
		String in = event.getMessage();
		if (player.hasPermission("MoreChat.User.Chat.Ip.Ignore")) {
			event.setMessage(in);
			
		} else {
			
			Pattern pattern = Pattern.compile("(([0-9]|[1-9][0-9]|1[0-9]{2}|2[0-4][0-9]|25[0-5])\\.){3}([0-9]|[1-9][0-9]|1[0-9]{2}|2[0-4][0-9]|25[0-5])");
			Matcher matcher = pattern.matcher(in);
			if (matcher.find()) {
				String action = plugin.getConfig().getString("Chat.Blocker.Ip.Action");

				if (action.equals("Block")) {
					event.setCancelled(true);
					if (plugin.getConfig().getString("Chat.Blocker.Ip.Broadcast").equals("true")) {
						User.broadcastMessage(ChatColor.RED + player.getName() + " failed to post a IP");
					}
				}

				if (action.equals("kick")) {
					event.setCancelled(true);
					player.kickPlayer(
							"" + ChatColor.RED + ChatColor.BOLD + "You were kicked for posting an IP");

					if (plugin.getConfig().getString("Chat.Blocker.Ip.Broadcast").equals("true")) {
						User.broadcastMessage(ChatColor.RED + player.getName() + " was kicked for posting an IP");
					}
				}

				if (action.equals("ban")) {
					event.setCancelled(true);

					User.setBanned(player.getName(), true);
					player.kickPlayer("" + ChatColor.RED + ChatColor.BOLD + "You were banned for posting an IP");

					if (plugin.getConfig().getString("Chat.Blocker.Ip.Broadcast").equals("true")) {
						User.broadcastMessage(ChatColor.RED + player.getName() + " was banned for posting an IP");
					}
				}
			}
		}
	}

	// Blocker ip command
	@EventHandler
	public void onCommandIp(PlayerCommandPreprocessEvent event) {
		
		Player player = event.getPlayer();;
		
		String in = event.getMessage();
		
		if (player.hasPermission("MoreChat.User.Chat.Ip.Ignore")) {
			event.setMessage(in);
			
		} else {
			
			Pattern pattern = Pattern.compile("(([0-9]|[1-9][0-9]|1[0-9]{2}|2[0-4][0-9]|25[0-5])\\.){3}([0-9]|[1-9][0-9]|1[0-9]{2}|2[0-4][0-9]|25[0-5])");
			Matcher matcher = pattern.matcher(in);
			if (matcher.find()) {
				String action = plugin.getConfig().getString("Chat.Blocker.Ip.Action");

				if (action.equals("Block")) {
					event.setCancelled(true);
					if (plugin.getConfig().getString("Chat.Blocker.Ip.Broadcast").equals("true")) {
						User.broadcastMessage(ChatColor.RED + player.getName() + " failed to post a IP");
					}
				}

				if (action.equals("kick")) {
					event.setCancelled(true);
					player.kickPlayer("" + ChatColor.RED + ChatColor.BOLD + "You were kicked for posting an IP");

					if (plugin.getConfig().getString("Chat.Blocker.Ip.Broadcast").equals("true")) {
						User.broadcastMessage(ChatColor.RED + player.getName() + " was kicked for posting an IP");
					}
				}

				if (action.equals("ban")) {
					event.setCancelled(true);
					User.setBanned(player.getName(), true);
					player.kickPlayer("" + ChatColor.RED + ChatColor.BOLD + "You were banned for posting an IP");

					if (plugin.getConfig().getString("Chat.Blocker.Ip.Broadcast").equals("true")) {
						User.broadcastMessage(ChatColor.RED + player.getName() + " was banned for posting an IP");
					}
				}
			}
		}
	}

	// Blocker Domain chat local
	@EventHandler
	public void onChatURL(AsyncPlayerChatEvent event) {
		
		Player player = event.getPlayer();;
		String in = event.getMessage();
		
		if (player.hasPermission("MoreChat.User.Chat.URL.Ignore")) {
			event.setMessage(in);
			
		} else {
			
			Pattern pattern = Pattern.compile("([a-zA-Z0-9\\-]+\\.)+[a-zA-Z]{2,}(:[0-9]{1,5})?");
			Matcher matcher = pattern.matcher(in);
			if (matcher.find()) {
				String action = plugin.getConfig().getString("Chat.Blocker.URL.Action");

				if (action.equals("Block")) {
					event.setCancelled(true);
					if (plugin.getConfig().getString("Chat.Blocker.URL.Broadcast").equals("true")) {
						User.broadcastMessage(ChatColor.RED + player.getName() + " failed to post a URL");
					}
				}

				if (action.equals("kick")) {
					event.setCancelled(true);
					player.kickPlayer("" + ChatColor.RED + ChatColor.BOLD + "You were kicked for posting an URL");

					if (plugin.getConfig().getString("Chat.Blocker.URL.Broadcast").equals("true")) {
						User.broadcastMessage(ChatColor.RED + player.getName() + " was kicked for posting an URL");
					}
				}

				if (action.equals("ban")) {
					event.setCancelled(true);
					User.setBanned(player.getName(), true);
					player.kickPlayer("" + ChatColor.RED + ChatColor.BOLD + "You were banned for posting an IP");

					if (plugin.getConfig().getString("Chat.Blocker.URL.Broadcast").equals("true")) {
						User.broadcastMessage(ChatColor.RED + player.getName() + " was banned for posting an IP");
					}
				}
			}
		}
	}

	// Blocker URL command
	@EventHandler
	public void onCommandURL(PlayerCommandPreprocessEvent event) {
		
		Player player = event.getPlayer();;
		String in = event.getMessage();
		
		if (player.hasPermission("MoreChat.User.Chat.URL.Ignore")) {
			event.setMessage(in);
			
		} else {
			
			Pattern pattern = Pattern.compile("([a-zA-Z0-9\\-]+\\.)+[a-zA-Z]{2,}(:[0-9]{1,5})?");
			Matcher matcher = pattern.matcher(in);
			if (matcher.find()) {
				String action = plugin.getConfig().getString("Chat.Blocker.URL.Action");

				if (action.equals("Block")) {
					event.setCancelled(true);
					if (plugin.getConfig().getString("Chat.Blocker.URL.Broadcast").equals("true")) {
						User.broadcastMessage(ChatColor.RED + player.getName() + " failed to post a URL");
					}
				}

				if (action.equals("kick")) {
					event.setCancelled(true);
					player.kickPlayer("" + ChatColor.RED + ChatColor.BOLD + "You were kicked for posting an IP");

					if (plugin.getConfig().getString("Chat.Blocker.URL.Broadcast").equals("true")) {
						User.broadcastMessage(ChatColor.RED + player.getName() + " was kicked for posting an IP");
					}
				}

				if (action.equals("ban")) {
					event.setCancelled(true);
					User.setBanned(player.getName(), true);
					player.kickPlayer(
							"" + ChatColor.RED + ChatColor.BOLD + "You were banned for posting an IP");

					if (plugin.getConfig().getString("Chat.Blocker.URL.Broadcast").equals("true")) {
						User.broadcastMessage(ChatColor.RED + player.getName() + " was banned for posting an IP");
					}
				}
			}
		}
	}
}