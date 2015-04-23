package jdbc;

public class DatabaseSettings {

	/**
	 * Username for access to Database
	 */
	private static final String DB_USERNAME = "40142115";
	//private static final String DB_USERNAME = "javateers";
	
	/**
	 * Use if Username is part of the Databases URL, otherwise leave blank
	 */
	private static final String URL_USERNAME = "40142115";
	//private static final String URL_USERNAME = "hospitaldb";
	
	/**
	 * Password for access to Database
	 */
	private static final String DB_PASSWORD = "YCR2335";
	//private static final String DB_PASSWORD = "123456";
	
	/**
	 * Main body of URL of Database, eg //web2.eeecs.qub.ac.uk/ or
	 * thin:@db.yale.edu:univdb
	 */
	private static final String WEB_ADDRESS = "//web2.eeecs.qub.ac.uk/";
	//private static final String WEB_ADDRESS = "//127.0.0.1/";

	/**
	 * Type of database, eg mysql: or oracle: etc
	 */
	private static final String DB_PROTOCOL = "mysql:";

	/**
	 * name of Database driver to be called, for mysql com.mysql.jdbc.Driver or
	 * for oracle oracle.jdbc.driver.OracleDriver
	 */
	private static final String DB_DRIVER = "com.mysql.jdbc.Driver";

	public DatabaseSettings() {
	}
	
	public String getDB_USERNAME() {
		return DB_USERNAME;
	}

	public String getURL_USERNAME() {
		return URL_USERNAME;
	}

	public String getDB_PASSWORD() {
		return DB_PASSWORD;
	}

	public String getWEB_ADDRESS() {
		return WEB_ADDRESS;
	}

	public String getDB_PROTOCOL() {
		return DB_PROTOCOL;
	}

	public String getDB_DRIVER() {
		return DB_DRIVER;
	}


}
