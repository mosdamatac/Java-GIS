/**
 * Project: A00973641Gis
 * File: Validator.java
 * Date: Feb 12, 2016
 * Time: 2:19:58 PM
 */
package A00973641.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Mara
 *
 */
public class Validator {

	private static final Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern
			.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

	public Validator() {

	}

	/**
	 * Determine if number of elements is equal to expected
	 * 
	 * @param current
	 * @param expected
	 * @return boolean if current is equal to expected or not
	 */
	public static boolean isElementsEqual(int total, int expected) {
		return total == expected;
	}

	public static boolean isValidEmail(String email) {
		Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(email);
		return matcher.find();
	}

}
