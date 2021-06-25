package net.MoreCraft.MoreChat.Commands;

import java.util.ArrayList;
import java.util.List;

import net.MoreCraft.MoreChat.MoreChat;
import net.MoreCraft.MoreChat.Events.ChatSpy;
import net.MoreCraft.MoreChat.Utilites.*;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

public class CommandChat implements CommandExecutor, TabCompleter {

	private static List<String> TabList = new ArrayList<String>();

	static {
		TabList.add("help");
		TabList.add("Color");
		TabList.add("List");
		TabList.add("Clear");
		TabList.add("Control");
		TabList.add("Spy");
		TabList.add("Admin");
		TabList.add("Reload");
		TabList.add("Version");
	}

	public MoreChat plugin;

	public CommandChat(MoreChat instance) {
		plugin = instance;
	}

	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		if ((sender instanceof Player)) {
			Player player = (Player) sender;

			if (args.length == 0) {
				Messaging.Sender(player, "&a----------&a&l[&a MoreChat Commands &a&l]&a----------");
				Messaging.Sender(player, "&a/morechat&f: &7View all commands;");
				Messaging.Sender(player, "&a/chat color&f: &7View all colors;");
				Messaging.Sender(player, "&a/chat list&f: &7View all communication channels;");
				Messaging.Sender(player, "&a/chat clear&f: &7Clears your chat;");
				Messaging.Sender(player, "&a/chat admin&f: &7View all admin commands;");
				Messaging.Sender(player, "&a/chat reload&f: &7Reload settings and database;");
				Messaging.Sender(player, "&a/chat version&f: &7View the plugin version and authors;");
				return true;
			}

			if (args[0].equalsIgnoreCase("help")) {
				Messaging.Sender(player, "&a----------&a&l[&a MoreChat Commands &a&l]&a----------");
				Messaging.Sender(player, "&a/morechat&f: &7View all commands;");
				Messaging.Sender(player, "&a/chat color&f: &7View all colors;");
				Messaging.Sender(player, "&a/chat list&f: &7View all communication channels;");
				Messaging.Sender(player, "&a/chat clear&f: &7Clears your chat;");
				Messaging.Sender(player, "&a/chat admin&f: &7View all admin commands;");
				Messaging.Sender(player, "&a/chat reload&f: &7Reload settings and database;");
				Messaging.Sender(player, "&a/chat version&f: &7View the plugin version and authors;");
				return true;
			}

			if (args[0].equalsIgnoreCase("on")) {
				if (!player.hasPermission("MoreChat.User.Chat.Disable")) {
					Messaging.Sender(player, "&cYou didn't have permission to execute this command.");
					return true;
				}

				plugin.getConfig().set("Chat.Disable.Action.Chat", false);
				plugin.getConfig().set("Chat.Disable.Action.Command", false);
				plugin.saveConfig();
				plugin.reloadConfig();

				Messaging.Sender(player, "&cChat was successfully activated!");
				return true;
			}

			if (args[0].equalsIgnoreCase("off")) {
				if (!player.hasPermission("MoreChat.User.Chat.Disable")) {
					Messaging.Sender(player, "&cYou didn't have permission to execute this command.");
					return true;
				}

				plugin.getConfig().set("Chat.Disable.Action.Chat", true);
				plugin.getConfig().set("Chat.Disable.Action.Command", true);
				plugin.saveConfig();
				plugin.reloadConfig();

				Messaging.Sender(player, "&cChat has been successfully disabled!");
				return true;
			}

			if (args[0].equalsIgnoreCase("control")) {
				if (!player.hasPermission("MoreChat.User.Chat.Control")) {
					Messaging.Sender(player, "&cYou didn't have permission to execute this command.");
					return true;
				}

				if (args.length == 1) {
					Messaging.Sender(player, "&cError!");
					Messaging.Sender(player, "&c/chat control <player> <command|message>");
					return true;
				}
				if (args.length == 2) {
					Messaging.Sender(player, "&cError!");
					Messaging.Sender(player, "&cType <command|message>");
					return true;
				}

				if (User.getPlayer(args[1]) != null) {
					User.getPlayer(args[1]).chat(Util.getFinalArg(args, 2, 3));
					return true;
				}
				Messaging.Sender(player, "&cThis player could not be found...");
				return true;
			}

			if (args[0].equalsIgnoreCase("spy")) {
				if (!player.hasPermission("MoreChat.User.Chat.Spy")) {
					Messaging.Sender(player, "&cYou didn't have permission to execute this command.");
					return true;
				}
				if (ChatSpy.players.contains(player)) {
					ChatSpy.players.remove(player);
					Messaging.Sender(player, "&7You can't see everything the players type.");
					return true;
				} else {
					ChatSpy.players.contains(player);
					ChatSpy.players.add(player);
					Messaging.Sender(player, "&7You can see everything the players type.");
					return true;
				}
			}

			if (args[0].equalsIgnoreCase("admin")) {
				if (!player.hasPermission("MoreChat.User.Chat.Help.Admin")) {
					Messaging.Sender(player, "&cYou didn't have permission to execute this command.");
					return true;
				}

				Messaging.Sender(player, "&a----------&a&l[&a MoreChat Admin &a&l]&a----------");
				Messaging.Sender(player, "&a/mute&f: &7Shut up a player;");
				Messaging.Sender(player, "&a/chat on&f: &7Turn on the chat;");
				Messaging.Sender(player, "&a/chat off&f: &7Turn off the chat;");
				Messaging.Sender(player, "&a/chat clear global&f: &7Clears all channels;");
				Messaging.Sender(player, "&a/chat control&f: &7Force a player to write something;");
				Messaging.Sender(player, "&a/chat spy&f: &7Spy on what the players say;");
				Messaging.Sender(player, "&a/staff&f: &7Staff channel;");
				return true;
			}

			if (args[0].equalsIgnoreCase("color")) {
				player.sendMessage("|§0&0§f|§1&1§f|§2&2§f|§3&3§f|§4&4§f|§5&5§f|§6&6§f|§7&7§f|§8&8§f|§9&9§f|§A&A§f|§B&B§f|§C&C§f|§D&D§f|§E&E§f|§F&F|");
				return true;
			}

			if (args[0].equalsIgnoreCase("reload") || args[0].equalsIgnoreCase("rl")) {
				if (!player.hasPermission("MoreChat.User.Reload")) {
					Messaging.Sender(player, "&cYou didn't have permission to execute this command.");
					return true;
				}
				plugin.reloadConfig();
				plugin.getConfigs();
				Messaging.Sender(player, "&a&lMoreChat &7reloaded successfully!");
				return true;
			}

			if (args[0].equalsIgnoreCase("list")) {
				Messaging.Sender(player, "&a----------&a&l[&a MoreChat Channels &a&l]&a----------");
				Messaging.Sender(player, "&a/global&f: &7Global channel;");
				Messaging.Sender(player, "&a/information&f: &7Support channel;");
				Messaging.Sender(player, "&a/pm&f: &7Private channel;");
				Messaging.Sender(player, "&a/youtube&f: &7YouTube channel;");
				Messaging.Sender(player, "&a/warning&f: &7Warning channel");
				Messaging.Sender(player, "&a/chatvip&f: &7VIP channel");
				return true;
			}

			if (args[0].equalsIgnoreCase("clear")) {
				if (!player.hasPermission("MoreChat.User.Chat.Clear")) {
					Messaging.Sender(player, "&cYou didn't have permission to execute this command.");
					return true;
				}
				
				for (int msgs = 1; msgs <= 100; msgs++) {
					player.sendMessage("");
				}
				if (args.length == 2) {
					if (args[1].equalsIgnoreCase("global")) {
						for (int msgs = 1; msgs <= 100; msgs++) {
							User.broadcastMessage("");
						}
						Messaging.Sender(player, "&7The chat was successfully cleaned!");
						return true;
					}
				}
				Messaging.Sender(player, "&7Chat cleaned!");
				return true;
			}

			if (args[0].equalsIgnoreCase("permision") || args[0].equalsIgnoreCase("perm")) {
				Messaging.Sender(player, "&a----------&a&l[&a MoreChat Permissions &a&l]&a----------");
				Messaging.Sender(player, "&aPermission&f: &7Em Breve!");
				return true;
			}

			if (args[0].equalsIgnoreCase("version")) {
				Messaging.Sender(player, "&a----------&a&l[&a MoreChat Version &a&l]&a----------");
				Messaging.Sender(player, "&aVersion&f: &75.0");
				Messaging.Sender(player, "&aDependencies&f: &7Vault and PermissionsEx or similar");
				Messaging.Sender(player, "&aAuthors&f: &cJohnnyPlayy and RuToc");
				return true;
			}
			Messaging.Sender(player, "&7Type &a/" + commandLabel + " help &7to view all commands.");
			return false;
		}
		Logger.info("Command used only in game!");
		return false;
	}

	@Override
	public List<String> onTabComplete(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		if (args.length == 1) {
			return TabList;
		}
		return null;
	}
}