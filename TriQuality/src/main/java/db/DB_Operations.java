package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;
import java.util.Map.Entry;


public class DB_Operations {

	private static Statement stmt = null;

	private static String dbURL = "jdbc:derby://192.168.8.103:1527/Automation;create=true";

	static String userName = "admin";
	static String password = "admin";

	private static Connection conn = null;
	
	

	public static Connection getConn() {
		return conn;
	}

	public static void setConn(Connection conn) {
		DB_Operations.conn = conn;
	}

	public static Connection createConnection() {

		try {

			Class.forName("org.apache.derby.jdbc.ClientDriver").newInstance();

			// Get a connection
			conn = DriverManager.getConnection(dbURL);

			// check connection
			if (conn != null) {
				System.out.println("connection success");
			} else {
				System.out.println("connection failed");
			}

		} catch (Exception except) {
			except.printStackTrace();
		}
		return conn;
	}

	public static void closeConnection() {
		try {

			conn.close();

			if (conn != null) {
				DriverManager.getConnection(dbURL + ";shutdown=true");
				conn.close();
			}
		} catch (SQLException sqlExcept) {
			sqlExcept.printStackTrace();
		}

	}

	public static int getId(String tableName) {

		int newId = 0;

		try {
			stmt = conn.createStatement();
			ResultSet results = stmt.executeQuery("select max(id) from " + tableName);

			results.next();
			int maxId = results.getInt(1);
			newId = maxId + 1;

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return newId;
	}

	public static int getIdByColumn(String tableName, String columnName) {

		int newId = 0;

		try {
			stmt = conn.createStatement();
			ResultSet results = stmt.executeQuery("select max(" + columnName + ") from " + tableName);

			results.next();
			int maxId = results.getInt(1);
			newId = maxId + 1;

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return newId;
	}

	public static void delete(String tableName, int id) {
		try {
			stmt = conn.createStatement();
			stmt.execute("delete from " + tableName + " where ID=" + id + "");
			stmt.close();
		} catch (SQLException sqlExcept) {
			sqlExcept.printStackTrace();
		}
	}

	public static void update(String tableName, int id, Map<String, String> updateMap) {
		try {

			stmt = conn.createStatement();
			for (Entry<String, String> updateEntry : updateMap.entrySet()) {
				String Key = updateEntry.getKey().toUpperCase();
				String Value = updateEntry.getValue().toUpperCase();
				stmt.execute("UPDATE " + tableName + " SET " + Key + "='" + Value + "' WHERE ID=" + id + "");
			}

			stmt.close();
		} catch (SQLException sqlExcept) {
			sqlExcept.printStackTrace();
		}
	}

}
