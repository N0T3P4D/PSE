package org.ojim.logic.state;

import java.util.LinkedList;

public class FieldGroup {
	
	private int color;
	private LinkedList<BuyableField> fields;
	
	public FieldGroup(int color) {
		this.color = color;
		fields = new LinkedList<BuyableField>();
	}
	
	public void addField(BuyableField field) {
		fields.add(field);
	}
	
	public LinkedList<BuyableField> getFields() {
		return this.fields;
	}
}
