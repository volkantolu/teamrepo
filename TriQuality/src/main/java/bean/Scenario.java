package bean;

import java.io.Serializable;


public class Scenario implements Serializable{
	
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
private int id;
private String name;
private String description;
private String scriptFile;

public Scenario() {}

public Scenario(int id, String name, String description, String scriptFile) {
    this.id = id;
    this.name = name;
    this.description = description;
    this.scriptFile = scriptFile;
}


public int getId() {
	return id;
}

public void setId(int id) {
	this.id = id;
}

public String getName() {
	return name;
}

public void setName(String name) {
	this.name = name;
}

public String getDescription() {
	return description;
}

public void setDescription(String description) {
	this.description = description;
}

public String getScriptFile() {
	return scriptFile;
}

public void setScriptFile(String scriptFile) {
	this.scriptFile = scriptFile;
}





}
