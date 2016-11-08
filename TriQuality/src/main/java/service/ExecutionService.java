package service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import db.DB_Operations;
import run.ExecutionBean;
import bean.Execution;

@ManagedBean(name = "executionService")
@ApplicationScoped

public class ExecutionService {

	private static String tableName = "TEST.EXECUTION";
	// jdbc Connection
	private static Connection conn = null;
	private static Statement stmt = null;

	public static List<Execution> listBySession(int sessionId) {

		List<ExecutionBean> executionListBySession = new ArrayList<>();

		try {
			stmt = conn.createStatement();
			ResultSet results = stmt.executeQuery("select * from " + tableName + " where SESSION_ID = " + sessionId);
			ResultSetMetaData rsmd = results.getMetaData();
			int numberCols = rsmd.getColumnCount();

			while (results.next()) {

				executionListBySession.add(new Execution(results.getInt(1), results.getInt(2), results.getString(3),
						results.getString(4), results.getString(5), results.getString(6)));

			}

			results.close();
			stmt.close();

		} catch (SQLException sqlExcept) {
			sqlExcept.printStackTrace();
		}

		return executionListBySession;
	}

	public static void insertExecution(Execution execution) {

		DB_Operations dbOps = new DB_Operations();

		int executionId = dbOps.getId(conn, tableName);

		try {
			stmt = conn.createStatement();
			stmt.execute("insert into " + tableName + " values (" + executionId + "," + execution.getScenarioId + "," + execution.getSessionId
					+ ",'" + execution.getStatus + "','" + execution.getBrowserType + "', TIMESTAMP('" + execution.getExecutionTime + "'))");
			stmt.close();
		} catch (SQLException sqlExcept) {
			sqlExcept.printStackTrace();
		}
	}

	public static Connection getConn() {
		return conn;
	}

	public static void setConn(Connection conn) {
		ExecutionService.conn = conn;
	}

}
