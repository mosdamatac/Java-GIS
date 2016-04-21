/**
 * Project: A00973641Gis
 * File: Dao.java
 * Date: Mar 20, 2016
 * Time: 4:23:45 PM
 */
package A00973641.database.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import A00973641.database.Database;
import A00973641.database.util.DBUtil;

/**
 * @author Mara
 *
 */
public abstract class Dao {

	private static final Logger LOG = LogManager.getLogger(Dao.class);
	protected final Database database;
	protected final String tableName;

	protected Dao(String tableName) {
		database = Database.getInstance();
		this.tableName = tableName;
	}

	public abstract void create() throws SQLException;

	protected void add(String updateStatement) throws SQLException {

	}

	/**
	 * Delete table if it already exists.
	 * 
	 * @throws SQLException
	 */
	public void drop() throws SQLException {
		LOG.debug("Dropping table.");
		PreparedStatement statement = null;
		try {
			Connection connection = database.getConnection();
			if (DBUtil.tableExists(connection, tableName)) {
				String sql = "drop table " + tableName;
				statement = connection.prepareStatement(sql);
				DBUtil.executeUpdate(statement, sql);
			}
		} finally {
			close(statement);
		}
	}

	protected void close(Statement statement) {
		try {
			if (statement != null) {
				statement.close();
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			LOG.error(e.getMessage());
		}
	}
}
