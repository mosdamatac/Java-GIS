/**
 * Project: A00973641Gis
 * File: CompareByGame.java
 * Date: Feb 19, 2016
 * Time: 12:27:34 AM
 */
package A00973641.util.sort;

import java.util.Comparator;

import A00973641.data.DisplayData;

/**
 * @author Mara
 *
 */
public class CompareByGameName implements Comparator<DisplayData> {

	/**
	 * Compare dataset by game name.
	 */
	@Override
	public int compare(DisplayData dataset1, DisplayData dataset2) {
		String g1 = dataset1.getGame();
		String g2 = dataset2.getGame();

		return g1.compareTo(g2);
	}
}
