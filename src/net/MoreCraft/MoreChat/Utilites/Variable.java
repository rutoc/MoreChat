package net.MoreCraft.MoreChat.Utilites;

import org.bukkit.entity.Player;

import net.MoreCraft.MoreChat.MoreChat;

public class Variable {
	
	static MoreChat plugin;
	
	public Variable(MoreChat instance) {
		
		plugin = instance;
	}
	
	public static String Format(Player player, String format, String[] args) {
		
		format = format.replace("&", "§");
		format = format.replace("[(]", "【");
		format = format.replace("[)]", "】");
		format = format.replace("[Name]", player.getName());
		format = format.replace("[Display]", player.getDisplayName());
		format = format.replace("[Msg]", Util.getFinalArg(args, 0, 1));
		format = format.replace("[MsgColor]", Colorizer.parseColors(Util.getFinalArg(args, 0, 1)));
		format = format.replace("[Prefix]", plugin.getPrefix(player));
		format = format.replace("[Suffix]", plugin.getSuffix(player));
		
		return format;
	}
}
