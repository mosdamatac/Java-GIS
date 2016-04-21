/**
 * Project: A00973641Gis
 * File: PersonaDetailsDialog.java
 * Date: Mar 23, 2016
 * Time: 2:44:34 PM
 */
package A00973641.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

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
public class PersonaDetailsDialog extends JDialog {

	private static final Logger LOG = LogManager.getLogger(PersonaDetailsDialog.class);
	private static final PersonaDao personaDao = DaoManipulator.getPersonaDao();
	private static final PlayerDao playerDao = DaoManipulator.getPlayerDao();
	private final JPanel contentPanel = new JPanel();
	private JTextField textFieldFirstName;
	private JTextField textFieldPlatform;
	private JTextField textFieldGamertag;
	private JTextField textFieldPlayerID;
	private JTextField textFieldID;
	private JTextField textFieldLastName;
	private JTextField textFieldEmail;
	private JTextField textFieldBirthdate;
	private JTextField textFieldAge;

	/**
	 * Create the dialog.
	 */
	public PersonaDetailsDialog(Persona persona, Player player) {
		setModal(true);
		setBounds(100, 100, 407, 316);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		JLabel lblId = new JLabel("ID");
		lblId.setFont(new Font("Monospaced", Font.PLAIN, 12));
		lblId.setBounds(10, 11, 78, 14);
		contentPanel.add(lblId);

		JLabel lblPlayerId = new JLabel("Player ID");
		lblPlayerId.setFont(new Font("Monospaced", Font.PLAIN, 12));
		lblPlayerId.setBounds(10, 36, 78, 14);
		contentPanel.add(lblPlayerId);

		JLabel lblGamertag = new JLabel("Gamertag");
		lblGamertag.setFont(new Font("Monospaced", Font.PLAIN, 12));
		lblGamertag.setBounds(10, 61, 78, 14);
		contentPanel.add(lblGamertag);

		JLabel lblPlatform = new JLabel("Platform");
		lblPlatform.setFont(new Font("Monospaced", Font.PLAIN, 12));
		lblPlatform.setBounds(10, 86, 78, 14);
		contentPanel.add(lblPlatform);

		JLabel lblFirstName = new JLabel("First Name");
		lblFirstName.setFont(new Font("Monospaced", Font.PLAIN, 12));
		lblFirstName.setBounds(10, 111, 78, 14);
		contentPanel.add(lblFirstName);

		JLabel lblLastName = new JLabel("Last Name");
		lblLastName.setFont(new Font("Monospaced", Font.PLAIN, 12));
		lblLastName.setBounds(10, 136, 78, 14);
		contentPanel.add(lblLastName);

		JLabel lblEmail = new JLabel("Email");
		lblEmail.setFont(new Font("Monospaced", Font.PLAIN, 12));
		lblEmail.setBounds(10, 161, 78, 14);
		contentPanel.add(lblEmail);

		JLabel lblBirthdate = new JLabel("Birthdate");
		lblBirthdate.setFont(new Font("Monospaced", Font.PLAIN, 12));
		lblBirthdate.setBounds(10, 186, 78, 14);
		contentPanel.add(lblBirthdate);

		JLabel lblAge = new JLabel("Age");
		lblAge.setFont(new Font("Monospaced", Font.PLAIN, 12));
		lblAge.setBounds(10, 211, 78, 14);
		contentPanel.add(lblAge);

		textFieldFirstName = new JTextField();
		textFieldFirstName.setFont(new Font("Monospaced", Font.PLAIN, 12));
		textFieldFirstName.setBounds(98, 111, 278, 20);
		contentPanel.add(textFieldFirstName);
		textFieldFirstName.setColumns(10);
		textFieldFirstName.setText(player.getFirstName());

		textFieldPlatform = new JTextField();
		textFieldPlatform.setFont(new Font("Monospaced", Font.PLAIN, 12));
		textFieldPlatform.setEditable(false);
		textFieldPlatform.setBounds(98, 86, 278, 20);
		contentPanel.add(textFieldPlatform);
		textFieldPlatform.setColumns(10);
		textFieldPlatform.setText(persona.getPlatform());

		textFieldGamertag = new JTextField();
		textFieldGamertag.setFont(new Font("Monospaced", Font.PLAIN, 12));
		textFieldGamertag.setBounds(98, 61, 278, 20);
		contentPanel.add(textFieldGamertag);
		textFieldGamertag.setColumns(10);
		textFieldGamertag.setText(persona.getGamerTag());

		textFieldPlayerID = new JTextField();
		textFieldPlayerID.setFont(new Font("Monospaced", Font.PLAIN, 12));
		textFieldPlayerID.setEditable(false);
		textFieldPlayerID.setBounds(98, 36, 278, 20);
		contentPanel.add(textFieldPlayerID);
		textFieldPlayerID.setColumns(10);
		textFieldPlayerID.setText(String.valueOf(persona.getPlayerID()));

		textFieldID = new JTextField();
		textFieldID.setFont(new Font("Monospaced", Font.PLAIN, 12));
		textFieldID.setEditable(false);
		textFieldID.setBounds(98, 11, 278, 20);
		contentPanel.add(textFieldID);
		textFieldID.setColumns(10);
		textFieldID.setText(String.valueOf(persona.getID()));

		textFieldLastName = new JTextField();
		textFieldLastName.setFont(new Font("Monospaced", Font.PLAIN, 12));
		textFieldLastName.setBounds(98, 136, 278, 20);
		contentPanel.add(textFieldLastName);
		textFieldLastName.setColumns(10);
		textFieldLastName.setText(player.getLastName());

		textFieldEmail = new JTextField();
		textFieldEmail.setFont(new Font("Monospaced", Font.PLAIN, 12));
		textFieldEmail.setBounds(98, 161, 278, 20);
		contentPanel.add(textFieldEmail);
		textFieldEmail.setColumns(10);
		textFieldEmail.setText(player.getEmail());

		textFieldBirthdate = new JTextField();
		textFieldBirthdate.setFont(new Font("Monospaced", Font.PLAIN, 12));
		textFieldBirthdate.setEditable(false);
		textFieldBirthdate.setBounds(98, 186, 278, 20);
		contentPanel.add(textFieldBirthdate);
		textFieldBirthdate.setColumns(10);
		textFieldBirthdate.setText(player.getBirthdate());

		textFieldAge = new JTextField();
		textFieldAge.setFont(new Font("Monospaced", Font.PLAIN, 12));
		textFieldAge.setEditable(false);
		textFieldAge.setBounds(98, 211, 278, 20);
		contentPanel.add(textFieldAge);
		textFieldAge.setColumns(10);
		textFieldAge.setText(String.valueOf(player.getAge()));
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				PersonaUpdate update = new PersonaUpdate();
				update.setData(persona, player);
				okButton.addActionListener(update);
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

	private class PersonaUpdate implements ActionListener {

		Player player;
		Persona persona;

		public void setData(Persona persona, Player player) {
			this.player = player;
			this.persona = persona;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			persona.setGamerTag(textFieldGamertag.getText());
			player.setFirstName(textFieldFirstName.getText());
			player.setLastName(textFieldLastName.getText());
			player.setEmail(textFieldEmail.getText());
			try {
				personaDao.update(persona);
				playerDao.update(player);
			} catch (SQLException ex) {
				JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				LOG.error(ex.getMessage());
			}

			dispose();
		}

	}
}
