package eu.learnpad.simulator.mon.event;

import java.util.Vector;

import eu.learnpad.simulator.mon.coverage.Learner;
import eu.learnpad.simulator.mon.event.GlimpseBaseEventGeneric;

public class GlimpseBaseEventBPMN<T> extends GlimpseBaseEventGeneric<String> {

	private static final long serialVersionUID = 1L;
	public String sessionID;
	public Vector<Learner> usersInvolved = new Vector<Learner>();
	public String taskID;
	public String subProcessID;
	public String desideredCompletionTime;
	
	public GlimpseBaseEventBPMN(
			String data, String probeID, Long timeStamp,
			String eventName, boolean isException, String extraDataField, 
			String sessionID, Vector<Learner> usersInvolved, String taskID,
			String subProcessID, String desideredCompletionTime) {
		
		super(data, probeID, timeStamp, eventName, isException, extraDataField);
		
		this.sessionID = sessionID;
		this.usersInvolved = usersInvolved;		
		this.taskID = taskID;
		this.subProcessID = subProcessID;
		this.desideredCompletionTime = desideredCompletionTime;
	}
	
	public String getDesideredCompletionTime() {
		return this.desideredCompletionTime;
	}
	
	public void setDesideredCompletionTimeID(String desideredCompletionTime) {
		this.desideredCompletionTime = desideredCompletionTime;
	}
	
	public String getSessionID() {
		return this.sessionID;
	}
	
	public void setSessionID(String sessionID) {
		this.sessionID = sessionID;
	}
	
	public Vector<Learner> getUsersInvolved() {
		return this.usersInvolved;
	}
	
	public void setUsersInvolved(Vector<Learner> usersInvolved) {
		this.usersInvolved = usersInvolved;
	}
	
	public String getTaskID() {
		return this.taskID;
	}
	
	public void setTaskID(String taskID) {
		this.taskID = taskID;
	}	
	public String getSubProcessID() {
		return this.subProcessID;
	}
	
	public void setSubProcessID(String subProcessID) {
		this.subProcessID = subProcessID;
	}
}