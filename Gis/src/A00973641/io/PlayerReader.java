/**
 * Project: A00973641Gis
 * File: PlayerReader.java
 * Date: Feb 12, 2016
 * Time: 2:55:50 AM
 */
package A00973641.io;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import A00973641.ApplicationException;
import A00973641.data.Player;
import A00973641.util.Validator;

/**
 * @author Mara
 *
 */
public class PlayerReader extends Reader<Integer, Player> {

	/**
	 * Constructor for PlayerReader
	 */
	public PlayerReader() {

	}

	/**
	 * 
	 * @param List
	 *            of string
	 * @return Map of information about Players
	 * 
	 */
	@Override
	public Map<Integer, Player> setInfo(List<String> list) throws ApplicationException {
		Map<Integer, Player> players = new HashMap<>();
		Player player;
		String[] info;

		Iterator<String> it = list.iterator();
		while (it.hasNext()) {
			player = new Player();
			info = splitString(it.next());

			player.setID(Integer.parseInt(info[0]));
			player.setFirstName(info[1]);
			player.setLastName(info[2]);
			if (!Validator.isValidEmail(info[3])) {
				throw new ApplicationException(info[3]);
			}

			player.setEmail(info[3]);
			player.setBirthdate(info[4]);
			player.setAge(); // setAge should be called after birthdate is set

			// Add player in map with player ID as key
			players.put(player.getID(), player);
		}

		return players;
	}
}
