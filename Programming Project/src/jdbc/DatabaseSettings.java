package jdbc;

public class DatabaseSettings {

	/**
	 * Username for access to Database
	 */
	private String DB_USERNAME = "40142115";

	/**
	 * Use if Username is part of the Databases URL, otherwise leave blank
	 */
	private String URL_USERNAME = "40142115";

	/**
	 * Password for access to Database
	 */
	private String DB_PASSWORD = "YCR2335";

	/**
	 * Main body of URL of Database, eg //web2.eeecs.qub.ac.uk/ or
	 * thin:@db.yale.edu:univdb
	 */
	private String WEB_ADDRESS = "//web2.eeecs.qub.ac.uk/";

	/**
	 * Type of database, eg mysql: or oracle: etc
	 */
	private String DB_PROTOCOL = "mysql:";

	/**
	 * name of Database driver to be called, for mysql com.mysql.jdbc.Driver or
	 * for oracle oracle.jdbc.driver.OracleDriver
	 */
	private String DB_DRIVER = "com.mysql.jdbc.Driver";

	public DatabaseSettings() {
	}
	
	public DatabaseSettings(String DB_USERNAME, String URL_USERNAME, String DB_PASSWORD, String WEB_ADDRESS) {
		this.DB_USERNAME = DB_USERNAME;
		this.DB_PASSWORD = DB_PASSWORD;
		this.URL_USERNAME = URL_USERNAME;
		this.WEB_ADDRESS = WEB_ADDRESS;
		this.DB_PROTOCOL = "mysql:";
		this.DB_DRIVER = "com.mysql.jdbc.Driver";
	}
	
	public String getDB_USERNAME() {
		return DB_USERNAME;
	}

	public void setDB_USERNAME(String dB_USERNAME) {
		DB_USERNAME = dB_USERNAME;
	}

	public String getURL_USERNAME() {
		return URL_USERNAME;
	}

	public void setURL_USERNAME(String uRL_USERNAME) {
		URL_USERNAME = uRL_USERNAME;
	}

	public String getDB_PASSWORD() {
		return DB_PASSWORD;
	}

	public void setDB_PASSWORD(String dB_PASSWORD) {
		DB_PASSWORD = dB_PASSWORD;
	}

	public String getWEB_ADDRESS() {
		return WEB_ADDRESS;
	}

	public void setWEB_ADDRESS(String wEB_ADDRESS) {
		WEB_ADDRESS = wEB_ADDRESS;
	}

	public String getDB_PROTOCOL() {
		return DB_PROTOCOL;
	}

	public void setDB_PROTOCOL(String dB_PROTOCOL) {
		DB_PROTOCOL = dB_PROTOCOL;
	}

	public String getDB_DRIVER() {
		return DB_DRIVER;
	}

	public void setDB_DRIVER(String dB_DRIVER) {
		DB_DRIVER = dB_DRIVER;
	}
}
