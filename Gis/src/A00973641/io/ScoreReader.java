/**
 * Project: A00973641Gis
 * File: ScoreReader.java
 * Date: Feb 12, 2016
 * Time: 2:56:00 AM
 */
package A00973641.io;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import A00973641.data.Score;

/**
 * @author Mara
 *
 */
public class ScoreReader extends Reader<String, Score> {

	/**
	 * Constructor for ScoreReader
	 */
	public ScoreReader() {

	}

	/**
	 * 
	 * @param List
	 *            of string
	 * @return Map of information about Scores
	 * 
	 */
	@Override
	public Map<String, Score> setInfo(List<String> list) {
		Map<String, Score> scores = new HashMap<>();
		Score score;
		String[] info;

		for (int i = 0; i < list.size(); i++) {
			score = new Score();
			info = splitString(list.get(i));

			score.setID(i);
			score.setPersonaID(Integer.parseInt(info[0]));
			score.setGameID(info[1]);
			score.setWin(info[2]);

			scores.put(Integer.toString(score.getID()), score);
		}

		return scores;
	}
}
