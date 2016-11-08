package bean;

import java.sql.Timestamp;

public class Execution {

	private int id;
	private int scenarioId;
	private int sessionId;
	private String status;
	private String browserType;
	private Timestamp executionTime;

	public Execution() {

	}

	public Execution(int id, int scenarioId, int sessionId, String status, String browserType, Timestamp executionTime) {
		this.id = id;
		this.scenarioId = scenarioId;
		this.sessionId = sessionId;
		this.status = status;
		this.browserType = browserType;
		this.executionTime = executionTime;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getScenarioId() {
		return scenarioId;
	}

	public void setScenarioId(int scenarioId) {
		this.scenarioId = scenarioId;
	}

	public int getSessionId() {
		return sessionId;
	}

	public void setSessionId(int sessionId) {
		this.sessionId = sessionId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getBrowserType() {
		return browserType;
	}

	public void setBrowserType(String browserType) {
		this.browserType = browserType;
	}

	public Timestamp getExecutionTime() {
		return executionTime;
	}

	public void setExecutionTime(Timestamp executionTime) {
		this.executionTime = executionTime;
	}

}
