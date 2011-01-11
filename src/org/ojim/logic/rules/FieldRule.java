package org.ojim.logic.rules;

public class FieldRule {
	
	private final String name;
	private final int position;
	
	private final Action[] executeActions;
	private final Action[] passThroughActions;
	
	public FieldRule(String name, int position, Action[] executeActions, Action[] passThroughActions) {
		this.executeActions = executeActions;
		this.passThroughActions = passThroughActions;
		this.name = name;
		this.position = position;
	}
	
	public String getName() {
		return this.name;
	}
	
	public int getPosition() {
		return this.position;
	}
	
	public void execute() {
		for (int i = 0; i < this.executeActions.length; i++) {
			this.executeActions[i].execute();
		}
	}
	
	public void passThrough() {
		for (int i = 0; i < this.passThroughActions.length; i++) {
			this.passThroughActions[i].execute();
		}		
	}
}
