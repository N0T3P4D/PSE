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
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.ojim.client.gui.GUISettings;
import org.ojim.language.Localizer;
import org.ojim.language.Localizer.TextKey;

public class SettingsFrame extends JFrame {

	private static final long serialVersionUID = -8959368377777373485L;
	
	private JLabel name;
	private JTextField nameField;
	private JTextField widthField;
	private JTextField heightField;
	private JTextField ipField;
	private JPanel panel;
	private JButton saveButton;
	private JLabel saveText;
	private Localizer language;
	private GUISettings settings;
	private JLabel widthLabel;
	private JLabel heightLabel;
	private JLabel ipLabel;
	
	
	private ActionListener saveListener = new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			settings.setTitle("");
			settings.setPlayerName(nameField.getText());
			settings.setWidth(Integer.parseInt(widthField.getText()));
			settings.setHeight(Integer.parseInt(heightField.getText()));
			settings.setLastIP(ipField.getText());
			settings.saveSettings();
			
		}
	};;;
	
	public SettingsFrame(Localizer language, GUISettings settings) {
		this.language = language;
		setMinimumSize(new Dimension(400, 100));
		
		this.settings = settings;
		
		name = new JLabel(this.language.getText(TextKey.PLAYER_NAME));
		nameField = new JTextField(settings.getPlayerName());
		
		widthLabel = new JLabel(this.language.getText(TextKey.WIDTH));
		widthField = new JTextField(settings.getWidth()+"");
		
		heightLabel = new JLabel(this.language.getText(TextKey.HEIGHT));
		heightField = new JTextField(settings.getHeight()+"");
		
		ipLabel = new JLabel(this.language.getText(TextKey.IP));
		ipField = new JTextField(settings.getLastIP());
		
		
		saveButton = new JButton();
		saveButton.add(saveText = new JLabel(this.language.getText(TextKey.SAVE)));
		saveButton.addActionListener(saveListener);
		nameField.setColumns(20);
		widthField.setColumns(4);
		heightField.setColumns(4);
		ipField.setColumns(15);
		panel = new JPanel();
		panel.add(name);
		panel.add(nameField);
		panel.add(widthLabel);
		panel.add(widthField);
		panel.add(heightLabel);
		panel.add(heightField);
		panel.add(ipLabel);
		panel.add(ipField);
		panel.add(saveButton);
		this.add(panel);
		
		panel.setLayout(new GridLayout(0,2));
		this.pack();
	}
	
	public void draw(){

		
		name.setText(this.language.getText(TextKey.PLAYER_NAME));
		widthLabel.setText(this.language.getText(TextKey.WIDTH));
		
		heightLabel.setText(this.language.getText(TextKey.HEIGHT));
		
		ipLabel.setText(this.language.getText(TextKey.IP));
		
		saveText.setText(this.language.getText(TextKey.SAVE));
		
	}

	public void setLanguage(Localizer language) {
		this.language = language;
		if(isVisible()){
			draw();
		}
		
	}

}
