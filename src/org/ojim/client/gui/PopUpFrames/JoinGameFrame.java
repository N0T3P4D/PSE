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

public class JoinGameFrame extends JFrame {

	private JLabel ip;
	private JTextField ipField;
	private JPanel panel;
	private JButton joinButton;
	private Localizer language;
	
	private Window windowStatus;
	
	enum Window {
		directConnection, serverList
	}
	
	public JoinGameFrame(Localizer language) {
		this.language = language;
		setMinimumSize(new Dimension(400, 100));
		
		ip = new JLabel(this.language.getText("IP"));
		ipField = new JTextField();
		joinButton = new JButton();
		joinButton.add(new JLabel(this.language.getText("join")));
		ipField.setColumns(20);
		panel = new JPanel();

		panel.add(ip);
		panel.add(ipField);
		panel.add(joinButton);
		
		this.pack();
	}

	public void showDirectConnection() {
		
		
		ip = new JLabel(this.language.getText("ip"));
		ipField = new JTextField();
		joinButton = new JButton();
		joinButton.add(new JLabel(this.language.getText("join")));
		ipField.setColumns(20);
		panel = new JPanel();
		
		panel.add(ip);
		panel.add(ipField);
		panel.add(joinButton);
		
		showJoin();

	}

	public void showServerList() {
		ip = new JLabel(this.language.getText("serverListText"));

		panel.add(ip);
	}

	public void showJoin() {
		
		remove(panel);
		
		this.add(panel);
		this.repaint();
		setVisible(true);

	}

	public void setLanguage(Localizer language) {
		this.language = language;
		if(isVisible()){
			showJoin();
		}
		
	}

}
