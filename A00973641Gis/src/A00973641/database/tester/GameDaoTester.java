/**
 * Project: A00973641Gis
 * File: GameDaoTester.java
 * Date: Mar 22, 2016
 * Time: 4:15:11 PM
 */
package A00973641.database.tester;

import java.sql.SQLException;
import java.util.List;

import javax.swing.JOptionPane;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import A00973641.data.Game;
import A00973641.database.dao.GameDao;

/**
 * @author Mara
 *
 */
public class GameDaoTester {

	private static Logger LOG = LogManager.getLogger(GameDaoTester.class);

	public GameDaoTester() {

	}

	public void test() {
		GameDao tester = new GameDao();
		try {
			List<Game> games = tester.getGames();
			for (Game game : games) {
				LOG.info("Test successful. Loaded: " + game.toString());
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			LOG.error(e.getMessage());
		}
	}
}
