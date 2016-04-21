/**
 * Project: A00973641Gis
 * File: Game.java
 * Date: Feb 12, 2016
 * Time: 2:30:24 AM
 */
package A00973641.data;

/**
 * @author Mara
 *
 */
public class Game {
	private String ID;
	private String name;
	private String producer;

	/**
	 * @return the id
	 */
	public String getID() {
		return ID;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setID(String ID) {
		this.ID = ID;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the producer
	 */
	public String getProducer() {
		return producer;
	}

	/**
	 * @param producer
	 *            the producer to set
	 */
	public void setProducer(String producer) {
		this.producer = producer;
	}

	@Override
	public String toString() {
		return "Game [id=" + ID + ", name=" + name + ", producer=" + producer + "]";
	}

}
