/**
 * Project: A00973641Gis
 * File: Arguments.java
 * Date: Feb 12, 2016
 * Time: 8:28:16 PM
 */
package A00973641.data.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Mara
 *
 */
public enum Argument {
	TOTAL("total"), BY_GAMERTAG("by gamertag"), BY_GAME("by game"), BY_COUNT("by count"), DESC("descending"), PLAYERS(
			"players"), PLATFORM("platform"), FILTER("gamertag");

	private final String argument;

	// Reverse lookup map for getting constant variable from string value
	private static final Map<String, Argument> lookup = new HashMap<>();

	static {
		for (Argument arg : Argument.values()) {
			lookup.put(arg.toString(), arg);
		}
	}

	private Argument(final String argument) {
		this.argument = argument;
	}

	@Override
	public String toString() {
		return argument;
	}

	public static Argument get(String arg) {
		return lookup.get(arg);
	}
}
