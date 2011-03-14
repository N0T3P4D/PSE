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

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.ojim.client.gui.GUIClient;
import org.ojim.language.Localizer;
import org.ojim.language.Localizer.TextKey;
import org.ojim.logic.rules.GameRules;

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

	private JLabel hostLabel;
	private JTextField hostField;

	private JLabel errorLabel;

	private ActionListener serverStartListener;
	private GUIClient gui;
	private String wrongInput;

	public CreateGameFrame(Localizer language, final GUIClient gui) {
		ImageIcon icon = new ImageIcon("icons/g4468.png");
		this.setIconImage(icon.getImage());
		setMinimumSize(new Dimension(200, 50));

		this.gui = gui;

		serverStartListener = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					if (!nameField.getText().equals("")
							&& Integer.parseInt(playerField.getText()) > Integer
									.parseInt(kiField.getText()) && Integer.parseInt(playerField.getText()) < 9) {
						gui.startServer(nameField.getText(), Integer
								.parseInt(playerField.getText()), Integer
								.parseInt(kiField.getText()),
								hostField.getText());
					}
				} catch (NumberFormatException e2) {
					errorLabel.setText(wrongInput);
				}

			}
		};

		panel = new JPanel();
		startButton = new JButton();

		nameLabel = new JLabel(language.getText(TextKey.SERVER_NAME));
		nameField = new JTextField("Server");

		playerLabel = new JLabel(language.getText(TextKey.MAX_PLAYER));
		playerField = new JTextField("4");

		kiLabel = new JLabel(language.getText(TextKey.AI_PLAYER));
		kiField = new JTextField("3");
		
		hostLabel = new JLabel(language.getText(TextKey.HOST));
		hostField = new JTextField("");

		errorLabel = new JLabel();

		startButton.add(startButtonlabel = new JLabel(language
				.getText(TextKey.START_SERVER)));
		startButton.addActionListener(serverStartListener);

		panel.add(nameLabel);
		panel.add(nameField);
		panel.add(playerLabel);
		panel.add(playerField);
		panel.add(kiLabel);
		panel.add(kiField);
		panel.add(hostLabel);
		panel.add(hostField);
		panel.add(startButton);
		panel.add(errorLabel);
		panel.setLayout(new GridLayout(0, 2));
		this.add(panel);

		this.pack();
	}

	public void draw() {

		nameLabel.setText(language.getText(TextKey.SERVER_NAME));
		playerLabel.setText(language.getText(TextKey.MAX_PLAYER));
		kiLabel.setText(language.getText(TextKey.AI_PLAYER));
		hostLabel.setText(language.getText(TextKey.HOST));
		startButtonlabel.setText(language.getText(TextKey.START_SERVER));
		wrongInput = language.getText(TextKey.WRONG_INPUT);
	}

	public void setLanguage(Localizer language) {
		this.language = language;
		if (isVisible()) {
			draw();
		}

	}
}
