/**
 * Project: A00973641Gis
 * File: DbConstants.java
 * Date: Mar 20, 2016
 * Time: 4:10:23 PM
 */
package A00973641.database;

/**
 * @author Mara
 *
 */
public interface DbConstants {

	String DB_PROPERTIES_FILENAME = "db.properties";
	String DB_DRIVER_KEY = "db.driver";
	String DB_URL_KEY = "db.url";
	String DB_USER_KEY = "db.user";
	String DB_PASSWORD_KEY = "db.password";
	String TABLE_ROOT = "S641_";
	String GAME_TABLE_NAME = TABLE_ROOT + "Game";
	String PERSONA_TABLE_NAME = TABLE_ROOT + "Persona";
	String PLAYER_TABLE_NAME = TABLE_ROOT + "Player";
	String SCORE_TABLE_NAME = TABLE_ROOT + "Score";
}
