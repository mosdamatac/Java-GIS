/**
 * Project: A00973641Gis
 * File: PersonaReader.java
 * Date: Feb 12, 2016
 * Time: 2:52:51 AM
 */
package A00973641.io;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import A00973641.data.Persona;

/**
 * @author Mara
 *
 */
public class PersonaReader extends Reader<Integer, Persona> {

	/**
	 * Constructor for PersonaReader
	 */
	public PersonaReader() {

	}

	/**
	 * 
	 * @param List
	 *            of string
	 * @return Map of information about Personas
	 * 
	 */
	@Override
	public Map<Integer, Persona> setInfo(List<String> list) {
		Map<Integer, Persona> personas = new HashMap<>();
		Persona persona;
		String[] info;

		Iterator<String> it = list.iterator();
		while (it.hasNext()) {
			persona = new Persona();
			info = splitString(it.next());

			persona.setID(Integer.parseInt(info[0]));
			persona.setPlayerID(Integer.parseInt(info[1]));
			persona.setGamerTag(info[2]);
			persona.setPlatform(info[3]);

			// Add persona in map with persona ID as key
			personas.put(persona.getID(), persona);
		}

		return personas;
	}
}
