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

package org.ojim.client.gui.RightBar;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.util.LinkedList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import org.ojim.client.gui.OLabel.FontLayout;
import org.ojim.language.Localizer;

public class ChatWindow extends JPanel {

	Localizer language;
	LinkedList<ChatMessage> messages = new LinkedList<ChatMessage>();
	JTextArea textArea;
	
	public ChatWindow(Localizer language) {
		super();

		this.setLayout(new GridBagLayout());

		textArea = new JTextArea();

		textArea.setEditable(false);
		textArea.add(new JLabel("bla"));
		//add(new JScrollPane(textArea));
		// textPane.append("Zeile 1\nZeile 2\nZeile3\nZeile4");
		
		

		// Zeigt die erste Zeile an,
		// indem dort der Caret positioniert wird
		// textPane.setCaretPosition(4);

		this.add(textArea, new GridBagConstraints(0, 0, 2, 1, 1.0, 1.0,
				GridBagConstraints.NORTH, GridBagConstraints.BOTH,
				new Insets(0, 0, 0, 0), 0, 0));

		
		JPanel textPanel = new JPanel();
		
		textPanel.setLayout(new GridLayout(1,0));
		
		JTextField textField = new JTextField("");
		textField.setLayout(new FontLayout());

		this.add(textPanel, new GridBagConstraints(0, 1, 2, 1, 1.0, 0.0,
				GridBagConstraints.NORTHWEST, GridBagConstraints.BOTH,
				new Insets(0, 0, 0, 0), 0, 0));

		JButton sendButton = new JButton(language.getText("send"));
		sendButton.setLayout(new FontLayout());

		textPanel.add(textField);
		
		textPanel.add(sendButton);
	}

	public void clear() {

	}

	public void write(ChatMessage chatMessage) {
		messages.add(chatMessage);
		if(chatMessage.getPrivate()){
			textArea.append(language.getText("private")+": ");
			
		}
		textArea.append(" <"+chatMessage.getPlayer()+"> "+chatMessage.getMessage()+"\n");
	}

}
