package net.MoreCraft.MoreChat.Commands;

import java.io.File;
import java.io.IOException;

import net.MoreCraft.MoreChat.MoreChat;
import net.MoreCraft.MoreChat.Utilites.*;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

public class CommandMute implements CommandExecutor {

	MoreChat plugin;

	public CommandMute(MoreChat instance) {
		plugin = instance;
	}

	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
        if (!(sender instanceof Player)) {
        	Logger.info("Esse comando pode ser usado somente no jogo");
            return true;
        }
        
        Player player = (Player) sender;
        if (cmd.getName().equalsIgnoreCase("mute")) {
            if (!player.hasPermission("MoreChat.User.Chat.Mute")) {
            	Messaging.Sender(sender, "&cVocê não tem permissões");
                return true;
            }
            
            if (args.length == 1) {
                if (User.getPlayer(args[0]) != null) {
                    Player target = User.getPlayer(args[0]);
                    FileConfiguration config;
                    File file = new File("plugins" + File.separator + "MoreChat" + File.separator + "Data.dat");
                    config = YamlConfiguration.loadConfiguration(file);
                    if (!config.getBoolean(target.getName() + "_Muted")) {
                    	config.set(target.getName() + "_Muted", true);
                        try {
                            config.save(file);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        Messaging.Sender(player, "&7" + target.getName() + " foi Silenciado");
                        Messaging.Sender(target, "&7Você foi Silenciado.");
                    } 
                    else {
                        config.set(target.getName() + "_Muted", false);
                        try {
                            config.save(file);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        Messaging.Sender(player, "&7" + target.getName() + " não esta mais Silenciado");
                        Messaging.Sender(target, "&7Você não esta mais Silenciado.");
                    }
                } 
                else {
                	Messaging.Sender(player.getName(), "&cEsse jogador não pôde ser encontrado");
                }
            } else if (args.length > 1 || args.length < 1) {
				Messaging.Sender(player.getName(), "&cErro");
				Messaging.Sender(player.getName(), "&cUso: /" + commandLabel + " <jogador>");
            }
        }
        return true;
    }
}
