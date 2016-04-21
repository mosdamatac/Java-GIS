/**
 * Project: A00973641Gis
 * File: Gis.java
 * Date: Feb 12, 2016
 * Time: 2:25:54 AM
 */
package A00973641;

import java.awt.EventQueue;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Properties;

import javax.swing.JOptionPane;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.config.ConfigurationSource;
import org.apache.logging.log4j.core.config.Configurator;

import A00973641.data.DataMap;
import A00973641.database.Database;
import A00973641.database.DbConstants;
import A00973641.database.util.DaoManipulator;
import A00973641.io.Report;
import A00973641.ui.MainFrame;

/**
 * @author Mara Damatac
 *
 */
public class Gis {

	private static final String LOG4J_CONFIG_FILENAME = "log_config.xml";
	private static Logger LOG;
	private static final String GAME_FILE = "games.dat";
	private static final String PERSONA_FILE = "personas.dat";
	private static final String PLAYER_FILE = "players.dat";
	private static final String SCORE_FILE = "scores.dat";
	private DataMap datamap;
	private static Database database;
	private static Connection connection;

	/**
	 * Start to run the program.
	 * 
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		// Get start time
		LocalDateTime start = LocalDateTime.now();
		configureLogging();
		LOG = LogManager.getLogger(Gis.class);
		LOG.info("Starting application: " + start);

		// Get properties file
		File file = new File(DbConstants.DB_PROPERTIES_FILENAME);
		if (!file.exists()) {
			LOG.error(String.format("Program cannot start because %s cannot be found.",
					DbConstants.DB_PROPERTIES_FILENAME));
			System.exit(-1);
		}

		try {
			new Gis(file).run();
		} finally {
			if (connection != null) {
				connection.close();
			}
		}

		Runtime.getRuntime().addShutdownHook(new Thread() {
			@Override
			public void run() {
				// Close database
				database.shutdown();

				// Log end time and duration
				LocalDateTime end = LocalDateTime.now();
				LOG.info("Application end: " + end);
				LOG.info("Duration: " + ChronoUnit.MILLIS.between(start, end) + " ms ");
			}
		});
	}

	/**
	 * Run functionalities to start program.
	 * 
	 * @param args
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public void run() throws ClassNotFoundException, SQLException {
		// startReader(); // Set table data by using .dat files

		// Set data for later mapping. Store in Maps
		DaoManipulator daoManipulator = new DaoManipulator();
		initializeDataMap(); // Using database to initialize data
		// daoManipulator.initDataMap(datamap); // use .dat files to initialize
		// data
		// initializeTables(daoManipulator); // Using .dat files

		// Test all dao
		daoManipulator.runTests();

		// Show GUI
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					MainFrame frame = new MainFrame(datamap);
					frame.setVisible(true);
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		// Print report on console and text file
		// String argument = ""; if (args.length != 0) { argument = args[0]; }
		// startReport(argument);
	}

	/**
	 * Initialize tables from .dat files
	 * 
	 * @param daoManipulator
	 */
	@SuppressWarnings("unused")
	private void initializeTables(DaoManipulator daoManipulator) {
		daoManipulator.insertPlayers(); // Populate Player table
		daoManipulator.insertPersonas(); // Populate Persona table
		daoManipulator.insertGames(); // Populate Game table
		daoManipulator.insertScores(); // Populate Score table

	}

	/**
	 * Set DataMap from database
	 */
	private void initializeDataMap() {
		datamap = new DataMap();
		datamap.setPersonas();
		datamap.setPlayers();
		datamap.setGames();
		datamap.setScores();
	}

	/**
	 * Set DataMap from .dat files
	 */
	public void startReader() {
		datamap = new DataMap();
		datamap.setGames(GAME_FILE);
		datamap.setPersonas(PERSONA_FILE);
		datamap.setPlayers(PLAYER_FILE);
		datamap.setScores(SCORE_FILE);
	}

	public void startReport(String argument) {
		new Report(argument, datamap);
	}

	/**
	 * Configure logging.
	 */
	public static void configureLogging() {
		ConfigurationSource source;
		try {
			source = new ConfigurationSource(new FileInputStream(LOG4J_CONFIG_FILENAME));
			Configurator.initialize(null, source);
		} catch (IOException e) {
			System.out.println(
					String.format("Can't find the log4j logging configuration file %s.", LOG4J_CONFIG_FILENAME));
		}
	}

	/**
	 * Private constructor for Gis class.
	 * 
	 * @param file
	 * @throws IOException
	 */
	private Gis(File file) throws IOException {
		Properties dbProperties = new Properties();
		dbProperties.load(new FileInputStream(file));

		database = Database.getInstance();
		database.init(dbProperties);
	}
}
