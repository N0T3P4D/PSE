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

import java.util.Map;

import org.jdom.DataConversionException;
import org.jdom.Element;
import org.ojim.logic.ServerLogic;
import org.ojim.logic.actions.ActionFactory;

public class TaxField extends Field {
	
	private final int amount;

	public TaxField(String name, int position, int amount) {
		super(name, position);
		this.amount = amount;
	}
	
	public TaxField(String name, int position, int amount, ServerLogic logic) {
		this(name, position, amount);
		this.setExecuteActions(ActionFactory.newTransferMoneyToBank(logic, amount));
	}
	
	public TaxField(Element element, Map<Integer, FieldGroup> groups, ServerLogic logic) throws DataConversionException {
		super(element, groups);
		this.amount = Integer.parseInt(element.getChildText("amount"));
		this.setExecuteActions(ActionFactory.newTransferMoneyToBank(logic, amount));
	}
	
	public int getAmount() {
		return this.amount;
	}

	@Override
	public int getColorGroup() {
		return -9;
	}
}
