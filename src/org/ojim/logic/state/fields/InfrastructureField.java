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
import org.ojim.logic.state.DiceSet;

public class InfrastructureField extends BuyableField {

	private DiceSet dices;
	
	public InfrastructureField(String name, int position, int price) {
		super(name, position, price);
	}
	
	public InfrastructureField(String name, int position, int price, ServerLogic logic) {
		super(name, position, price, logic);
		this.dices = logic.getGameState().getDices();
	}
	
	public Class<? extends FieldGroup> getFieldGroupClass() {
		return InfrastructureFieldGroup.class;
	}
	
	@Override
	public int getRent() {
		// Calculate here

		assert this.getFieldGroup() != null;
		
		int ownerOwns = 0;
		for (Field field : this.getFieldGroup().getFields()) {
			if (field instanceof InfrastructureField && ((BuyableField)field).getOwner() != null && ((InfrastructureField) field).getOwner().equals(
						this.getOwner())) {
				ownerOwns++;
			}
		}	
		
		return ((InfrastructureFieldGroup) this.getFieldGroup()).getFactor(ownerOwns) * dices.getResultSum();
	}
}
