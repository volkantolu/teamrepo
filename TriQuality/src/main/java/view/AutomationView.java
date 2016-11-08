package view;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import bean.Scenario;
import db.DB_Operations;
import service.ScenarioService;

@ManagedBean
@ViewScoped
public class AutomationView {

	 @ManagedProperty("#{scenarioService}")
	 private ScenarioService scenarioService;
	
	
	private List<Scenario> allScenarios;
    private List<Scenario> selectedScenarios;
    private List<String> selectedBrowsers;
    private boolean selectedChrome;
    private boolean selectedIE;
    private boolean selectedFireFox;
	
	@PostConstruct
	public void init() {
		//System.out.println("Ýçeri girdi");
		DB_Operations.createConnection();
		scenarioService.initService();
		allScenarios=scenarioService.listScenario();
		selectedChrome = false;
		selectedIE = false;
		selectedFireFox = false;
		selectedBrowsers=new ArrayList<String>();
	}

	
	
	public void invokeAutomation(){
		
		
		if(selectedChrome)
		{
			if(!selectedBrowsers.contains("chrome"))
			 {selectedBrowsers.add("chrome");}
		}
		else
		{
			if(selectedBrowsers.contains("chrome"))
			{selectedBrowsers.remove("chrome");}
		}
		if(selectedIE)
		{
			if(!selectedBrowsers.contains("ie"))
			 {selectedBrowsers.add("ie");}
		}
		else
		{
			if(selectedBrowsers.contains("ie"))
			{selectedBrowsers.remove("ie");}
		}
		if(selectedFireFox)
		{
			if(!selectedBrowsers.contains("firefox"))
			 {selectedBrowsers.add("firefox");}
		}
		else
		{
			if(selectedBrowsers.contains("firefox"))
			{selectedBrowsers.remove("firefox");}
		}
		
		
		for (int i = 0; i <selectedBrowsers.size(); i++) {
			System.out.println(selectedBrowsers.get(i));
		}
		
		for (int i = 0; i <selectedScenarios.size(); i++) {
			System.out.println(selectedScenarios.get(i).getId());
		}
		
	}
	
	
	public boolean getSelectedChrome() {
		return selectedChrome;
	}



	public void setSelectedChrome(boolean selectedChrome) {
		this.selectedChrome = selectedChrome;
	}



	public boolean getSelectedIE() {
		return selectedIE;
	}



	public void setSelectedIE(boolean selectedIE) {
		this.selectedIE = selectedIE;
	}



	public boolean getSelectedFireFox() {
		return selectedFireFox;
	}



	public void setSelectedFireFox(boolean selectedFireFox) {
		this.selectedFireFox = selectedFireFox;
	}



	public List<String> getSelectedBrowsers() {
		return selectedBrowsers;
	}

	public void setSelectedBrowsers(List<String> selectedBrowsers) {
		this.selectedBrowsers = selectedBrowsers;
	}



	public List<Scenario> getAllScenarios() {
		return allScenarios;
	}

	public void setAllScenarios(List<Scenario> allScenarios) {
		this.allScenarios = allScenarios;
	}

	public List<Scenario> getSelectedScenarios() {
		return selectedScenarios;
	}

	public void setSelectedScenarios(List<Scenario> selectedScenarios) {
		this.selectedScenarios = selectedScenarios;
	}

	public ScenarioService getScenarioService() {
		return scenarioService;
	}

	public void setScenarioService(ScenarioService scenarioService) {
		this.scenarioService = scenarioService;
	}
	
	

}
