package net.MoreCraft.MoreChat.Utilites;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.BanList;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class User {
	

	public static void broadcastMessage(String arg0) {
		for (Player player : Bukkit.getOnlinePlayers()) {
			player.sendMessage(arg0);
		}
	}
	
	public static Player getPlayer(String arg0) {
		for (Player player : Bukkit.getOnlinePlayers()) {
			if (player.getName().equalsIgnoreCase(arg0)) {
				return player;
			}
		}
		return null;
	}
	
	public static List<Player> matchPlayer(String arg0) {
		List<Player> matches = new ArrayList<Player>();
		for (Player player : Bukkit.getOnlinePlayers()) {
			if (player.getName().substring(0, Math.min(player.getName().length(), arg0.length())).equalsIgnoreCase(arg0)) {
				matches.add(player);
				}
			}
		return matches;
	}
	
	public static void teleport(Player player, Location location) {
		player.teleport(new Location(location.getWorld(), location.getX(), location.getY(), location.getZ(), location.getYaw(), location.getPitch()));
	}
	
	public static void setBanned(String name, boolean args) {
		if(args) {
			Player player = getPlayer(name);
			Bukkit.getBanList(BanList.Type.NAME).addBan(player.getName(), null, null, null);
			player.kickPlayer(name);
		}
	}
}
