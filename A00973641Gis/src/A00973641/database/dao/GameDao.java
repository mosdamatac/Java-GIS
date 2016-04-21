/**
 * Project: A00973641Gis
 * File: GameDao.java
 * Date: Mar 22, 2016
 * Time: 4:02:25 PM
 */
package A00973641.database.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import A00973641.data.Game;
import A00973641.database.DbConstants;
import A00973641.database.util.DBUtil;

/**
 * @author Mara
 *
 */
public class GameDao extends Dao {

	private static final String TABLE_NAME = DbConstants.GAME_TABLE_NAME;
	private static Logger LOG = LogManager.getLogger(GameDao.class);

	public GameDao() {
		super(TABLE_NAME);
	}

	@Override
	public void create() throws SQLException {
		LOG.debug("Creating Game table.");
		PreparedStatement statement = null;
		try {
			Connection connection = database.getConnection();
			String sql = String.format(
					"CREATE TABLE %s(%s VARCHAR(4), %s VARCHAR(15), %s VARCHAR(20), PRIMARY KEY(%s))", //
					TABLE_NAME, //
					Fields.ID, //
					Fields.NAME, //
					Fields.PRODUCER, //
					Fields.ID); // Primary key for table Game
			statement = connection.prepareStatement(sql);
			DBUtil.executeUpdate(statement, sql);
		} finally {
			close(statement);
		}
	}

	/**
	 * Add game into database.
	 * 
	 * @param game
	 * @throws SQLException
	 */
	public void add(Game game) throws SQLException {
		LOG.debug("Adding game: " + game.toString());
		PreparedStatement statement = null;
		try {
			Connection connection = database.getConnection();
			String sql = String.format("INSERT INTO %s VALUES(?, ?, ?)", TABLE_NAME);
			statement = connection.prepareStatement(sql);
			statement.setString(Fields.ID.getColumn(), game.getID());
			statement.setString(Fields.NAME.getColumn(), game.getName());
			statement.setString(Fields.PRODUCER.getColumn(), game.getProducer());
			DBUtil.executeUpdate(statement, sql);
		} finally {
			close(statement);
		}

	}

	/**
	 * Update information of existing Game.
	 * 
	 * @param game
	 * @throws SQLException
	 */
	public void update(Game game) throws SQLException {
		LOG.debug("Updating game: " + game.toString());
		PreparedStatement statement = null;
		try {
			Connection connection = database.getConnection();
			String sql = String.format("UPDATE %s SET %s=\'?\', %s=\'?\', %s=\'?\', WHERE %s=%s", //
					TABLE_NAME, //
					Fields.ID, //
					Fields.NAME, //
					Fields.PRODUCER, //
					Fields.ID, //
					game.getID());
			statement = connection.prepareStatement(sql);
			statement.setString(Fields.ID.getColumn(), game.getID());
			statement.setString(Fields.NAME.getColumn(), game.getName());
			statement.setString(Fields.PRODUCER.getColumn(), game.getProducer());
		} finally {
			close(statement);
		}

	}

	/**
	 * Delete a Game from the database.
	 * 
	 * @param id
	 * @throws SQLException
	 */
	public void delete(String id) throws SQLException {
		LOG.debug("Deleting game with id:" + id);
		PreparedStatement statement = null;
		try {
			Connection connection = database.getConnection();
			String sql = String.format("DELETE FROM %s WHERE %s=?", //
					TABLE_NAME, //
					Fields.ID);
			statement = connection.prepareStatement(sql);
			statement.setString(Fields.ID.getColumn(), id);
			DBUtil.executeQuery(statement, sql);
		} finally {
			close(statement);
		}
	}

	public List<Game> getGames() throws SQLException {
		List<Game> games = new ArrayList<>();
		Statement statement = null;
		try {
			Connection connection = database.getConnection();
			statement = connection.createStatement();
			String sql = String.format("SELECT * FROM %s", TABLE_NAME);
			ResultSet resultset = DBUtil.executeQuery(statement, sql);

			// Store personas from database to list
			Game game;
			while (resultset.next()) {
				game = new Game();
				game.setID(resultset.getString(Fields.ID.getColumn()));
				game.setName(resultset.getString(Fields.NAME.getColumn()));
				game.setProducer(resultset.getString(Fields.PRODUCER.getColumn()));

				games.add(game);
			}
			resultset.close();
		} finally {
			close(statement);
		}
		LOG.debug(String.format("Loaded %d Personas from database", games.size()));

		return games;
	}

	/**
	 * Field constants for Game table.
	 * 
	 * @author Mara
	 *
	 */
	public enum Fields {

		ID(1), NAME(2), PRODUCER(3);

		private final int column;

		Fields(int column) {
			this.column = column;
		}

		public int getColumn() {
			return column;
		}
	}
}
