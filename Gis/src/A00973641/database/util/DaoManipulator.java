/**
 * Project: A00973641Gis
 * File: DaoManipulator.java
 * Date: Mar 22, 2016
 * Time: 6:13:59 PM
 */
package A00973641.database.util;

import java.sql.SQLException;
import java.util.Map;

import javax.swing.JOptionPane;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import A00973641.data.DataMap;
import A00973641.data.Game;
import A00973641.data.Persona;
import A00973641.data.Player;
import A00973641.data.Score;
import A00973641.database.Database;
import A00973641.database.dao.GameDao;
import A00973641.database.dao.PersonaDao;
import A00973641.database.dao.PlayerDao;
import A00973641.database.dao.ScoreDao;
import A00973641.database.tester.GameDaoTester;
import A00973641.database.tester.PersonaDaoTester;
import A00973641.database.tester.PlayerDaoTester;
import A00973641.database.tester.ScoreDaoTester;

/**
 * @author Mara
 *
 */
public class DaoManipulator {

	private static final Logger LOG = LogManager.getLogger(DaoManipulator.class);
	private DataMap datamap;
	private static PlayerDao playerDao;
	private static PersonaDao personaDao;
	private static GameDao gameDao;
	private static ScoreDao scoreDao;

	public DaoManipulator() throws SQLException {
		playerDao = new PlayerDao();
		personaDao = new PersonaDao();
		gameDao = new GameDao();
		scoreDao = new ScoreDao();
		Database.getInstance().getConnection();
	}

	public void initDataMap(DataMap datamap) {
		this.datamap = datamap;
	}

	public void insertPlayers() {
		Map<Integer, Player> players = datamap.getPlayers();
		try {
			playerDao.drop();
			playerDao.create();
			for (Map.Entry<Integer, Player> entry : players.entrySet()) {
				playerDao.add(entry.getValue());
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			LOG.error(e.getMessage());
		}
	}

	public void insertPersonas() {
		Map<Integer, Persona> personas = datamap.getPersonas();
		try {
			personaDao.drop();
			personaDao.create();
			for (Map.Entry<Integer, Persona> entry : personas.entrySet()) {
				personaDao.add(entry.getValue());
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			LOG.error(e.getMessage());
		}
	}

	public void insertGames() {
		Map<String, Game> games = datamap.getGames();
		try {
			gameDao.drop();
			gameDao.create();
			for (Map.Entry<String, Game> entry : games.entrySet()) {
				gameDao.add(entry.getValue());
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			LOG.error(e.getMessage());
		}
	}

	public void insertScores() {
		Map<String, Score> scores = datamap.getScores();
		try {
			scoreDao.drop();
			scoreDao.create();
			for (Map.Entry<String, Score> entry : scores.entrySet()) {
				scoreDao.add(entry.getValue());
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			LOG.error(e.getMessage());
		}
	}

	public static PlayerDao getPlayerDao() {
		return playerDao;
	}

	public static PersonaDao getPersonaDao() {
		return personaDao;
	}

	public static GameDao getGameDao() {
		return gameDao;
	}

	public static ScoreDao getScoreDao() {
		return scoreDao;
	}

	public void runTests() {
		// PlayerDao
		PlayerDaoTester playerTester = new PlayerDaoTester();
		playerTester.test();
		// PersonaDao
		PersonaDaoTester personaTester = new PersonaDaoTester();
		personaTester.test();
		// GameDao
		GameDaoTester gameTester = new GameDaoTester();
		gameTester.test();
		// ScoreDao
		ScoreDaoTester scoreTester = new ScoreDaoTester();
		scoreTester.test();
	}
}
