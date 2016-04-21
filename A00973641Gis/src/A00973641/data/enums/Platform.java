/**
 * Project: A00973641Gis
 * File: Platform.java
 * Date: Feb 20, 2016
 * Time: 12:13:29 AM
 */
package A00973641.data.enums;

/**
 * @author Mara
 *
 */
public enum Platform {
	IO, PS, AN, PC, XB;

	public static boolean contains(String str) {
		for (Platform p : Platform.values()) {
			if (p.name().contains(str.toUpperCase())) {
				return true;
			}
		}

		return false;
	}
}
