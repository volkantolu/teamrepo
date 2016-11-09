package run;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

import bean.Input;
import bean.Scenario;
import db.DB_Operations;
import service.ExecutionService;
import service.InputService;

@ManagedBean(name = "executionOperations")
@ApplicationScoped
public class ExecutionOperations {
	
//	String threads, String suiteName,
//	String browserType, String base,
//	HashMap<String, Object> variableHashMap
	private static String base = "http://sahi.co.in/demo/training/"; 
	
	@ManagedProperty("#{inputService}")
	InputService inputService;
	
	@ManagedProperty("#{executionService}")
	ExecutionService executionService;
	

	
	public void start(List<Scenario> selectedScenarios,List<String> selectedBrowsers) {
		
		String  path = getClass().getClassLoader().getResource("Scripts/").getPath();
		path=path.substring(1, path.length());
		
		int sessionId=DB_Operations.getIdByColumn("TEST.EXECUTION", "SESSION_ID");
		
		for (int i = 0; i < selectedScenarios.size(); i++) {
			
					int scenarioId=selectedScenarios.get(i).getId();
					String scriptFile=selectedScenarios.get(i).getScriptFile();
					
					for (int j = 0; j < selectedBrowsers.size(); j++) {
						
						String browser=selectedBrowsers.get(i);
						
						String suiteName=path+scriptFile;
						
						inputService.initService();
						List<Input> inputs=inputService.listScenarioInput(scenarioId);
						
						HashMap<String, Object> variableHashMap=new HashMap<String, Object>();
						
						for (int k = 0; k < inputs.size(); k++) {
							variableHashMap.put(inputs.get(i).getInputName(), inputs.get(i).getInputValue());
						}
						
						String status = new SahiOperations().sahiScriptLauncher("1", suiteName, browser, ExecutionOperations.base, variableHashMap);
						
						executionService.initService();
						int executionId=DB_Operations.getId("TEST.EXECUTION");
						executionService.insertExecution(new bean.Execution(executionId, scenarioId, sessionId, status, browser, new Timestamp(new Date().getTime())));
						
					}
			
		}

	}



	public InputService getInputService() {
		return inputService;
	}



	public void setInputService(InputService inputService) {
		this.inputService = inputService;
	}



	public ExecutionService getExecutionService() {
		return executionService;
	}



	public void setExecutionService(ExecutionService executionService) {
		this.executionService = executionService;
	}
	
	

}
