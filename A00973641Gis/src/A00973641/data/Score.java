/**
 * Project: A00973641Gis
 * File: Score.java
 * Date: Feb 12, 2016
 * Time: 2:30:31 AM
 */
package A00973641.data;

/**
 * @author Mara
 *
 */
public class Score {

	private int ID;
	private int personaID;
	private String gameID;
	private boolean win;

	/**
	 * @return the iD
	 */
	public int getID() {
		return ID;
	}

	/**
	 * @param iD
	 *            the iD to set
	 */
	public void setID(int iD) {
		ID = iD;
	}

	/**
	 * @return the personaId
	 */
	public int getPersonaID() {
		return personaID;
	}

	/**
	 * @param personaId
	 *            the personaId to set
	 */
	public void setPersonaID(int personaId) {
		this.personaID = personaId;
	}

	/**
	 * @return the gameId
	 */
	public String getGameID() {
		return gameID;
	}

	/**
	 * @param gameId
	 *            the gameId to set
	 */
	public void setGameID(String gameId) {
		this.gameID = gameId;
	}

	/**
	 * @return the win
	 */
	public boolean isWin() {
		return win;
	}

	/**
	 * @param win
	 *            the win to set
	 */
	public void setWin(String win) {
		if (win.equalsIgnoreCase("WIN"))
			this.win = true;
		else
			this.win = false;
	}

	@Override
	public String toString() {
		return "Score [id=" + ID + ", persona id=" + personaID + ", game id=" + gameID + ", win=" + win + "]";
	}
}
