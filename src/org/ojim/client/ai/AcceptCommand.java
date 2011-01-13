package org.ojim.client.ai;


public class AcceptCommand implements Command {

	private AIClient client;
	
	public AcceptCommand (AIClient client) {
		this.client = client;
	}
	
	@Override
	public void execute() {
		client.accept();		
	}

}
