package bean;

import java.io.Serializable;

public class Input implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private int scenarioId;
	private String description;
	private String inputName;
	private String inputValue;
	
	public Input(){
		
	}
	

	public Input(int id, int scenarioId, String description, String inputName, String inputValue) {
		this.id = id;
		this.scenarioId = scenarioId;
		this.description = description;
		this.inputName = inputName;
		this.inputValue = inputValue;
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


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public String getInputName() {
		return inputName;
	}


	public void setInputName(String inputName) {
		this.inputName = inputName;
	}


	public String getInputValue() {
		return inputValue;
	}


	public void setInputValue(String inputValue) {
		this.inputValue = inputValue;
	}

	
	
}
