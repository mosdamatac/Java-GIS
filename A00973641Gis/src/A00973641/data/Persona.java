/**
 * Project: A00973641Gis
 * File: Persona.java
 * Date: Feb 12, 2016
 * Time: 2:30:16 AM
 */
package A00973641.data;

/**
 * @author Mara
 *
 */
public class Persona {

	private int ID;
	private int playerID;
	private String gamerTag;
	private String platform;

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
	 * @return the playerID
	 */
	public int getPlayerID() {
		return playerID;
	}

	/**
	 * @param playerID
	 *            the playerID to set
	 */
	public void setPlayerID(int playerID) {
		this.playerID = playerID;
	}

	/**
	 * @return the gamerTag
	 */
	public String getGamerTag() {
		return gamerTag;
	}

	/**
	 * @param gamerTag
	 *            the gamerTag to set
	 */
	public void setGamerTag(String gamerTag) {
		this.gamerTag = gamerTag;
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

	@Override
	public String toString() {
		return "Persona [id=" + ID + ", player id=" + playerID + ", gamer tag=" + gamerTag + ", platform=" + platform
				+ "]";
	}

}
