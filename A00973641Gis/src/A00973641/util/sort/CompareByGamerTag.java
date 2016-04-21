/**
 * Project: A00973641Gis
 * File: CompareByPersona.java
 * Date: Feb 18, 2016
 * Time: 11:43:35 PM
 */
package A00973641.util.sort;

import java.util.Comparator;

import A00973641.data.DisplayData;

/**
 * @author Mara
 *
 */
public class CompareByGamerTag implements Comparator<DisplayData> {

	/**
	 * Compare dataset by gamer tag.
	 */
	@Override
	public int compare(DisplayData dataset1, DisplayData dataset2) {
		String p1 = dataset1.getGamertag();
		String p2 = dataset2.getGamertag();

		return p1.compareTo(p2);
	}
}
