package net.MoreCraft.MoreChat;

import java.util.Arrays;

import net.MoreCraft.MoreChat.Chat.*;
import net.MoreCraft.MoreChat.Commands.*;
import net.MoreCraft.MoreChat.Events.*;
import net.MoreCraft.MoreChat.Utilites.*;
import net.milkbowl.vault.chat.Chat;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

public class MoreChat extends JavaPlugin {
	
	public Double ChatRange;
	public Boolean NotifyRange;
	public String MsgRange = "";
	public String LocalFormat;
	public String GlobalFormat;
	public String InfoFormat;
	public String PrivateFormat;
	public String WarningFormat;
	public String YouTubeFormat;
	public String ServerFormat;
	public String StaffFormat;
	public String ColorNamePrivate;
	public String ColorMessagePrivate;
	public String CapsLockPercentage;
	public String CapsLockMessage;
	
	
	public static MoreChat plugin;
	
    public static Chat chat = null;
	
    public void onEnable() {
		Logger.info("MoreChat files uploaded successfully!");
		Logger.info("MoreChat - Loading files and database...");
		
		plugin = this;
	
		if (Bukkit.getServer().getPluginManager().getPlugin("Vault") == null) {
			Logger.info("&cError: Vault not found!");
			
			Bukkit.getServer().getPluginManager().disablePlugin(this);
			return;
		}
		
		setupChat();
		
		getRegister();
		getCommands();
		getConfigs();
    }
	
    // Vault Chat Support
    public boolean setupChat() {
        RegisteredServiceProvider<Chat> chatProvider = Bukkit.getServer().getServicesManager().getRegistration(net.milkbowl.vault.chat.Chat.class);
        if (chatProvider != null) {
            chat = chatProvider.getProvider();
        }
        return (chat != null);
    }
    
    public static Chat getChat() {
    	return chat;
    }
    
	public String getPrefix(Player player) {
		String Prefix = getChat().getPlayerPrefix(player);
		Prefix = Colorizer.parseColors(Prefix);
		return Prefix;
	}

	public String getSuffix(Player player) {
		String Suffix = getChat().getPlayerSuffix(player);
		Suffix = Colorizer.parseColors(Suffix);
		return Suffix;
	}
    
    public void getRegister() {
    	PluginManager Register = getServer().getPluginManager();
    	Register.registerEvents(new ChatBlocker(this), this);
    	Register.registerEvents(new ChatCapsLock(this), this);
    	Register.registerEvents(new ChatColorizer(this), this);
    	Register.registerEvents(new ChatDisable(this), this);
    	Register.registerEvents(new ChatMute(this), this);
    	Register.registerEvents(new ChatSpam(this), this);
    	Register.registerEvents(new ChatSpy(this), this);
    	Register.registerEvents(new ChatWearing(this), this);
    	Register.registerEvents(new ChatLocal(this), this);
    }
	
	public void getCommands() {

		getCommand("morechat").setExecutor(new CommandChat(this));
		getCommand("mute").setExecutor(new CommandMute(this));
		getCommand("global").setExecutor(new CommandChatGlobal(this));
		getCommand("information").setExecutor(new CommandChatInfo(this));
		getCommand("private").setExecutor(new CommandChatPrivate(this));
		getCommand("chatserver").setExecutor(new CommandChatServer(this));
		getCommand("chatstaff").setExecutor(new CommandChatStaff(this));	
		getCommand("warning").setExecutor(new CommandChatWarning(this));
		getCommand("youtube").setExecutor(new CommandChatYouTube(this));
	}
	
	public void getConfigs() {
        getConfig().options().copyDefaults(true);
        getConfig().addDefault("Chat.Local.Range", 50);
        getConfig().addDefault("Chat.Local.NotifyRange", true);
        getConfig().addDefault("Chat.Local.Message", "&cThere is no one here...");
        getConfig().addDefault("Chat.Local.Format", "[Prefix][Name][Suffix]&8: &e[MsgColor]");
        getConfig().addDefault("Chat.Global.Format", "&7[(]&a&lG&7[)][Prefix][Name][Suffix]&8: &7[MsgColor]");
        getConfig().addDefault("Chat.Info.Format", "&7[(]&b&lI&7[)]&f[Name]&8: &b[Msg]");
        getConfig().addDefault("Chat.Warning.Format", "&7[(]&e&lWarning&7[)]&7[Msg]");
        getConfig().addDefault("Chat.YouTube.Format", "&7[(]&f&lYou&c&lTube&7[)]&f[Name]&8: &7[MsgColor]");
        getConfig().addDefault("Chat.Server.Format", "&7[(]&f&lServer&7[)]&7[Msg]");
        getConfig().addDefault("Chat.Staff.Format", "&7[(]&c&lS&7[)]&7[Name]&8: &e[Msg]");
        //getConfig().addDefault("Chat.Private.Format", "&7[VocÃª -> [Name]] [Msg]");
        getConfig().addDefault("Chat.Private.Color.Name", "&7");
        getConfig().addDefault("Chat.Private.Color.Message", "&7");
        getConfig().addDefault("Chat.Blocker.CapsLock.Action.Chat", true);
        getConfig().addDefault("Chat.Blocker.CapsLock.Action.Command", true);
        getConfig().addDefault("Chat.Blocker.CapsLock.MinLength", 4);
        getConfig().addDefault("Chat.Blocker.CapsLock.CapsPercentage", 25);
        getConfig().addDefault("Chat.Blocker.Ip.Action", "kick");
        getConfig().addDefault("Chat.Blocker.Ip.Broadcast", true);
        getConfig().addDefault("Chat.Blocker.URL.Action", "kick");
        getConfig().addDefault("Chat.Blocker.URL.Broadcast", true);
        getConfig().addDefault("Chat.Blocker.Mute.Action.Chat", true);
        getConfig().addDefault("Chat.Blocker.Mute.Action.Command", true);
        getConfig().addDefault("Chat.Blocker.Mute.Action.Block.List", Arrays.asList(new String[] { "global", "g", "info", "youtube", "yt", "warning", "w" }));
        getConfig().addDefault("Chat.Blocker.Spam.Action.Chat", true);
        getConfig().addDefault("Chat.Blocker.Spam.Action.Command", true);
        //getConfig().addDefault("Chat.Blocker.Spam.Time", 3);
        getConfig().addDefault("Chat.Blocker.Spam.Message", "&cPlease don't repeat the messages in the chat...");
        getConfig().addDefault("Chat.Blocker.Wearing.Action.Chat", true);
        getConfig().addDefault("Chat.Blocker.Wearing.Action.Command", true);
        getConfig().addDefault("Chat.Blocker.Wearing.Message", "&cI'm going to wash your mouth with soap!");
        getConfig().addDefault("Chat.Blocker.Wearing.List", Arrays.asList(new String[] { "fuck", "ass", "bitch" }));
        getConfig().addDefault("Chat.Blocker.Wearing.Action.Chat", true);
        getConfig().addDefault("Chat.Disable.Action.Chat", false);
        getConfig().addDefault("Chat.Disable.Action.Command", false);
        
        saveConfig();
	}
}