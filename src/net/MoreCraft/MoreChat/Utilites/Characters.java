package net.MoreCraft.MoreChat.Utilites;

public class Characters {

	public static String Replace(String input) {
		final String[][] Utf8 = new String[][] {

				{ "[<3]", "❤" }, { "[CHECK]", "✔" },

				{ "[(]", "【" }, { "[)]", "】" },

				{ "[ONE]", "❶" }, { "[TWO]", "❷" }, { "[THREE]", "❸" }, { "[FOUR]", "❹" }, { "[FIVE]", "❺" },
				{ "[SIX]", "❻" }, { "[SEVEN]", "❼" }, { "[EIGHT]", "❽" }, { "[NINE]", "❾" }, { "[TEN]", "❿" }, };

		for (String[] List : Utf8) {
			input = input.replace(List[0], List[1]);
		}
		return input;
	}
}