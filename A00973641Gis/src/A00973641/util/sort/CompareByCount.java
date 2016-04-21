/**
 * Project: A00973641Gis
 * File: CompareByCount.java
 * Date: Feb 19, 2016
 * Time: 5:10:04 PM
 */
package A00973641.util.sort;

import java.util.Comparator;

import A00973641.data.DisplayData;

/**
 * @author Mara
 *
 */
public class CompareByCount implements Comparator<DisplayData> {

	/**
	 * Compare dataset by win and loss counts
	 */
	@Override
	public int compare(DisplayData dataset1, DisplayData dataset2) {
		int win1 = dataset1.getWin();
		int win2 = dataset2.getWin();
		int loss1 = dataset1.getLoss();
		int loss2 = dataset2.getLoss();

		if (win1 == win2) {
			return loss1 - loss2;
		} else {
			return win1 - win2;
		}
	}
}
