/**
 * Project: A00973641Gis
 * File: Report.java
 * Date: Feb 12, 2016
 * Time: 7:31:44 PM
 */
package A00973641.io;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import A00973641.data.DataMap;
import A00973641.data.DisplayData;
import A00973641.data.Player;
import A00973641.data.enums.Argument;
import A00973641.data.enums.Platform;
import A00973641.util.SetData;
import A00973641.util.sort.CompareByCount;
import A00973641.util.sort.CompareByGameName;
import A00973641.util.sort.CompareByGamerTag;

/**
 * @author Mara
 *
 */
public class Report {

	private static final int MAX_ARGUMENTS = 4;
	private static final Logger LOG = LogManager.getLogger(Report.class);
	private List<DisplayData> dataset;

	/**
	 * Constructor for Report
	 */
	public Report(String argument, DataMap datamap) {
		SetData.set(datamap);
		dataset = SetData.get();
		// Default: sort by gamertag, ascending
		Collections.sort(dataset, new CompareByGamerTag());

		// Store all arguments in an array
		List<String> arguments = Arrays.asList(argument.toLowerCase().split("\\s"));

		// Check if number of arguments exceeded allowable maximum
		if (arguments.size() > MAX_ARGUMENTS) {
			LOG.error("Exceeded maximum allowable arguments: " + MAX_ARGUMENTS);
			return;
		}

		// Default: print all data
		if (argument.equals("") || argument.length() == 0) {
			printData();
			return;
		}

		// Exit if "by_count" and "by_game" are entered
		if (arguments.contains(Argument.BY_COUNT.toString()) && arguments.contains(Argument.BY_GAME.toString())) {
			LOG.error("Cannot determine sorting order");
			return;
		}

		// Exit if "players" is entered plus other arguments
		if (arguments.contains(Argument.PLAYERS.toString()) && arguments.size() > 1) {
			LOG.error("Argument \"players\" can't be associated with other arguments");
			return;
		}

		// Iterate through all arguments and perform filters and sort
		Argument arg;
		String platform = "";
		for (int i = 0; i < arguments.size(); i++) {
			try {
				if (arguments.get(i).contains("=")) {
					// Split string by "=" to get specified platform
					platform = arguments.get(i).split("=")[1];
					arguments.set(i, arguments.get(i).split("=")[0]);

					// Throw an exception if argument is invalid.
					// invalid "platform" argument or cannot identify platform
					// value
					if (!arguments.get(i).equals(Argument.PLATFORM.toString()) || !Platform.contains(platform)) {
						throw new IllegalArgumentException();
					}
				}
				// get constant variable from string command line argument
				arg = Argument.get(arguments.get(i).toLowerCase());
				if (arg == null) {
					throw new NullPointerException();
				}
			} catch (IllegalArgumentException | NullPointerException e) {
				LOG.error("Invalid argument");
				return;
			}

			switch (arg) {
			case BY_GAME:
				if (arguments.contains(Argument.DESC.toString())) {
					Collections.sort(dataset, Collections.reverseOrder(new CompareByGameName()));
				} else {
					Collections.sort(dataset, new CompareByGameName());
				}
				break;
			case BY_COUNT:
				if (arguments.contains(Argument.DESC.toString())) {
					Collections.sort(dataset, Collections.reverseOrder(new CompareByCount()));
				} else {
					Collections.sort(dataset, new CompareByCount());
				}
				break;
			case PLATFORM:
				filterByPlatform(platform);
				break;
			case PLAYERS:
				printPlayers(datamap);
				return;
			default:
				break;
			}
		}

		// Print all data after filters and sort
		printData();
		if (arguments.contains(Argument.TOTAL.toString())) {
			printTotal();
		}
	}

	/**
	 * Print header and all data
	 * 
	 * @param displayData
	 *            All required data in printing
	 */
	private void printData() {
		// Print header
		System.out.printf("%s:%s %5s %17s %15s %n", "Win", "Loss", "Game Name", "Gamertag", "Platform");
		System.out.println("----------------------------------------------------");

		// Print sorted data
		for (DisplayData data : dataset) {
			System.out.printf("%d:%-6d %-18s %-15s %s %n", data.getWin(), data.getLoss(), data.getGame(),
					data.getGamertag(), data.getPlatform());
		}
		System.out.println("----------------------------------------------------");
	}

	/**
	 * Print total number of times each game was played.
	 */
	public void printTotal() {
		Map<String, Integer> counts = new HashMap<>();
		String key;
		for (DisplayData data : dataset) {
			key = data.getGame();
			if (counts.containsKey(key)) {
				counts.put(key, counts.get(key) + data.getWin() + data.getLoss());
			} else {
				counts.put(key, data.getWin() + data.getLoss());
			}
		}

		for (Map.Entry<String, Integer> entry : counts.entrySet()) {
			System.out.printf("%-15s %d %n", entry.getKey(), entry.getValue());
		}
		System.out.println("----------------------------------------------------");
	}

	/**
	 * Remove elements that does not belong to specified platform.
	 * 
	 * @param displayData
	 * @param platform
	 * @return filetered dataset by platform
	 */
	public void filterByPlatform(String platform) {
		Iterator<DisplayData> it = dataset.iterator();
		DisplayData data;
		while (it.hasNext()) {
			data = it.next();
			// Remove element if platform is not the same as specified
			if (!data.getPlatform().equalsIgnoreCase(platform)) {
				it.remove();
			}
		}
	}

	/**
	 * Print player details.
	 * 
	 * @param players
	 */
	public void printPlayers(DataMap datamap) {
		Map<Integer, Player> players = datamap.getPlayers();
		int playerID;
		String fullName;
		String email;
		long age;
		int totalGamesPlayed;
		int totalWins;

		System.out.printf("%s %10s %15s %25s %10s %10s %n", "Player ID", "Full name", "Email", "Age",
				"Total games played", "Total Wins");
		System.out.println(
				"--------------------------------------------------------------------------------------------");

		for (Map.Entry<Integer, Player> entry : players.entrySet()) {
			playerID = entry.getValue().getID();
			fullName = String.format("%s %2s", entry.getValue().getFirstName(), entry.getValue().getLastName());
			email = entry.getValue().getEmail();
			age = entry.getValue().getAge();
			totalGamesPlayed = entry.getValue().getTotalGamesPlayed();
			totalWins = entry.getValue().getTotalWins();

			System.out.printf("%-10d %-19s %-27s %-10d %-15d %d %n", playerID, fullName, email, age, totalGamesPlayed,
					totalWins);
		}
		System.out.println(
				"--------------------------------------------------------------------------------------------");
	}
}
