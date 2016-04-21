/**
 * Project: A00973641Gis
 * File: ScoreDaoTester.java
 * Date: Mar 22, 2016
 * Time: 4:48:15 PM
 */
package A00973641.database.tester;

import java.sql.SQLException;
import java.util.List;

import javax.swing.JOptionPane;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import A00973641.data.Score;
import A00973641.database.dao.ScoreDao;

/**
 * @author Mara
 *
 */
public class ScoreDaoTester {

	private static Logger LOG = LogManager.getLogger(ScoreDaoTester.class);

	public ScoreDaoTester() {

	}

	public void test() {
		ScoreDao tester = new ScoreDao();
		try {
			List<Score> scores = tester.getScores();
			for (Score score : scores) {
				LOG.info("Test successful. Loaded: " + score.toString());
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			LOG.error(e.getMessage());
		}
	}
}
