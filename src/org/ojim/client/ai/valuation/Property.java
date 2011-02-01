package org.ojim.client.ai.valuation;

public class Property implements Comparable<Property> {
	private int position;
	private double value;
	private int price;

	public Property(int id, double value, int price) {
		this.position = id;
		this.value = value;
		this.price = price;
	}

	@Override
	public int compareTo(Property o) {
		return (int) (o.getValue() - value);
	}

	public int getID() {
		return position;
	}

	public void setValue(double value) {
		this.value = value;
	}

	public double getValue() {
		return value;
	}
	
	public int getPrice() {
		return price;
	}

}
