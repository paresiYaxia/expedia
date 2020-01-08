package utilities.dataRelated;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import utilities.reportRelated.Log4jManager;

public class JDBCManager {
	// Constant for Database URL
	// URL Syntax if using mysql: "jdbc:mysql://ipaddress:portnumber/db_name"
	public static String DB_URL = "jdbc:sqlite:studentDB.db";                    // this part is important need to change in DBTestingExam

	// Constant for Database User name
	public static String DB_USER = "root";
	// Constant for Database Password
	public static String DB_PASSWORD = "root";

	// Connection object
	static Connection con = null;
	// Statement object
	private static Statement stmt;

	private static void setUp() throws Exception {

		try {
			// Make the database connection.Use Class.forName("com.mysql.jdbc.Driver") if
			// using mySQL
			Class.forName("org.sqlite.JDBC");
			Log4jManager.info("Connecting to Database...");

			// Get connection to DB
			con = DriverManager.getConnection(DB_URL);
			if (con != null) {
				Log4jManager.info("Connected to the Database...");
			} else {
				Log4jManager.info("Failed to connect to the Database...");
			}

			// Statement object to send the SQL statement to the Database
			stmt = con.createStatement();

		} catch (SQLException e) {
			Log4jManager.error("SQLException, please check database connection information");
		}

	}

	public static ArrayList<String> getDataList(String query, int startCol, int endCol) throws Exception {
		setUp();

		ArrayList<String> alist = new ArrayList<String>();

		try {

			// Get the contents of user info table from DB
			ResultSet res = stmt.executeQuery(query);

			// res.next() returns true if there is any next record else returns false
			while (res.next()) {
				for (int i = startCol; i <= endCol; i++) {
					alist.add(res.getString(i).toString());
				}

			}
			Log4jManager.debug(">>> retrieved data list from Database: " + alist);
			// System.out.println(alist);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		tearDown();
		return alist;
	}

	public static String getData(String query, String columnLabel) throws Exception {
		setUp();
		String data = null;

		try {

			// Get the contents of user info table from DB
			ResultSet res = stmt.executeQuery(query);

			// res.next() returns true if there is any next record else returns false
			while (res.next()) {
				data = res.getString(columnLabel).toString();
			}
			Log4jManager.debug(">>> retrieved Data Cell from Database: " + data);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		tearDown();
		return data;
	}

	private static void tearDown() throws Exception {
		// Close DB connection
		if (con != null) {
			try {
				Log4jManager.info("Closing Database Connection...");
				con.close();
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}
	}
}
