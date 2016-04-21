/**
 * Project: A00973641Gis
 * File: PlayerDialog.java
 * Date: Mar 22, 2016
 * Time: 5:49:05 PM
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

import A00973641.data.Player;
import A00973641.database.dao.PlayerDao;
import A00973641.database.util.DaoManipulator;

/**
 * @author Mara
 *
 */
@SuppressWarnings("serial")
public class PlayerDialog extends JDialog {

	private static final Logger LOG = LogManager.getLogger(PlayerDialog.class);
	private final JPanel contentPanel = new JPanel();
	private JList<String> playersView;
	private PlayerListData playersModel;

	/**
	 * Create the dialog.
	 */
	public PlayerDialog() {
		setModal(true);
		PlayerDao playerDao = DaoManipulator.getPlayerDao();
		setBounds(100, 100, 337, 280);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		playersModel = new PlayerListData(playerDao);
		playersView = new JList<String>(playersModel);
		playersView.setFont(new Font("Monospaced", Font.PLAIN, 12));
		{
			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
			scrollPane.setBounds(0, 0, 320, 240);
			contentPanel.add(scrollPane);
			scrollPane.setViewportView(playersView);
		}
	}

	// Inner Classes

	private class PlayerListData extends AbstractListModel<String> {

		List<String> players;

		public PlayerListData(PlayerDao playerDao) {
			this.players = new ArrayList<>();
			List<Player> players = new ArrayList<>();
			try {
				players = playerDao.getPlayers();
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				LOG.error(e.getMessage());
			}

			// Format players
			Iterator<Player> iterator = players.iterator();
			Player player = null;
			while (iterator.hasNext()) {
				player = iterator.next();
				String display = String.format("%s %s", player.getFirstName(), player.getLastName());
				this.players.add(display);
			}
		}

		@Override
		public int getSize() {
			return players.size();
		}

		@Override
		public String getElementAt(int index) {
			return players.get(index);
		}
	}
}
