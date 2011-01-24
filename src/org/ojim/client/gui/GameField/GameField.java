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

package org.ojim.client.gui.GameField;

import javax.swing.JPanel;

import org.ojim.client.gui.StreetColor;
import org.ojim.logic.state.GameState;
import org.ojim.logic.state.Player;
import org.ojim.logic.state.fields.Field;

public class GameField extends JPanel {

	
	GameFieldPiece[] fields;
	int fieldsAmount;
	
	public GameField() {
		

	}

	// Hält GameFieldPieceCollection
	// Hält Referenz auf GameFieldPiece
	InteractionPopup interactionPopup;

	public void buildOnStreet(Field field) {
		// TODO Auto-generated method stub
		
	}

	public void playerBuysField(Player player, Field field) {
		// TODO Auto-generated method stub
		
	}

	public void destroyOnStreet(Field field) {
		// TODO Auto-generated method stub
		
	}

	public void switchFieldStatus(Field field) {
		// TODO Auto-generated method stub
		
	}

	public void playerMoves(Field field, Player player) {
		// TODO Auto-generated method stub
	}
	
	public void init(int fieldsAmount, GameState gameState){
		this.fieldsAmount = fieldsAmount;
		fields = new GameFieldPiece[fieldsAmount];
		for(int i = 0; i < fieldsAmount; i++){
			fields[i] = new GameFieldPiece(gameState.getFieldAt(i));
			//fields[i].setField(gameState.getFieldAt(i));
		}
	}
	
	public void draw(){
		
		this.setLayout(new GameFieldLayout());
		

		JPanel actualLabel = new JPanel();

		actualLabel.setBackground(StreetColor.getBackGroundColor(2));
		
		actualLabel.setName(-1+"");
		this.add(actualLabel);
		
		for(int i = 0; i<fieldsAmount; i++){
			actualLabel = fields[i];
			/*
			if(i%2 == 0){
				actualLabel.setBackground(StreetColor.getBackGroundColor(0));
			} else {
				actualLabel.setBackground(StreetColor.getBackGroundColor(1));				
			}*/
			actualLabel.setName(i+"");
			this.add(actualLabel);
		}
	}
	
}
