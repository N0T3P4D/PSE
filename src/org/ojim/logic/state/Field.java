package org.ojim.logic.state;

import org.ojim.logic.rules.FieldRule;

public class Field {
	
	private FieldRule rule;
	protected GameState state;
	
	public Field(GameState state) {
		this.state = state;
	}
	
	protected void setRule(FieldRule rule) {
		this.rule = rule;
	}
	
	public FieldRule getRule() {
		return this.rule;
	}	
}
