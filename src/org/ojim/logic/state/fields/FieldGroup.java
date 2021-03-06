/*  Copyright (C) 2010 - 2011  Fabian Neundorf, Philip Caroli,
 *  Maximilian Madlung,	Usman Ghani Ahmed, Jeremias Mechler
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

package org.ojim.logic.state.fields;

import java.util.LinkedList;

public class FieldGroup {
	
	public static final int GO = -1;
	public static final int JAIL = -2;
	public static final int FREE_PARKING = -3;
	public static final int GO_TO_JAIL = -4;
	public static final int EVENT = -5;
	public static final int COMMUNITY = -6;
	public static final int STATIONS = -7;
	public static final int INFRASTRUCTURE = -8;
	public static final int TAX = -9;
	
	private int color;
	private LinkedList<Field> fields;
	
	public FieldGroup(int color) {
		this.color = color;
		fields = new LinkedList<Field>();
	}
	
	public Field addField(Field field) {
		if (this.getClass().equals(field.getFieldGroupClass())) {
			this.fields.add(field);
			field.setFieldGroup(this);
			return field;
		} else {
			throw new IllegalArgumentException("Wrong field group class (Required: " + this.getClass() + ", Got: " + field.getFieldGroupClass() + ")");
		}
	}
	
	public Field[] getFields() {
		return this.fields.toArray(new Field[0]);
	}

	public int getColor() {
		return this.color;
	}
}
