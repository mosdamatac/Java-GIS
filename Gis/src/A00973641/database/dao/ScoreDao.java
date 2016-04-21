/**
 * Project: A00973641Gis
 * File: ScoreDao.java
 * Date: Mar 22, 2016
 * Time: 4:19:40 PM
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

import A00973641.data.Score;
import A00973641.database.DbConstants;
import A00973641.database.util.DBUtil;

/**
 * @author Mara
 *
 */
public class ScoreDao extends Dao {

	private static final String TABLE_NAME = DbConstants.SCORE_TABLE_NAME;
	private static Logger LOG = LogManager.getLogger(ScoreDao.class);

	public ScoreDao() {
		super(TABLE_NAME);
	}

	@Override
	public void create() throws SQLException {
		LOG.debug("Creating Score table.");
		PreparedStatement statement = null;
		try {
			Connection connection = database.getConnection();
			String sql = String.format(
					"CREATE TABLE %s(%s INTEGER, %s INTEGER, %s VARCHAR(4), %s BIT, PRIMARY KEY(%s))", //
					TABLE_NAME, //
					Fields.ID, //
					Fields.PERSONAID, //
					Fields.GAMEID, //
					Fields.WIN, //
					Fields.ID); // Primary key for Score table
			statement = connection.prepareStatement(sql);
			DBUtil.executeUpdate(statement, sql);
		} finally {
			close(statement);
		}
	}

	/**
	 * Add score into database.
	 * 
	 * @param score
	 * @throws SQLException
	 */
	public void add(Score score) throws SQLException {
		LOG.debug("Adding score: " + score.toString());
		PreparedStatement statement = null;
		try {
			Connection connection = database.getConnection();
			String sql = String.format("INSERT INTO %s VALUES(?, ?, ?, ?)", TABLE_NAME);
			statement = connection.prepareStatement(sql);
			statement.setInt(Fields.ID.getColumn(), score.getID());
			statement.setInt(Fields.PERSONAID.getColumn(), score.getPersonaID());
			statement.setString(Fields.GAMEID.getColumn(), score.getGameID());
			statement.setBoolean(Fields.WIN.getColumn(), score.isWin());
			DBUtil.executeUpdate(statement, sql);
		} finally {
			close(statement);
		}

	}

	/**
	 * Update information of existing Score.
	 * 
	 * @param score
	 * @throws SQLException
	 */
	public void update(Score score) throws SQLException {
		LOG.debug("Updating score: " + score.toString());
		PreparedStatement statement = null;
		try {
			Connection connection = database.getConnection();
			String sql = String.format("UPDATE %s SET %s=?, %s=?, %s=? WHERE %s=?", //
					TABLE_NAME, //
					Fields.PERSONAID, //
					Fields.GAMEID, //
					Fields.WIN, //
					Fields.ID);
			statement = connection.prepareStatement(sql);
			statement.setInt(1, score.getPersonaID());
			statement.setString(2, score.getGameID());
			statement.setBoolean(3, score.isWin());
			statement.setInt(4, score.getID());
		} finally {
			close(statement);
		}

	}

	/**
	 * Delete a Score from the database.
	 * 
	 * @param personaID,
	 *            gameID
	 * @throws SQLException
	 */
	public void delete(int personaID, String gameID) throws SQLException {
		LOG.debug("Deleting score with id:" + "(" + personaID + "," + gameID + ")");
		PreparedStatement statement = null;
		try {
			Connection connection = database.getConnection();
			String sql = String.format("DELETE FROM %s WHERE %s=? AND %s=?", //
					TABLE_NAME, //
					Fields.PERSONAID, //
					Fields.GAMEID);
			statement = connection.prepareStatement(sql);
			statement.setInt(Fields.PERSONAID.getColumn(), personaID);
			statement.setString(Fields.GAMEID.getColumn(), gameID);
			DBUtil.executeQuery(statement, sql);
		} finally {
			close(statement);
		}
	}

	public List<Score> getScores() throws SQLException {
		List<Score> scores = new ArrayList<>();
		Statement statement = null;
		try {
			Connection connection = database.getConnection();
			statement = connection.createStatement();
			String sql = String.format("SELECT * FROM %s", TABLE_NAME);
			ResultSet resultset = DBUtil.executeQuery(statement, sql);

			// Store scores from database to list
			Score score;
			while (resultset.next()) {
				score = new Score();
				score.setID(resultset.getInt(Fields.ID.getColumn()));
				score.setPersonaID(resultset.getInt(Fields.PERSONAID.getColumn()));
				score.setGameID(resultset.getString(Fields.GAMEID.getColumn()));
				if (resultset.getBoolean(Fields.WIN.getColumn())) {
					score.setWin("WIN");
				} else {
					score.setWin("LOSE");
				}

				scores.add(score);
			}
			resultset.close();
		} finally {
			close(statement);
		}
		LOG.debug(String.format("Loaded %d Scores from database", scores.size()));

		return scores;
	}

	/**
	 * Field constants for Score table.
	 * 
	 * @author Mara
	 *
	 */
	public enum Fields {

		ID(1), PERSONAID(2), GAMEID(3), WIN(4);

		private final int column;

		Fields(int column) {
			this.column = column;
		}

		public int getColumn() {
			return column;
		}
	}

}
