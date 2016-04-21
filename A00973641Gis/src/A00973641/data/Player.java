/**
 * Project: A00973641Gis
 * File: Player.java
 * Date: Feb 12, 2016
 * Time: 2:29:57 AM
 */
package A00973641.data;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

/**
 * @author Mara
 *
 */
public class Player {

	private static final String INPUT_DATE_FORMAT = "yyyyMMdd";

	private int ID;
	private String firstName;
	private String lastName;
	private String email;
	private String birthdate;
	private long age;
	private int totalGamesPlayed;
	private int totalWins;

	/**
	 * Constructor for Player class
	 */
	public Player() {
		totalGamesPlayed = 0;
		totalWins = 0;
	}

	/**
	 * @return the totalGamesPlayed
	 */
	public int getTotalGamesPlayed() {
		return totalGamesPlayed;
	}

	/**
	 * @param totalGamesPlayed
	 *            the totalGamesPlayed to set
	 */
	public void setTotalGamesPlayed(int totalGamesPlayed) {
		this.totalGamesPlayed += totalGamesPlayed;
	}

	/**
	 * @return the totalWins
	 */
	public int getTotalWins() {
		return totalWins;
	}

	/**
	 * @param totalWins
	 *            the totalWins to set
	 */
	public void setTotalWins(int totalWins) {
		this.totalWins += totalWins;
	}

	/**
	 * @return the id
	 */
	public int getID() {
		return ID;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setID(int ID) {
		this.ID = ID;
	}

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName
	 *            the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName
	 *            the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email
	 *            the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the birthdate
	 */
	public String getBirthdate() {
		return birthdate;
	}

	/**
	 * @param birthdate
	 *            the birthdate to set
	 */
	public void setBirthdate(String birthdate) {
		this.birthdate = birthdate;
	}

	/**
	 * Compute for the age of player
	 */
	public void setAge() {
		LocalDate date = LocalDate.parse(this.birthdate, DateTimeFormatter.ofPattern(INPUT_DATE_FORMAT));
		LocalDate now = LocalDate.now();
		age = ChronoUnit.YEARS.between(date, now);
	}

	/**
	 * 
	 * @return the age
	 */
	public long getAge() {
		return age;
	}

	@Override
	public String toString() {
		return "Player [id=" + ID + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
				+ ", birth date=" + birthdate + ", age=" + age + ", total games played=" + totalGamesPlayed
				+ ", total wins=" + totalWins + "]";
	}

}
