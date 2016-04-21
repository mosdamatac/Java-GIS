/**
 * Project: A00973641Gis
 * File: PersonaDialog.java
 * Date: Mar 22, 2016
 * Time: 7:15:54 PM
 */
package A00973641.ui;

import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import A00973641.data.Persona;
import A00973641.data.Player;
import A00973641.database.dao.PersonaDao;
import A00973641.database.dao.PlayerDao;
import A00973641.database.util.DaoManipulator;

/**
 * @author Mara
 *
 */
@SuppressWarnings("serial")
public class PersonaDialog extends JDialog {

	private static final Logger LOG = LogManager.getLogger(PersonaDialog.class);
	private final JPanel contentPanel = new JPanel();
	private JList<String> personasView;
	private PersonaListData personasModel;
	private PersonasController personasController;
	private final JScrollPane scrollPane = new JScrollPane();
	private PersonaDao personaDao;

	/**
	 * Create the dialog.
	 */
	public PersonaDialog() {
		this.personaDao = DaoManipulator.getPersonaDao();
		setModal(true);
		setBounds(100, 100, 337, 280);
		getContentPane().setLayout(null);
		contentPanel.setBounds(0, 0, 321, 242);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel);
		contentPanel.setLayout(null);
		personasModel = new PersonaListData(personaDao);
		personasView = new JList<String>(personasModel);
		personasView.setFont(new Font("Monospaced", Font.PLAIN, 12));
		// TODO add observer/observable
		personasController = new PersonasController();
		// TODO select from database only when double click
		ListSelectionModel listSelectionModel = personasView.getSelectionModel();
		listSelectionModel.addListSelectionListener(personasController);
		personasView.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					if (e.getClickCount() == 2) {
						LOG.info("Opening PersonaDetailsDialog");
						PersonaDetailsDialog personaDetailsDialog = new PersonaDetailsDialog(
								personasController.getPersona(), personasController.getPlayer());
						personaDetailsDialog.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
						personaDetailsDialog.setVisible(true);
					}
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
					LOG.error(ex.getMessage());
				}
			}
		});
		{
			scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
			scrollPane.setBounds(0, 0, 320, 240);
			contentPanel.add(scrollPane);
			scrollPane.setViewportView(personasView);
		}
	}

	// Inner Class

	private class PersonaListData extends AbstractListModel<String> {

		List<String> personas;

		public PersonaListData(PersonaDao personaDao) {
			this.personas = new ArrayList<>();
			List<Persona> personas = new ArrayList<>();
			try {
				personas = personaDao.getPersonas();
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				LOG.error(e.getMessage());
			}

			// Format personas
			Iterator<Persona> iterator = personas.iterator();
			Persona persona = null;
			while (iterator.hasNext()) {
				persona = iterator.next();
				this.personas.add(persona.getGamerTag());
			}
		}

		@Override
		public int getSize() {
			return personas.size();
		}

		@Override
		public String getElementAt(int index) {
			return personas.get(index);
		}
	}

	private class PersonasController implements ListSelectionListener {

		private Persona persona;
		private Player player;

		@Override
		public void valueChanged(ListSelectionEvent e) {
			if (e.getValueIsAdjusting()) {
				return;
			}

			Object o = personasView.getSelectedValue();
			if (o == null) {
				return;
			}

			PlayerDao playerDao = DaoManipulator.getPlayerDao();
			persona = new Persona();
			player = new Player();
			String gamertag = o.toString();
			try {
				persona = personaDao.getDetails(gamertag);
				player = playerDao.getDetails(persona.getPlayerID());
			} catch (SQLException ex) {
				JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				LOG.error(ex.getMessage());
			}
		}

		public Persona getPersona() {
			return persona;
		}

		public Player getPlayer() {
			return player;
		}
	}
}
