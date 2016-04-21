/**
 * Project: A00973641Gis
 * File: PlayerDao.java
 * Date: Mar 20, 2016
 * Time: 7:23:09 PM
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

import A00973641.data.Player;
import A00973641.database.DbConstants;
import A00973641.database.util.DBUtil;

/**
 * @author Mara
 *
 */
public class PlayerDao extends Dao {

	private static final String TABLE_NAME = DbConstants.PLAYER_TABLE_NAME;
	private static Logger LOG = LogManager.getLogger(PlayerDao.class);

	public PlayerDao() {
		super(TABLE_NAME);
	}

	@Override
	public void create() throws SQLException {
		LOG.debug("Creating Player table.");
		PreparedStatement statement = null;
		try {
			Connection connection = database.getConnection();
			String sql = String.format(
					"CREATE TABLE %s(%s INTEGER, %s VARCHAR(10), %s VARCHAR(10), %s VARCHAR(25), %s VARCHAR(15), %s INTEGER, %s INTEGER, %s INTEGER, PRIMARY KEY(%s))", //
					TABLE_NAME, //
					Fields.ID, //
					Fields.FIRSTNAME, //
					Fields.LASTNAME, //
					Fields.EMAIL, //
					Fields.BIRTHDATE, //
					Fields.AGE, //
					Fields.TOTALGAMESPLAYED, //
					Fields.TOTALWINS, //
					Fields.ID); // Primary key for table Player
			statement = connection.prepareStatement(sql);
			DBUtil.executeUpdate(statement, sql);
		} finally {
			close(statement);
		}
	}

	/**
	 * Add player into database.
	 * 
	 * @param player
	 * @throws SQLException
	 */
	public void add(Player player) throws SQLException {
		LOG.debug("Adding player: " + player.toString());
		PreparedStatement statement = null;
		try {
			Connection connection = database.getConnection();
			String sql = String.format("INSERT INTO %s VALUES(?, ?, ?, ?, ?, ?, ?, ?)", TABLE_NAME);
			statement = connection.prepareStatement(sql);
			statement.setInt(Fields.ID.getColumn(), player.getID());
			statement.setString(Fields.FIRSTNAME.getColumn(), player.getFirstName());
			statement.setString(Fields.LASTNAME.getColumn(), player.getLastName());
			statement.setString(Fields.EMAIL.getColumn(), player.getEmail());
			statement.setString(Fields.BIRTHDATE.getColumn(), player.getBirthdate());
			statement.setFloat(Fields.AGE.getColumn(), player.getAge());
			statement.setInt(Fields.TOTALGAMESPLAYED.getColumn(), player.getTotalGamesPlayed());
			statement.setInt(Fields.TOTALWINS.getColumn(), player.getTotalWins());
			DBUtil.executeUpdate(statement, sql);
		} finally {
			close(statement);
		}

	}

	/**
	 * Update information of existing Player.
	 * 
	 * @param player
	 * @throws SQLException
	 */
	public void update(Player player) throws SQLException {
		LOG.debug("Updating player: " + player.toString());
		PreparedStatement statement = null;
		try {
			Connection connection = database.getConnection();
			String sql = String.format("UPDATE %s SET %s=?, %s=?, %s=? WHERE %s=?", //
					TABLE_NAME, //
					Fields.FIRSTNAME, //
					Fields.LASTNAME, //
					Fields.EMAIL, //
					Fields.ID);
			statement = connection.prepareStatement(sql);
			statement.setString(1, player.getFirstName());
			statement.setString(2, player.getLastName());
			statement.setString(3, player.getEmail());
			statement.setInt(4, player.getID());
			DBUtil.executeUpdate(statement, sql);
			LOG.debug("Successfully update player: " + player.toString());
		} finally {
			close(statement);
		}

	}

	/**
	 * Delete a Player from the database.
	 * 
	 * @param playerID
	 * @throws SQLException
	 */
	public void delete(int playerID) throws SQLException {
		LOG.debug("Deleting player with player id:" + playerID);
		PreparedStatement statement = null;
		try {
			Connection connection = database.getConnection();
			String sql = String.format("DELETE FROM %s WHERE %s=?", //
					TABLE_NAME, //
					Fields.ID);
			statement = connection.prepareStatement(sql);
			statement.setInt(Fields.ID.getColumn(), playerID);
			DBUtil.executeQuery(statement, sql);
		} finally {
			close(statement);
		}
	}

	public List<Player> getPlayers() throws SQLException {
		List<Player> players = new ArrayList<>();
		Statement statement = null;
		try {
			Connection connection = database.getConnection();
			statement = connection.createStatement();
			String sql = String.format("SELECT * FROM %s", TABLE_NAME);
			ResultSet resultset = DBUtil.executeQuery(statement, sql);

			// Store players from database to list
			Player player;
			while (resultset.next()) {
				player = new Player();
				player.setID(resultset.getInt(Fields.ID.getColumn()));
				player.setFirstName(resultset.getString(Fields.FIRSTNAME.getColumn()));
				player.setLastName(resultset.getString(Fields.LASTNAME.getColumn()));
				player.setEmail(resultset.getString(Fields.EMAIL.getColumn()));
				player.setBirthdate(resultset.getString(Fields.BIRTHDATE.getColumn()));
				player.setAge(); // Age is based on birthdate
				player.setTotalGamesPlayed(resultset.getInt(Fields.TOTALGAMESPLAYED.getColumn()));
				player.setTotalWins(resultset.getInt(Fields.TOTALWINS.getColumn()));

				players.add(player);
			}
			resultset.close();
		} finally {
			close(statement);
		}
		LOG.debug(String.format("Loaded %d Players from database", players.size()));

		return players;
	}

	public Player getDetails(int id) throws SQLException {
		Player player = new Player();
		Statement statement = null;
		try {
			Connection connection = database.getConnection();
			statement = connection.createStatement();
			String sql = String.format("SELECT * FROM %s WHERE %s=\'%d\'", //
					TABLE_NAME, Fields.ID, id);
			ResultSet resultset = DBUtil.executeQuery(statement, sql);

			if (resultset.next()) {
				player.setID(resultset.getInt(Fields.ID.getColumn()));
				player.setFirstName(resultset.getString(Fields.FIRSTNAME.getColumn()));
				player.setLastName(resultset.getString(Fields.LASTNAME.getColumn()));
				player.setEmail(resultset.getString(Fields.EMAIL.getColumn()));
				player.setBirthdate(resultset.getString(Fields.BIRTHDATE.getColumn()));
				player.setAge(); // Age is based on birthdate
				player.setTotalGamesPlayed(resultset.getInt(Fields.TOTALGAMESPLAYED.getColumn()));
				player.setTotalWins(resultset.getInt(Fields.TOTALWINS.getColumn()));
			}
			resultset.close();
		} finally {
			close(statement);
		}

		return player;
	}

	/**
	 * Field constants for Player table.
	 * 
	 * @author Mara
	 *
	 */
	public enum Fields {

		ID(1), FIRSTNAME(2), LASTNAME(3), EMAIL(4), BIRTHDATE(5), AGE(6), TOTALGAMESPLAYED(7), TOTALWINS(8);

		private final int column;

		Fields(int column) {
			this.column = column;
		}

		public int getColumn() {
			return column;
		}
	}
}