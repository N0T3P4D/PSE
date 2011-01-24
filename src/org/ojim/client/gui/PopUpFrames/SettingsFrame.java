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

package org.ojim.client.gui.PopUpFrames;

import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.ojim.language.Localizer;

public class SettingsFrame extends JFrame {

	JLabel name;
	JTextField nameField;
	JPanel panel;
	JButton saveButton;
	Localizer language;
	
	public SettingsFrame(Localizer language) {
		this.language = language;
		setMinimumSize(new Dimension(400, 100));
		
		name = new JLabel(this.language.getText("player name"));
		nameField = new JTextField();
		saveButton = new JButton();
		saveButton.add(new JLabel(this.language.getText("save")));
		nameField.setColumns(20);
		panel = new JPanel();
		
		this.pack();
	}
	
	public void draw(){

		remove(panel);
		
		panel = new JPanel();
		name = new JLabel(this.language.getText("player name"));
		saveButton = new JButton();
		saveButton.add(new JLabel(this.language.getText("save")));
		panel.add(name);
		panel.add(nameField);
		panel.add(saveButton);
		
		this.add(panel);
		this.repaint();
		setVisible(true);
	}

	public void setLanguage(Localizer language) {
		this.language = language;
		if(isVisible()){
			draw();
		}
		
	}

}
