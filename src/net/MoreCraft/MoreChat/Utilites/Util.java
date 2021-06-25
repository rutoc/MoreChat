package net.MoreCraft.MoreChat.Utilites;

public class Util {

	public static String getFinalArg(String[] args, int args0) {
		StringBuilder builder = new StringBuilder();
		for (int i = args0; i < args.length; i++) {
			builder.append(" ").append(args[i]);
		}
		return builder.toString();
	}

	public static String getFinalArg(String[] args, int args0, int args1) {
		StringBuilder builder = new StringBuilder(args[args0]);
		for (int i = args1; i < args.length; i++) {
			builder.append(" ").append(args[i]);
		}
		return builder.toString();
	}
}