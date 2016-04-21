/**
 * Project: A00973641Gis
 * File: PersonaDao.java
 * Date: Mar 22, 2016
 * Time: 3:29:55 PM
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

import A00973641.data.Persona;
import A00973641.database.DbConstants;
import A00973641.database.util.DBUtil;

/**
 * @author Mara
 *
 */
public class PersonaDao extends Dao {

	private static final String TABLE_NAME = DbConstants.PERSONA_TABLE_NAME;
	private static Logger LOG = LogManager.getLogger(PersonaDao.class);

	public PersonaDao() {
		super(TABLE_NAME);
	}

	@Override
	public void create() throws SQLException {
		LOG.debug("Creating Persona table.");
		PreparedStatement statement = null;
		try {
			Connection connection = database.getConnection();
			String sql = String.format(
					"CREATE TABLE %s(%s INTEGER, %s INTEGER, %s VARCHAR(15), %s VARCHAR(2), PRIMARY KEY(%s))", //
					TABLE_NAME, //
					Fields.ID, //
					Fields.PLAYERID, //
					Fields.GAMERTAG, //
					Fields.PLATFORM, //
					Fields.ID); // Primary key for table Persona
			statement = connection.prepareStatement(sql);
			DBUtil.executeUpdate(statement, sql);
		} finally {
			close(statement);
		}
	}

	/**
	 * Add persona into database.
	 * 
	 * @param persona
	 * @throws SQLException
	 */
	public void add(Persona persona) throws SQLException {
		LOG.debug("Adding persona: " + persona.toString());
		PreparedStatement statement = null;
		try {
			Connection connection = database.getConnection();
			String sql = String.format("INSERT INTO %s VALUES(?, ?, ?, ?)", TABLE_NAME);
			statement = connection.prepareStatement(sql);
			statement.setInt(Fields.ID.getColumn(), persona.getID());
			statement.setInt(Fields.PLAYERID.getColumn(), persona.getPlayerID());
			statement.setString(Fields.GAMERTAG.getColumn(), persona.getGamerTag());
			statement.setString(Fields.PLATFORM.getColumn(), persona.getPlatform());
			DBUtil.executeUpdate(statement, sql);
		} finally {
			close(statement);
		}

	}

	/**
	 * Update information of existing Persona.
	 * 
	 * @param persona
	 * @throws SQLException
	 */
	public void update(Persona persona) throws SQLException {
		LOG.debug("Updating persona: " + persona.toString());
		PreparedStatement statement = null;
		try {
			Connection connection = database.getConnection();
			String sql = String.format("UPDATE %s SET %s=? WHERE %s=?", //
					TABLE_NAME, //
					Fields.GAMERTAG, //
					Fields.ID);
			statement = connection.prepareStatement(sql);
			statement.setString(1, persona.getGamerTag());
			statement.setInt(2, persona.getID());
			DBUtil.executeUpdate(statement, sql);
			LOG.debug("Successfully updated persona: " + persona.toString());
		} finally {
			close(statement);
		}

	}

	/**
	 * Delete a Persona from the database.
	 * 
	 * @param id
	 * @throws SQLException
	 */
	public void delete(int id) throws SQLException {
		LOG.debug("Deleting persona with id:" + id);
		PreparedStatement statement = null;
		try {
			Connection connection = database.getConnection();
			String sql = String.format("DELETE FROM %s WHERE %s=?", //
					TABLE_NAME, //
					Fields.ID);
			statement = connection.prepareStatement(sql);
			statement.setInt(Fields.ID.getColumn(), id);
			DBUtil.executeQuery(statement, sql);
		} finally {
			close(statement);
		}
	}

	public List<Persona> getPersonas() throws SQLException {
		List<Persona> personas = new ArrayList<>();
		Statement statement = null;
		try {
			Connection connection = database.getConnection();
			statement = connection.createStatement();
			String sql = String.format("SELECT * FROM %s", TABLE_NAME);
			ResultSet resultset = DBUtil.executeQuery(statement, sql);

			// Store personas from database to list
			Persona persona;
			while (resultset.next()) {
				persona = new Persona();
				persona.setID(resultset.getInt(Fields.ID.getColumn()));
				persona.setPlayerID(resultset.getInt(Fields.PLAYERID.getColumn()));
				persona.setGamerTag(resultset.getString(Fields.GAMERTAG.getColumn()));
				persona.setPlatform(resultset.getString(Fields.PLATFORM.getColumn()));

				personas.add(persona);
			}
			resultset.close();
		} finally {
			close(statement);
		}
		LOG.debug(String.format("Loaded %d Personas from database", personas.size()));

		return personas;
	}

	public Persona getDetails(String gamertag) throws SQLException {
		Persona persona = new Persona();
		Statement statement = null;
		try {
			Connection connection = database.getConnection();
			statement = connection.createStatement();
			String sql = String.format("SELECT * FROM %s WHERE %s=\'%s\'", //
					TABLE_NAME, Fields.GAMERTAG, gamertag);
			ResultSet resultset = DBUtil.executeQuery(statement, sql);

			if (resultset.next()) {
				persona.setID(resultset.getInt(Fields.ID.getColumn()));
				persona.setPlayerID(resultset.getInt(Fields.PLAYERID.getColumn()));
				persona.setGamerTag(resultset.getString(Fields.GAMERTAG.getColumn()));
				persona.setPlatform(resultset.getString(Fields.PLATFORM.getColumn()));
			}
			resultset.close();
		} finally {
			close(statement);
		}

		return persona;
	}

	public List<String> getGamertags() throws SQLException {
		List<String> gamertags = new ArrayList<>();
		Statement statement = null;
		try {
			Connection connection = database.getConnection();
			statement = connection.createStatement();
			String sql = String.format("SELECT %s FROM %s", Fields.GAMERTAG, TABLE_NAME);
			ResultSet resultset = DBUtil.executeQuery(statement, sql);

			String gamertag;
			while (resultset.next()) {
				gamertag = resultset.getString(1);
				gamertags.add(gamertag);
			}

		} finally {
			close(statement);
		}
		LOG.debug(String.format("Successfully loaded %d gamertags", gamertags.size()));
		return gamertags;
	}

	/**
	 * Field constants for Persona table.
	 * 
	 * @author Mara
	 *
	 */
	public enum Fields {

		ID(1), PLAYERID(2), GAMERTAG(3), PLATFORM(4);

		private final int column;

		Fields(int column) {
			this.column = column;
		}

		public int getColumn() {
			return column;
		}
	}
}
