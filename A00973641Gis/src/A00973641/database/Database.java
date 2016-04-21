/**
 * Project: A00973641Gis
 * File: Database.java
 * Date: Mar 20, 2016
 * Time: 4:25:20 PM
 */
package A00973641.database;

import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import javax.swing.JOptionPane;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import A00973641.Gis;

/**
 * @author Mara
 *
 */
public class Database {

	private static Database dbInstance = new Database();
	private static Logger LOG;
	private static Connection connection;
	private Properties properties;

	/**
	 * Private constructor for Database.
	 */
	private Database() {
		Gis.configureLogging();
		LOG = LogManager.getLogger(Database.class);
	}

	public void init(Properties properties) throws FileNotFoundException {
		this.properties = properties;
	}

	public static Database getInstance() {
		return dbInstance;
	}

	public Connection getConnection() throws SQLException {
		if (connection != null) {
			return connection;
		}

		try {
			connect();
		} catch (ClassNotFoundException e) {
			throw new SQLException(e);
		}

		return connection;
	}

	private void connect() throws ClassNotFoundException, SQLException {
		Class.forName(properties.getProperty(DbConstants.DB_DRIVER_KEY));
		LOG.info("Driver loaded.");
		connection = DriverManager.getConnection(properties.getProperty(DbConstants.DB_URL_KEY),
				properties.getProperty(DbConstants.DB_USER_KEY), properties.getProperty(DbConstants.DB_PASSWORD_KEY));
		LOG.info("Database connected.");
	}

	public void shutdown() {
		if (connection != null) {
			try {
				connection.close();
				connection = null;
				LOG.info("Database disconnected.");
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				LOG.error(e.getMessage());
			}
		}
	}
}
