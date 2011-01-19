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

import org.ojim.logic.state.fields.BuyableField;

public class Station extends BuyableField {

	public Station(String name, int position, int price) {
		//TODO: (xZise) Soll der Preis konstant bleiben?
		super(name, position, 1000);
	}
	
	@Override
	public int getRent() {
		int ownerOwns = 0;
		for (Field field : this.getFieldGroup().getFields()) {
			if (field instanceof Station && ((Station) field).getOwner().equals(this.getOwner())) {
				ownerOwns++;	
			}
		}
		
		//TODO: (xZise) Sind die Werte so gut? Also 500 für einen bahnhof, 1000 für 2, 2000 für 3 ...?
		return (int) Math.floor(500 * Math.pow(ownerOwns - 1, 2));
	}

}
