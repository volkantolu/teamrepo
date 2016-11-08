package service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import bean.Execution;
import db.DB_Operations;


@ManagedBean(name = "executionService")
@ApplicationScoped

public class ExecutionService {

	private static String tableName = "TEST.EXECUTION";
	// jdbc Connection
	private Connection conn = null;
	private static Statement stmt = null;
	
	
	public void initService(){
		this.conn=DB_Operations.getConn();
	}

	public List<Execution> listBySession(int sessionId) {

		List<Execution> executionListBySession = new ArrayList<Execution>();

		try {
			stmt = this.conn.createStatement();
			ResultSet results = stmt.executeQuery("select * from " + tableName + " where SESSION_ID = " + sessionId);
			ResultSetMetaData rsmd = results.getMetaData();
			int numberCols = rsmd.getColumnCount();

			while (results.next()) {
				
//				private int id;
//				private int scenarioId;
//				private int sessionId;
//				private String status;
//				private String browserType;
//				private Timestamp executionTime;

				executionListBySession.add(new Execution(results.getInt(1), results.getInt(2), results.getInt(3),results.getString(4), results.getString(5), results.getTimestamp(6)));

			}

			results.close();
			stmt.close();

		} catch (SQLException sqlExcept) {
			sqlExcept.printStackTrace();
		}

		return executionListBySession;
	}

	public void insertExecution(Execution execution) {

		int executionId = DB_Operations.getId(tableName);

		try {
			stmt = this.conn.createStatement();
			stmt.execute("insert into " + tableName + " values (" + executionId + "," + execution.getScenarioId() + "," + execution.getSessionId()
					+ ",'" + execution.getStatus() + "','" + execution.getBrowserType() + "', TIMESTAMP('" + execution.getExecutionTime() + "'))");
			stmt.close();
		} catch (SQLException sqlExcept) {
			sqlExcept.printStackTrace();
		}
	}



}
