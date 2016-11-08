package view;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import bean.Execution;
import db.DB_Operations;
import service.ExecutionService;


@ManagedBean
@ViewScoped
public class ReportView {

	private List<Execution> executionList;
	
	 @ManagedProperty("#{executionService}")
	 private ExecutionService executionService;	
	 
	 
	 @PostConstruct
		public void init() {
		 
		 executionService.initService();
		 int sessionId=DB_Operations.getIdByColumn("TEST.EXECUTION", "SESSION_ID");
		 executionList= executionService.listBySession(sessionId-1);
	 
	 }




	public List<Execution> getExecutionList() {
		return executionList;
	}


	public void setExecutionList(List<Execution> executionList) {
		this.executionList = executionList;
	}


	public ExecutionService getExecutionService() {
		return executionService;
	}


	public void setExecutionService(ExecutionService executionService) {
		this.executionService = executionService;
	}
	 
	
	
	 
	 
	
}
