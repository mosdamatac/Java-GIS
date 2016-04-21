/**
 * Project: A00973641Gis
 * File: PersonaDaoTester.java
 * Date: Mar 22, 2016
 * Time: 3:52:06 PM
 */
package A00973641.database.tester;

import java.sql.SQLException;
import java.util.List;

import javax.swing.JOptionPane;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import A00973641.data.Persona;
import A00973641.database.dao.PersonaDao;

/**
 * @author Mara
 *
 */
public class PersonaDaoTester {

	private static Logger LOG = LogManager.getLogger(PersonaDaoTester.class);

	public PersonaDaoTester() {

	}

	public void test() {
		PersonaDao tester = new PersonaDao();
		try {
			List<Persona> personas = tester.getPersonas();
			for (Persona persona : personas) {
				LOG.info("Test successful. Loaded: " + persona.toString());
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			LOG.error(e.getMessage());
		}
	}

}
