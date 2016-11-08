package run;

import java.sql.Connection;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import bean.Input;
import bean.Scenario;
import db.DB_Operations;
import service.ExecutionService;
import service.InputService;
import service.ScenarioService;

public class AppBoot {

	public static void main(String[] args) {

		AppBoot app = new AppBoot();

		// scenario table operations
		//app.scenarioOps();

		// input table operations
		//app.inputOps();

		// execution table operations
		app.executionOps();
	}

	public void scenarioOps() {
		
		DB_Operations dbOps = new DB_Operations();
		
		ScenarioService scenarioService = new ScenarioService();

		// create Connection
		scenarioService.setConn(DB_Operations.createConnection());
		
		String name = "Login To Website";
		String description = "Login Process Scenario";
		String scriptFile = "loginScenario.sah";
		
		Scenario scenario = new Scenario();
		scenario.setName(name);
		scenario.setDescription(description);
		scenario.setScriptFile(scriptFile);
		
		// insert and execution record to apache derby db
		scenarioService.insertScenario(scenario);
		
		// list all scenarios
		List<Scenario> scenarioList = scenarioService.listScenario();
		
		// get scenario entity
		scenario = scenarioService.getScenario(1);
				
		String tableName = "TEST.SCENARIOS";
		int id = DB_Operations.getId(scenarioService.getConn(), tableName);
		//delete from last entry
		id = id - 1;
		
		Map<String, String> updateMap = new HashMap<String, String>();		
		updateMap.put("DESCRIPTION", "updateOK");
		dbOps.update(scenarioService.getConn(), tableName, id, updateMap);	
		
		dbOps.delete(scenarioService.getConn(), tableName , id);
		
	}

	public void inputOps() {
		
		List<Input> scenarioInputList = new ArrayList<>();

		InputService inputService = new InputService();
		
		DB_Operations dbOps = new DB_Operations();
		
		// create Connection
		inputService.setConn(DB_Operations.createConnection());
		
		int scenarioId = 0;
		
		String description = "Username alanını set etmek için kullanılacak bilgilerdir";
		String inputName = "$user";
		String inputValue = "triquality";
		Input input = new Input();
		input.setScenarioId(scenarioId);
		input.setInputName(inputName);
		input.setInputValue(inputValue);
		
		inputService.insertInput(input);
		
		// list scenario inputs
		scenarioInputList = inputService.listScenarioInput(scenarioId);

		
		String tableName = "TEST.INPUTS";
		int id = dbOps.getId(inputService.getConn(), tableName);
		//delete from last entry
		id = id - 1;
		
		Map<String, String> updateMap = new HashMap<String, String>();
		updateMap.put("INPUT_VALUE", "updateOK");
		dbOps.update(inputService.getConn(), tableName, id, updateMap);		
		
		dbOps.delete(inputService.getConn(), tableName , id);

	}

	public void executionOps() {
		
		List<ExecutionBean> executionListBySession = new ArrayList<>();
		
		ExecutionService executionService = new ExecutionService();
		
		DB_Operations dbOps = new DB_Operations();
		
		// create Connection
		ExecutionService.setConn(DB_Operations.createConnection());

		//ui üzerinden beslenecektir.
		int scenarioId = 2;
		//senaryo listesi için ortak kulanılacaktır
		int sessionId = 1;
		String status = "READY";
		String browserType = "CHROME";

		Date date = new Date();
		Timestamp executionTime = new Timestamp(date.getTime());

		// insert and execution record to apache derby db
		
		ExecutionBean executionBean = new ExecutionBean();
		executionBean.setScenarioId(scenarioId);
		executionBean.setSessionId(sessionId);
		executionBean.setStatus(status);
		executionBean.setBrowserType(browserType);
		executionBean.setExecutionTime(executionTime);
		
		executionService.insertExecution(executionBean);

		// list executions by sessionId
		executionListBySession = executionService.listBySession(sessionId);
		
		String tableName = "TEST.EXECUTION";
		int id = dbOps.getId(executionService.getConn(), tableName);
		//delete from last entry
		id = id - 1;
		
		Map<String, String> updateMap = new HashMap<String, String>();
		updateMap.put("STATUS", "updateOK.");
		dbOps.update(executionService.getConn(), tableName, id, updateMap);		
		
		dbOps.delete(executionService.getConn(), tableName , id);
		
	}

}
