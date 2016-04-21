/**
 * Project: A00973641Gis
 * File: DataSetter.java
 * Date: Feb 12, 2016
 * Time: 7:03:04 PM
 */
package A00973641.data;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JOptionPane;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import A00973641.ApplicationException;
import A00973641.database.dao.GameDao;
import A00973641.database.dao.PersonaDao;
import A00973641.database.dao.PlayerDao;
import A00973641.database.dao.ScoreDao;
import A00973641.database.util.DaoManipulator;
import A00973641.io.GameReader;
import A00973641.io.PersonaReader;
import A00973641.io.PlayerReader;
import A00973641.io.Reader;
import A00973641.io.ScoreReader;

/**
 * @author Mara
 *
 */
public class DataMap {

	private static final Logger LOG = LogManager.getLogger(DataMap.class);
	private Map<String, Game> games;
	private Map<Integer, Persona> personas;
	private Map<Integer, Player> players;
	private Map<String, Score> scores;

	/**
	 * Constructor for DataMap
	 */
	public DataMap() {

	}

	/**
	 * @return the games
	 */
	public Map<String, Game> getGames() {
		return games;
	}

	/**
	 * @param games
	 *            the games to set
	 */
	public void setGames(String file) {
		LOG.debug("Entering setGames(file = {})", file);
		try {
			Reader<String, Game> gameReader = new GameReader();
			List<String> games = gameReader.read(gameReader.getFile(file));
			this.games = gameReader.setInfo(games);
		} catch (IOException e) {
			LOG.error("Problem encountered while reading" + file);
			LOG.error("Gis application end.");
			System.exit(-1);
		} catch (ApplicationException e) {
			LOG.error(e.getMessage());
			LOG.error("Gis application end.");
			System.exit(-1);
		}
		LOG.debug("Leaving setGames");
	}

	/**
	 * Set DataMap game values from database.
	 */
	public void setGames() {
		GameDao gameDao = DaoManipulator.getGameDao();
		try {
			List<Game> games = gameDao.getGames();
			this.games = new HashMap<>();
			for (Game game : games) {
				this.games.put(game.getID(), game);
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			LOG.error(e.getMessage());
		}
	}

	/**
	 * @return the personas
	 */
	public Map<Integer, Persona> getPersonas() {
		return personas;
	}

	/**
	 * @param personas
	 *            the personas to set
	 */
	public void setPersonas(String file) {
		LOG.debug("Entering setPersonas(file = {})", file);
		try {
			Reader<Integer, Persona> personaReader = new PersonaReader();
			List<String> personas = personaReader.read(personaReader.getFile(file));
			this.personas = personaReader.setInfo(personas);
		} catch (IOException e) {
			LOG.error("Problem encountered while reading" + file);
			LOG.error("Gis application end.");
			System.exit(-1);
		} catch (ApplicationException e) {
			LOG.error(e.getMessage());
			LOG.error("Gis application end.");
			System.exit(-1);
		}
		LOG.debug("Leaving setPersonas");
	}

	/**
	 * Set DataMap persona values from database.
	 */
	public void setPersonas() {
		PersonaDao personaDao = DaoManipulator.getPersonaDao();
		try {
			List<Persona> personas = personaDao.getPersonas();
			this.personas = new HashMap<>();
			for (Persona persona : personas) {
				this.personas.put(persona.getID(), persona);
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			LOG.error(e.getMessage());
		}
	}

	/**
	 * @return the players
	 */
	public Map<Integer, Player> getPlayers() {
		return players;
	}

	/**
	 * @param players
	 *            the players to set
	 */
	public void setPlayers(String file) {
		LOG.debug("Entering setPlayers(file = {})", file);
		try {
			Reader<Integer, Player> playerReader = new PlayerReader();
			List<String> players = playerReader.read(playerReader.getFile(file));
			this.players = playerReader.setInfo(players);
		} catch (IOException e) {
			LOG.error("Problem encountered while reading" + file);
			LOG.error("Gis application end.");
			System.exit(-1);
		} catch (ApplicationException e) {
			LOG.error(e.getMessage());
			LOG.error("Gis application end.");
			System.exit(-1);
		}
		LOG.debug("Leaving setPlayers");
	}

	/**
	 * Set DataMap player values from database.
	 */
	public void setPlayers() {
		PlayerDao playerDao = DaoManipulator.getPlayerDao();
		try {
			List<Player> players = playerDao.getPlayers();
			this.players = new HashMap<>();
			for (Player player : players) {
				this.players.put(player.getID(), player);
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			LOG.error(e.getMessage());
		}
	}

	/**
	 * @return the scores
	 */
	public Map<String, Score> getScores() {
		return scores;
	}

	/**
	 * @param scores
	 *            the scores to set
	 */
	public void setScores(String file) {
		LOG.debug("Entering setScores(file = {})", file);
		try {
			Reader<String, Score> scoreReader = new ScoreReader();
			List<String> scores = scoreReader.read(scoreReader.getFile(file));
			this.scores = scoreReader.setInfo(scores);
		} catch (IOException e) {
			LOG.error("Problem encountered while reading" + file);
			LOG.error("Gis application end.");
			System.exit(-1);
		} catch (ApplicationException e) {
			LOG.error(e.getMessage());
			LOG.error("Gis application end.");
			System.exit(-1);
		}
		LOG.debug("Leaving setScores");
	}

	/**
	 * Set DataMap score values from database.
	 */
	public void setScores() {
		ScoreDao scoreDao = DaoManipulator.getScoreDao();
		try {
			List<Score> scores = scoreDao.getScores();
			this.scores = new HashMap<>();
			for (Score score : scores) {
				this.scores.put(Integer.toString(score.getID()), score);
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			LOG.error(e.getMessage());
		}
	}
}
