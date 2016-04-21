/**
 * Project: A00973641Gis
 * File: SetData.java
 * Date: Feb 19, 2016
 * Time: 8:14:53 PM
 */
package A00973641.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import A00973641.data.DataMap;
import A00973641.data.DisplayData;
import A00973641.data.Game;
import A00973641.data.Persona;
import A00973641.data.Player;
import A00973641.data.Score;

/**
 * @author Mara
 *
 */
public class SetData {

	private static int win;
	private static int loss;
	private static List<DisplayData> displayData;

	public static void set(DataMap datamap) {
		// get mapped data
		Map<String, Game> games = datamap.getGames();
		Map<Integer, Persona> personas = datamap.getPersonas();
		Map<Integer, Player> players = datamap.getPlayers();
		Map<String, Score> scores = datamap.getScores();

		List<Score> visited = new ArrayList<>(); // store visited Score objects
		displayData = new ArrayList<>();
		List<Score> scoreList = new ArrayList<Score>(scores.values());
		DisplayData data;
		Score score;
		Persona persona;
		Game game;
		Player player;

		// Variables to store values during iteration on data
		String gameID;
		int personaID;
		String gameName;
		String gamerTag;
		String platform;

		win = 0;
		loss = 0;

		// Check all Score objects in list to get information from other data
		// objects
		for (int i = 0; i < scoreList.size(); i++) {
			score = scoreList.get(i);
			// Skip to end of loop if score is already visited
			if (visited.contains(score))
				continue;

			persona = personas.get(score.getPersonaID());
			game = games.get(score.getGameID());
			player = players.get(persona.getPlayerID());

			gameID = game.getID();
			personaID = persona.getID();

			tallyScore(score, player);

			// Get next Score object to compare from previous Score object
			for (int j = i + 1; j < scoreList.size(); j++) {
				Score temp = scoreList.get(j);
				String tempGameID = games.get(temp.getGameID()).getID();
				int tempPersonaID = personas.get(temp.getPersonaID()).getID();
				// Skip to end of loop if temp is already visited
				if (visited.contains(temp))
					continue;

				// Check win or loss if persona and game are the same
				if (gameID.equals(tempGameID) && personaID == tempPersonaID) {
					tallyScore(temp, player);
					visited.add(temp);
				}
			}

			// Add win and/or loss to total games played by player
			player.setTotalGamesPlayed(win + loss);

			gameName = game.getName();
			gamerTag = persona.getGamerTag();
			platform = persona.getPlatform();

			// Create object DataSet to store all data
			data = new DisplayData();
			data.setGame(gameName);
			data.setGamertag(gamerTag);
			data.setLoss(loss);
			data.setWin(win);
			data.setPlatform(platform);
			displayData.add(data);

			// Reset values of win and loss
			win = 0;
			loss = 0;
		}
	}

	public static List<DisplayData> get() {
		return displayData;
	}

	/**
	 * Tally win/loss for the same game played by the same persona. Tally all
	 * wins for a game played by Player.
	 * 
	 * @param score
	 * @param player
	 */
	private static void tallyScore(Score score, Player player) {
		if (score.isWin()) {
			win++;
			player.setTotalWins(win); // Add win to player
		} else {
			loss++;
		}
	}

}
