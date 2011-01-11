package org.ojim.logic.state;

import java.util.LinkedList;

public class FieldGroup {
	
	private int color;
	private LinkedList<BuyableField> fields;
	private int housePrice;
	
	public FieldGroup(int color) {
		this.color = color;
		fields = new LinkedList<BuyableField>();
	}
	
	public FieldGroup(int color, int housePrice) {
		this.color = color;
		fields = new LinkedList<BuyableField>();
		this.housePrice = housePrice;
	}
	
	public int getHousePrice() {
		return this.housePrice;
	}
	
	public void addField(BuyableField field) {
		fields.add(field);
	}
	
	public LinkedList<BuyableField> getFields() {
		return this.fields;
	}

	public int getColor() {
		return this.color;
	}
}
