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

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import org.ojim.client.gui.PlayerColor;
import org.ojim.client.gui.StreetColor;
import org.ojim.client.gui.OLabel.FontLayout;
import org.ojim.logic.state.Player;
import org.ojim.logic.state.fields.BuyableField;
import org.ojim.logic.state.fields.Field;
import org.ojim.logic.state.fields.Street;

public class GameFieldPiece extends JPanel {

	private Field field;
	private JPanel dataPanel;
	private JLabel price;

	private JPanel playersPanel;
	private List<Player> players;

	public GameFieldPiece(Field field) {
		this.field = field;
		this.players = new ArrayList<Player>();

		// Field is known, now initialize all Data
		this.initialize();
		// this.draw();
	}

	public void initialize() {
		// Clear all before
		this.removeAll();
		
		this.dataPanel = new JPanel(new GridLayout(0, 1));
		
		this.playersPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		this.playersPanel.setOpaque(false);
		this.dataPanel.add(this.playersPanel);
		
		Player owner = null;
		
		if (this.field instanceof BuyableField) {
			owner = ((BuyableField) field).getOwner();
			if (this.field instanceof Street) {
				JPanel colorBar = new JPanel();
				colorBar.setBackground(StreetColor.getBackGroundColor(this.field.getColorGroup()));
				//TODO: (xZise) Border finden, der nur unten gezeichnet wird.
				colorBar.setBorder(new LineBorder(Color.black, 1));
				this.add(colorBar);

				JLabel group = new JLabel("<html>" + ((Street) field).getFieldGroup().getName());

				group.setLayout(new FontLayout());
				group.setHorizontalTextPosition(JLabel.CENTER);
				this.setColor(group, owner);
				this.dataPanel.add(group);
			}
			
			this.price = new JLabel();
			this.price.setHorizontalTextPosition(JLabel.CENTER);
			this.price.setVerticalTextPosition(JLabel.BOTTOM);
			this.price.setLayout(new FontLayout());
			this.setColor(this.price, owner);
		}
		
		JLabel name = new JLabel("<html>" + field.getName());
		name.setHorizontalTextPosition(JLabel.CENTER);
		name.setLayout(new FontLayout());
		this.setColor(name, owner);
		this.dataPanel.add(name);
		
		if (this.price != null) {
			this.dataPanel.add(this.price);
		}
		this.add(dataPanel);
		this.setLayout(new GameFieldPieceLayout());
		this.update();
		this.setVisible(true);
	}

	/**
	 * Sets the color specifications for a label. If Owner is null it uses the default colors.
	 * @param label The label holding the data.
	 * @param owner The owner specifying the color.
	 */
	private void setColor(JLabel label, Player owner) {
		int color = -1;
		if (owner != null) {
			color = owner.getColor();
		}

		label.setBackground(PlayerColor.getBackGroundColor(color));

		label.setForeground(PlayerColor.getFontColor(color));
	}
	
	/**
	 * Updates all volatile data.
	 */
	public void update() {
		if (this.field instanceof BuyableField) {
			Player owner = ((BuyableField) this.field).getOwner();
			if (owner != null) {
				this.price.setText("<html>" + owner.getName() + " (" + ((BuyableField) field).getPrice() + ")");
			} else {
				this.price.setText("<html>" + ((BuyableField) field).getPrice()); 
			}
			this.dataPanel.setBackground(PlayerColor.getBackGroundColor(owner == null ? -1 : owner.getColor()));
		}
	}
	
	private void updatePlayers() {
		this.playersPanel.removeAll();
		for (Player player : this.players) {
			JPanel playerBox = new JPanel();
			playerBox.setBackground(PlayerColor.getBackGroundColor(player.getColor()));
			playerBox.setBorder(new LineBorder(Color.black, 1));
			this.playersPanel.add(playerBox);
		}
	}

	public void addPlayer(Player player) {
		if (!this.players.contains(player)) {
			this.players.add(player);
			this.updatePlayers();
		}
	}

	public void removePlayer(Player player) {
		if (this.players.remove(player)) {
			this.updatePlayers();
		}
	}
	
	public void removePlayers() {
		this.players.clear();
		this.updatePlayers();
	}
}
