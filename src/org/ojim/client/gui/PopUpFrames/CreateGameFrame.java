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

import org.ojim.client.gui.GUIClient;
import org.ojim.language.Localizer;

public class CreateGameFrame extends JFrame {

	private JPanel panel;
	private JButton startButton;
	private JLabel startButtonlabel;
	private Localizer language;

	private JLabel nameLabel;
	private JTextField nameField;

	private JLabel playerLabel;
	private JTextField playerField;

	private JLabel kiLabel;
	private JTextField kiField;
	
	private JLabel errorLabel;
	
	private ActionListener serverStartListener;
	private GUIClient gui;
	private String wrongInput;

	public CreateGameFrame(Localizer language, final GUIClient gui) {
		setMinimumSize(new Dimension(200, 50));

		this.gui = gui;

		serverStartListener = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
				if (!nameField.getText().equals("") && Integer.parseInt(playerField.getText()) > Integer.parseInt(kiField.getText())) {
					gui.startServer(nameField.getText(),Integer.parseInt(playerField.getText()),Integer.parseInt(kiField.getText()));
				}
				} catch (NumberFormatException e2){
					errorLabel.setText(wrongInput);
				}

			}
		};

		panel = new JPanel();
		startButton = new JButton();
		
		nameLabel = new JLabel(language.getText("server name"));
		nameField = new JTextField(language.getText("Server"));
		
		playerLabel = new JLabel(language.getText("max player"));
		playerField = new JTextField(language.getText("4"));
		
		kiLabel = new JLabel(language.getText("ki player"));
		kiField = new JTextField(language.getText("3"));
		
		errorLabel = new JLabel();
		
		startButton.add(startButtonlabel = new JLabel(language
				.getText("start server")));
		startButton.addActionListener(serverStartListener);

		panel.add(nameLabel);
		panel.add(nameField);
		panel.add(playerLabel);
		panel.add(playerField);
		panel.add(kiLabel);
		panel.add(kiField);
		panel.add(startButton);
		panel.add(errorLabel);
		panel.setLayout(new GridLayout(0, 2));
		this.add(panel);

		this.pack();
	}

	public void draw() {

		nameLabel.setText(language.getText("server name"));		
		playerLabel.setText(language.getText("max player"));
		kiLabel.setText(language.getText("ki player"));
		startButtonlabel.setText(language.getText("start server"));
		wrongInput = language.getText("wrong input");
	}

	public void setLanguage(Localizer language) {
		this.language = language;
		if (isVisible()) {
			draw();
		}

	}
}
