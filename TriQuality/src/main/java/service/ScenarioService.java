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

import bean.Scenario;
import db.DB_Operations;

@ManagedBean(name = "scenarioService")
@ApplicationScoped

public class ScenarioService {

	private static String tableName = "TEST.SCENARIOS";
	// jdbc Connection
	private static Connection conn = null;
	private static Statement stmt = null;

	public static List<Scenario> listScenario() {

		List<Scenario> list = new ArrayList<Scenario>();

		try {
			stmt = conn.createStatement();
			ResultSet results = stmt.executeQuery("select * from " + tableName);
			ResultSetMetaData rsmd = results.getMetaData();
			int numberCols = rsmd.getColumnCount();

			while (results.next()) {

				list.add(new Scenario(results.getInt(1), results.getString(2), results.getString(3), results.getString(4)));

			}

			results.close();
			stmt.close();

		} catch (SQLException sqlExcept) {
			sqlExcept.printStackTrace();
		}

		return list;
	}

	public static Scenario getScenario(int scenarioId) {

		Scenario scenario = new Scenario();

		try {
			stmt = conn.createStatement();
			ResultSet results = stmt.executeQuery("select * from " + tableName + " where id = " + scenarioId);
			ResultSetMetaData rsmd = results.getMetaData();
			int numberCols = rsmd.getColumnCount();

			results.next();
			
			scenario = new Scenario(scenarioId, results.getString(2), results.getString(3), results.getString(4));

			results.close();
			stmt.close();

		} catch (SQLException sqlExcept) {
			sqlExcept.printStackTrace();
		}

		return scenario;
	}

	public static void insertScenario(Scenario scenarioBean) {

		DB_Operations dbOps = new DB_Operations();

		int scenarioId = dbOps.getId(conn, tableName);

		try {
			stmt = conn.createStatement();

			stmt.execute("insert into " + tableName + " values (" + scenarioId + ",'" + scenarioBean.getName() + "','"
					+ scenarioBean.getDescription() + "','" + scenarioBean.getScriptFile() + "')");
			stmt.close();
		} catch (SQLException sqlExcept) {
			sqlExcept.printStackTrace();
		}
	}

	public static Connection getConn() {
		return conn;
	}

	public static void setConn(Connection conn) {
		ScenarioService.conn = conn;
	}

}
