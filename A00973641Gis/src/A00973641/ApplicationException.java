/**
 * Project: A00973641Gis
 * File: ApplicationException.java
 * Date: Feb 12, 2016
 * Time: 5:58:16 PM
 */
package A00973641;

/**
 * @author Mara
 *
 */
@SuppressWarnings("serial")
public class ApplicationException extends Exception {

	public ApplicationException(int totalElems, int currentElems) {
		super("Expected " + totalElems + " elements but got " + currentElems);
	}

	public ApplicationException(String email) {
		super("\'" + email + "\' is an invalid email address");
	}

}
