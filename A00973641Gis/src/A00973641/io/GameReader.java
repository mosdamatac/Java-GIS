/**
 * Project: A00973641Gis
 * File: GameReader.java
 * Date: Feb 12, 2016
 * Time: 2:51:38 AM
 */
package A00973641.io;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import A00973641.ApplicationException;
import A00973641.data.Game;
import A00973641.util.Validator;

/**
 * @author Mara
 *
 */
public class GameReader extends Reader<String, Game> {

	/**
	 * Constructor for GameReader
	 */
	public GameReader() {

	}

	/**
	 * 
	 * @param List
	 *            of string
	 * @return Map of information about Games
	 * 
	 */
	@Override
	public Map<String, Game> setInfo(List<String> list) throws ApplicationException {
		Map<String, Game> games = new HashMap<>();
		Game game;
		String[] info;

		Iterator<String> it = list.iterator();
		while (it.hasNext()) {
			game = new Game();
			info = splitString(it.next());
			if (!Validator.isElementsEqual(info.length, getTotalElements())) {
				throw new ApplicationException(info.length, getTotalElements());
			}

			game.setID(info[0]);
			game.setName(info[1]);
			game.setProducer(info[2]);

			// Add game in map with game ID as key
			games.put(game.getID(), game);
		}

		return games;
	}
}
