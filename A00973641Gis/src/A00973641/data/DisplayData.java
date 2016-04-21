/**
 * Project: A00973641Gis
 * File: Test.java
 * Date: Feb 13, 2016
 * Time: 2:31:33 AM
 */
package A00973641.data;

/**
 * @author Mara Damatac
 * 
 *         Create a single object that contains all of the data objects: Game,
 *         Persona, Player, Score
 *
 */
public class DisplayData {

	int win;
	int loss;
	String game;
	String gamertag;
	String platform;

	/**
	 * Constructor for DataSet
	 */
	public DisplayData() {

	}

	/**
	 * @return the win
	 */
	public int getWin() {
		return win;
	}

	/**
	 * @param win
	 *            the win to set
	 */
	public void setWin(int win) {
		this.win = win;
	}

	/**
	 * @return the loss
	 */
	public int getLoss() {
		return loss;
	}

	/**
	 * @param loss
	 *            the loss to set
	 */
	public void setLoss(int loss) {
		this.loss = loss;
	}

	/**
	 * @return the game
	 */
	public String getGame() {
		return game;
	}

	/**
	 * @param game
	 *            the game to set
	 */
	public void setGame(String game) {
		this.game = game;
	}

	/**
	 * @return the gamertag
	 */
	public String getGamertag() {
		return gamertag;
	}

	/**
	 * @param gamertag
	 *            the gamertag to set
	 */
	public void setGamertag(String gamertag) {
		this.gamertag = gamertag;
	}

	/**
	 * @return the platform
	 */
	public String getPlatform() {
		return platform;
	}

	/**
	 * @param platform
	 *            the platform to set
	 */
	public void setPlatform(String platform) {
		this.platform = platform;
	}
}
