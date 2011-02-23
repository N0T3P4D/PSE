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

import org.ojim.logic.ServerLogic;
import org.ojim.logic.state.Player;
import org.ojim.logic.state.fields.BuyableField;

public class Station extends BuyableField {

	public Station(String name, int position, int price) {
		super(name, position, price);
	}
	
	public Station(String name, int position, int price, ServerLogic logic) {
		super(name, position, price, logic);
	}
	
	@Override
	public Class<? extends FieldGroup> getFieldGroupClass() {
		return StationFieldGroup.class;
	}
	
	@Override
	public int getRent() {
		int ownerOwns = 0;
		for (Field field : this.getFieldGroup().getFields()) {
			Player otherOwner = ((Station) field).getOwner();
			if (field instanceof Station && (otherOwner != null) && ((Station) field).getOwner().equals(this.getOwner())) {
				ownerOwns++;	
			}
		}
		
		return ((StationFieldGroup) this.getFieldGroup()).getRent(ownerOwns);
	}
}
