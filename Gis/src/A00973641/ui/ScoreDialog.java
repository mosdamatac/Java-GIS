/**
 * Project: A00973641Gis
 * File: ScoreDialog.java
 * Date: Mar 23, 2016
 * Time: 3:26:06 PM
 */
package A00973641.ui;

import java.awt.BorderLayout;
import java.awt.Font;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.AbstractListModel;
import javax.swing.JDialog;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import A00973641.data.Score;
import A00973641.database.dao.ScoreDao;
import A00973641.database.util.DaoManipulator;

/**
 * @author Mara
 *
 */
@SuppressWarnings("serial")
public class ScoreDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private static final Logger LOG = LogManager.getLogger(ScoreDialog.class);
	private ScoreListData scoresModel;
	private JList<String> scoresView;

	/**
	 * Create the dialog.
	 */
	public ScoreDialog() {
		setModal(true);
		setBounds(100, 100, 337, 280);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 321, 242);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		contentPanel.add(scrollPane);

		ScoreDao scoreDao = DaoManipulator.getScoreDao();
		scoresModel = new ScoreListData(scoreDao);
		scoresView = new JList<String>(scoresModel);
		scoresView.setFont(new Font("Monospaced", Font.PLAIN, 12));
		scrollPane.setViewportView(scoresView);
	}

	// Inner Class

	private class ScoreListData extends AbstractListModel<String> {

		List<String> scores;

		public ScoreListData(ScoreDao scoreDao) {
			this.scores = new ArrayList<>();
			List<Score> scores = new ArrayList<>();
			try {
				scores = scoreDao.getScores();
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				LOG.error(e.getMessage());
			}

			Iterator<Score> iterator = scores.iterator();
			Score score = null;
			while (iterator.hasNext()) {
				score = iterator.next();
				String display = String.format("%d %s %b", score.getPersonaID(), score.getGameID(), score.isWin());
				this.scores.add(display);
			}
		}

		@Override
		public int getSize() {
			return scores.size();
		}

		@Override
		public String getElementAt(int index) {
			return scores.get(index);
		}

	}
}
