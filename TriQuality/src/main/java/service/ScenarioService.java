package service;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import bean.Scenario;

@ManagedBean(name = "scenarioService")
@ApplicationScoped
public class ScenarioService {

	
	public List<Scenario> createScenarios(int size) {
		List<Scenario> list = new ArrayList<Scenario>();
		
		for (int i = 0; i < size; i++) {
			list.add(new Scenario(i,"Senaryo"+i,"Description","sah"+i));
			
		}
		
		return list;
	}
}
