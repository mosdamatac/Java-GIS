/**
 * Project: A00973641Gis
 * File: DBUtil.java
 * Date: Mar 20, 2016
 * Time: 5:40:58 PM
 */
package A00973641.database.util;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author Mara
 *
 */
public class DBUtil {

	private static Logger LOG = LogManager.getLogger(DBUtil.class);

	private DBUtil() {

	}

	/**
	 * Execute DML statements.
	 * 
	 * @param statement
	 * @param sql
	 * @return
	 * @throws SQLException
	 */
	public static int executeUpdate(PreparedStatement statement, String sql) throws SQLException {
		int count = statement.executeUpdate();
		LOG.debug("Executed: " + sql);
		LOG.debug(LocalDateTime.now());

		return count;
	}

	/**
	 * Execute query statements.
	 * 
	 * @param statement
	 * @param sql
	 * @return
	 * @throws SQLException
	 */
	public static ResultSet executeQuery(Statement statement, String sql) throws SQLException {
		ResultSet resultset = statement.executeQuery(sql);
		LOG.debug("Query: " + sql);
		LOG.debug(LocalDateTime.now());

		return resultset;
	}

	/**
	 * Determine if table already exists in the database.
	 * 
	 * @param connection
	 * @param tableName
	 * @return
	 * @throws SQLException
	 */
	public static boolean tableExists(Connection connection, String tableName) throws SQLException {
		DatabaseMetaData databaseMetaData = connection.getMetaData();
		ResultSet resultset = null;
		String rsTableName = null;

		try {
			resultset = databaseMetaData.getTables(connection.getCatalog(), "%", "%", null);
			while (resultset.next()) {
				rsTableName = resultset.getString("TABLE_NAME");
				if (rsTableName.equalsIgnoreCase(tableName)) {
					return true;
				}
			}
		} finally {
			resultset.close();
		}

		return false;
	}
}
