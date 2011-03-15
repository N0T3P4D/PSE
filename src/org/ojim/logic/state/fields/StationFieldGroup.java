/*  Copyright (C) 2010 - 2011  Fabian Neundorf, Philip Caroli,
 *  Maximilian Madlung, Usman Ghani Ahmed, Jeremias Mechler
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

public class StationFieldGroup extends FieldGroup {

	private int[] rent;
	
	public StationFieldGroup() {
		super(FieldGroup.STATIONS);
	}
	
	/**
	 * Sets the rent levels. The number of rents has to be the same as the number of fields.
	 * @param rent The rent levels.
	 * @throws IllegalArgumentException If not enough/to much rents are set.
	 */
	public void setRent(int[] rent) throws IllegalArgumentException {
		if (rent.length != this.getFields().length) {
			throw new IllegalArgumentException("The number of rents differs from the number of fields.");
		}
		this.rent = rent;
	}

	public int getRent(int ownerOwns) {
		return this.rent[ownerOwns - 1];
	}

}
