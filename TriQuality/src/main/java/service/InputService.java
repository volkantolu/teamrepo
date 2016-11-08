package service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import bean.Input;
import db.DB_Operations;

@ManagedBean(name = "inputService")
@ApplicationScoped

public class InputService {

	private static String tableName = "TEST.INPUTS";
	// jdbc Connection
	private static Connection conn = null;
	private static Statement stmt = null;

	public static List<Input> listScenarioInput(int scenarioId) {

		List<Input> inputList = new ArrayList<Input>();

		try {
			stmt = conn.createStatement();
			ResultSet results = stmt.executeQuery("select * from " + tableName + " where SCENARIO_ID = " + scenarioId);
			ResultSetMetaData rsmd = results.getMetaData();
			int numberCols = rsmd.getColumnCount();

			while (results.next()) {
				for (int i = 3; i <= numberCols; i++) {
					inputList.add(new Input(results.getInt(1), results.getInt(2), results.getString(3),
							results.getString(4), results.getString(5)));
				}
			}

			results.close();
			stmt.close();

		} catch (SQLException sqlExcept) {
			sqlExcept.printStackTrace();
		}

		return inputList;
	}

	public static void insertInput(Input input) {

		DB_Operations dbOps = new DB_Operations();

		int inputId = dbOps.getId(conn, tableName);

		try {
			stmt = conn.createStatement();
			stmt.execute("insert into " + tableName + " values (" + inputId + "," + input.getScenarioId() + ",'"
					+ input.getDescription() + "','" + input.getInputName() + "','" + input.getInputValue() + "')");
			stmt.close();
		} catch (SQLException sqlExcept) {
			sqlExcept.printStackTrace();
		}
	}

	public static Connection getConn() {
		return conn;
	}

	public static void setConn(Connection conn) {
		InputService.conn = conn;
	}

}
