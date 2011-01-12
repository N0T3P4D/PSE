/*  Copyright (C) 2010  Fabian Neundorf, Philip Caroli, Maximilian Madlung, 
 * 						Usman Ghani Ahmed, Jeremias Mechler
 *
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *  
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *  
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

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
