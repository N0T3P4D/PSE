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
import org.ojim.logic.actions.ActionTransferMoneyToPartner;

public class GoField extends Field {

	public GoField(int position) {
		super("go", position);
	}
	
	public GoField(String name, int position, ServerLogic logic) {
		this(position);
		int gomoney = logic.getGameRules().getGoMoney();
		this.setPassThroughActions(ActionTransferMoneyToPartner.newTransferMoneyToBank(logic, -gomoney));
		if (logic.getGameRules().getDoubleMoneyOnGo()) {
			gomoney *= 2;
		}
		this.setExecuteActions(ActionTransferMoneyToPartner.newTransferMoneyToBank(logic, -gomoney));
	}

}
