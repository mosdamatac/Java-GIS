/**
 * Project: A00973641Gis
 * File: Reader.java
 * Date: Feb 12, 2016
 * Time: 2:29:06 PM
 */
package A00973641.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import A00973641.ApplicationException;

/**
 * @author Mara
 *
 */
public abstract class Reader<E, T> {

	private static final Logger LOG = LogManager.getLogger(Reader.class);
	private String format;

	/**
	 * Get data file and check if it exists.
	 * 
	 * @param fileName
	 * @return File
	 */
	public File getFile(String fileName) {
		File file = new File(fileName);
		if (!file.exists()) {
			LOG.info("File " + fileName + " does not exist.");
			LOG.info("Exiting program..");
			System.exit(0);
		}

		return file;
	}

	/**
	 * Set string format of file
	 * 
	 * @param format
	 */
	private void setFormat(String format) {
		this.format = format;
	}

	/**
	 * Split string format to get number of elements
	 * 
	 * @return int Total number of elements
	 */
	public int getTotalElements() {
		String[] elements = format.split("\\|");
		return elements.length;
	}

	/**
	 * Read all lines of file and store in a List.
	 * 
	 * @param file
	 * @return List
	 */
	public List<String> read(File file) throws IOException {
		List<String> list = new ArrayList<>();
		BufferedReader reader = new BufferedReader(new FileReader(file));

		String line;
		// Set format of file on first readLine
		if ((line = reader.readLine()) != null) {
			setFormat(line);
		}

		// Populate list with information
		while ((line = reader.readLine()) != null) {
			list.add(line);
		}

		// Close BufferedReader
		reader.close();

		return list;
	}

	/**
	 * Split the string to get information
	 * 
	 * @param string
	 * @return array of information
	 */
	protected String[] splitString(String string) {
		String[] info = string.split("\\|");

		return info;
	}

	/**
	 * Set the information of data in list of strings
	 * 
	 * @param list
	 * @return Map
	 */
	abstract public Map<E, T> setInfo(List<String> list) throws ApplicationException;
}
