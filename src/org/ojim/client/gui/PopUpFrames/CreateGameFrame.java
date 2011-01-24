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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.ojim.client.gui.GUIClient;
import org.ojim.language.Localizer;

public class CreateGameFrame extends JFrame {
	
	private JPanel panel;
	private JButton startButton;
	private Localizer language;
	private ActionListener serverStartListener;
	private GUIClient gui;
	
	public CreateGameFrame(Localizer language, final GUIClient gui) {
		setMinimumSize(new Dimension(200, 50));
		
		this.gui = gui;
		
		serverStartListener = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				gui.startServer();
				
			}
		};
		
		
		panel = new JPanel();
		startButton = new JButton();
		startButton.add(new JLabel(language.getText("start server")));
		startButton.addActionListener(serverStartListener);
		
		panel.add(startButton);
		this.add(panel);

		this.pack();
	}
	
	public void draw(){

		remove(panel);
		
		panel = new JPanel();
		startButton = new JButton();
		startButton.add(new JLabel(language.getText("start server")));
		startButton.addActionListener(serverStartListener);
		
		panel.add(startButton);
		
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
