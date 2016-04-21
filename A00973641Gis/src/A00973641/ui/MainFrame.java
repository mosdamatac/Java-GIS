/**
 * Project: A00973641Gis
 * File: MainFrame.java
 * Date: Mar 22, 2016
 * Time: 5:32:58 PM
 */
package A00973641.ui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.swing.AbstractListModel;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import A00973641.data.DataMap;
import A00973641.data.DisplayData;
import A00973641.data.enums.Argument;
import A00973641.database.util.DaoManipulator;
import A00973641.util.SetData;
import A00973641.util.sort.CompareByCount;
import A00973641.util.sort.CompareByGameName;
import A00973641.util.sort.CompareByGamerTag;

/**
 * @author Mara
 *
 */
@SuppressWarnings("serial")
public class MainFrame extends JFrame {
	private static final Logger LOG = LogManager.getLogger(MainFrame.class);
	private final JPanel contentPanel = new JPanel();
	JCheckBoxMenuItem chckbxmntmDescending;
	private JList<String> mainFrameView;
	private MainFrameListData mainFrameModel;
	// flags for sorting
	private Argument currentSort;

	/**
	 * Create the frame.
	 */
	public MainFrame(DataMap datamap) {
		currentSort = Argument.BY_GAMERTAG; // Default

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 557, 350);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 538, 291);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		contentPanel.add(scrollPane);

		mainFrameModel = new MainFrameListData(datamap);
		mainFrameView = new JList<>(mainFrameModel);
		mainFrameView.setFont(new Font("Monospaced", Font.PLAIN, 12));
		scrollPane.setViewportView(mainFrameView);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnFile = new JMenu("File");
		mnFile.setMnemonic(KeyEvent.VK_F);
		menuBar.add(mnFile);

		JMenuItem mntmQuit = new JMenuItem("Quit");
		mntmQuit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		mntmQuit.setMnemonic(KeyEvent.VK_F4);
		mnFile.add(mntmQuit);

		JMenu mnLists = new JMenu("Lists");
		mnLists.setMnemonic(KeyEvent.VK_L);
		menuBar.add(mnLists);

		JMenuItem mntmPlayers = new JMenuItem("Players");
		mntmPlayers.setMnemonic(KeyEvent.VK_P);
		mntmPlayers.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				LOG.info("Opening PlayerDialog");
				try {
					PlayerDialog playerDialog = new PlayerDialog();
					playerDialog.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
					playerDialog.setVisible(true);
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
					LOG.error(ex.getMessage());
				}
			}
		});
		mnLists.add(mntmPlayers);

		JMenuItem mntmPersonas = new JMenuItem("Personas");
		mntmPersonas.setMnemonic(KeyEvent.VK_E);
		mntmPersonas.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				LOG.info("Opening PersonaDialog");
				try {
					PersonaDialog personaDialog = new PersonaDialog();
					personaDialog.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
					personaDialog.setVisible(true);
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
					LOG.error(ex.getMessage());
				}
			}
		});
		mnLists.add(mntmPersonas);

		JMenuItem mntmScores = new JMenuItem("Scores");
		mntmScores.setMnemonic(KeyEvent.VK_S);
		mntmScores.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					ScoreDialog scoreDialog = new ScoreDialog();
					scoreDialog.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
					scoreDialog.setVisible(true);
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
					LOG.error(ex.getMessage());
				}
			}
		});
		mnLists.add(mntmScores);

		JMenu mnReports = new JMenu("Reports");
		mnReports.setMnemonic(KeyEvent.VK_R);
		menuBar.add(mnReports);

		JMenuItem mntmTotal = new JMenuItem("Total");
		mntmTotal.setMnemonic(KeyEvent.VK_O);
		mntmTotal.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				mainFrameModel.update(mntmTotal.getText());
			}
		});
		mnReports.add(mntmTotal);

		chckbxmntmDescending = new JCheckBoxMenuItem("Descending");
		chckbxmntmDescending.setMnemonic(KeyEvent.VK_D);
		chckbxmntmDescending.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				mainFrameModel.update(chckbxmntmDescending.getText());
				mainFrameView = new JList<>(mainFrameModel);
			}
		});
		mnReports.add(chckbxmntmDescending);

		JMenuItem mntmByGame = new JMenuItem("By Game");
		mntmByGame.setMnemonic(KeyEvent.VK_G);
		mntmByGame.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				mainFrameModel.update(mntmByGame.getText());
				mainFrameView = new JList<>(mainFrameModel);
			}
		});
		mnReports.add(mntmByGame);

		JMenuItem mntmByCount = new JMenuItem("By Count");
		mntmByCount.setMnemonic(KeyEvent.VK_C);
		mntmByCount.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				mainFrameModel.update(mntmByCount.getText());
				mainFrameView = new JList<>(mainFrameModel);
			}
		});
		mnReports.add(mntmByCount);

		JMenuItem mntmGamertag = new JMenuItem("Gamertag");
		mntmGamertag.setMnemonic(KeyEvent.VK_T);
		mntmGamertag.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				mainFrameModel.update(mntmGamertag.getText());
				mainFrameView = new JList<>(mainFrameModel);
			}
		});
		mnReports.add(mntmGamertag);

		JMenu mnHelp = new JMenu("Help");
		mnHelp.setMnemonic(KeyEvent.VK_H);
		menuBar.add(mnHelp);

		JMenuItem mntmAbout = new JMenuItem("About");
		mntmAbout.setMnemonic(KeyEvent.VK_F1);
		mntmAbout.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String message = "GIS \nBy Mara Damatac A00973641";
				String about = "About GIS";
				JOptionPane.showMessageDialog(new JFrame(), message, about, JOptionPane.INFORMATION_MESSAGE);
			}
		});
		mnHelp.add(mntmAbout);
		getContentPane().setLayout(null);
		contentPanel.setBounds(0, 0, 538, 291);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel);
		contentPanel.setLayout(null);
	}

	private class MainFrameListData extends AbstractListModel<String> {
		List<String> stringList;
		List<DisplayData> displayData;
		List<String> gamertags;
		DataMap datamap;

		public MainFrameListData(DataMap datamap) {
			this.datamap = datamap;
			this.stringList = new ArrayList<>();
			this.gamertags = new ArrayList<>();
			try {
				gamertags = DaoManipulator.getPersonaDao().getGamertags();
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				LOG.error(e.getMessage());
			}
			SetData.set(datamap);
			displayData = SetData.get();
			Collections.sort(displayData, new CompareByGamerTag());

			setStringList(displayData);
		}

		@Override
		public int getSize() {
			return stringList.size();
		}

		@Override
		public String getElementAt(int index) {
			return stringList.get(index);
		}

		public void update(String obj) {
			Argument arg = Argument.get(obj.toLowerCase());
			if (arg.equals(Argument.DESC)) {
				arg = currentSort;
			}

			switch (arg) {
			case TOTAL:
				Map<String, Integer> counts = new HashMap<>();
				String key;
				for (DisplayData data : displayData) {
					key = data.getGame();
					if (counts.containsKey(key)) {
						counts.put(key, counts.get(key) + data.getWin() + data.getLoss());
					} else {
						counts.put(key, data.getWin() + data.getLoss());
					}
				}

				String message = "Total count of each game played:\n";
				String total = "Total";
				for (Map.Entry<String, Integer> entry : counts.entrySet()) {
					message += String.format("%-15s %d %n", entry.getKey(), entry.getValue());
				}
				JOptionPane.showMessageDialog(new JFrame(), message, total, JOptionPane.INFORMATION_MESSAGE);
				break;
			case BY_GAME:
				currentSort = Argument.BY_GAME;
				if (chckbxmntmDescending.isSelected()) {
					Collections.sort(displayData, Collections.reverseOrder(new CompareByGameName()));
				} else {
					Collections.sort(displayData, new CompareByGameName());
				}
				break;
			case BY_COUNT:
				currentSort = Argument.BY_COUNT;
				if (chckbxmntmDescending.isSelected()) {
					Collections.sort(displayData, Collections.reverseOrder(new CompareByCount()));
				} else {
					Collections.sort(displayData, new CompareByCount());
				}
				break;
			case BY_GAMERTAG:
				currentSort = Argument.BY_GAMERTAG;
				if (chckbxmntmDescending.isSelected()) {
					Collections.sort(displayData, Collections.reverseOrder(new CompareByGamerTag()));
				} else {
					Collections.sort(displayData, new CompareByGamerTag());
				}
				break;
			case FILTER:
				String gamertag = JOptionPane.showInputDialog("Enter gamertag");
				if (gamertag == null)
					return;

				if (gamertag.trim().equals("") || gamertag.trim().length() == 0) {
					// Remove filter: reset data
					currentSort = Argument.BY_GAMERTAG;
					SetData.set(datamap);
					displayData = SetData.get();
					Collections.sort(displayData, new CompareByGamerTag());
				} else if (!gamertags.contains(gamertag)) {
					JOptionPane.showMessageDialog(new JFrame(), "Invalid gamertag", "Warning",
							JOptionPane.WARNING_MESSAGE);
					return;
				} else {
					Iterator<DisplayData> it = displayData.iterator();
					DisplayData data;
					while (it.hasNext()) {
						data = it.next();
						// Remove element if gamertag is not the same as
						// specified
						if (!data.getGamertag().equalsIgnoreCase(gamertag))
							it.remove();
					}
				}
				break;
			default:
				break;
			}

			setStringList(displayData);
			fireContentsChanged(this, 0, getSize());
		}

		private void setStringList(List<DisplayData> displayData) {
			stringList = new ArrayList<>();
			for (DisplayData data : displayData) {
				String str = String.format("%d:%-6d %-18s %-15s %s", data.getWin(), data.getLoss(), data.getGame(),
						data.getGamertag(), data.getPlatform());
				this.stringList.add(str);
			}
			String header = String.format("%s:%s %5s %17s %15s", "Win", "Loss", "Game Name", "Gamertag", "Platform");
			String div = "----------------------------------------------------";
			stringList.add(0, header);
			stringList.add(1, div);
		}
	}
}
