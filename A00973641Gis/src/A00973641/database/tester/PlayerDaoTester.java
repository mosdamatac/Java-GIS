/**
 * Project: A00973641Gis
 * File: PlayerDaoTester.java
 * Date: Mar 21, 2016
 * Time: 2:24:51 PM
 */
package A00973641.database.tester;

import java.sql.SQLException;
import java.util.List;

import javax.swing.JOptionPane;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import A00973641.data.Player;
import A00973641.database.dao.PlayerDao;

/**
 * @author Mara
 *
 */
public class PlayerDaoTester {

	private static Logger LOG = LogManager.getLogger(PlayerDaoTester.class);

	public PlayerDaoTester() {

	}

	public void test() {
		PlayerDao tester = new PlayerDao();
		try {
			List<Player> players = tester.getPlayers();
			for (Player player : players) {
				LOG.info("Test successful. Loaded: " + player.toString());
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			LOG.error(e.getMessage());
		}
	}

}
