package org.ojim.server;

public class TestThread extends Thread {
	private TestClient client;
	
	public TestThread(TestClient client) {
		this.client = client;
	}
	
	public void run() {
		//Don't do nothing, stop when client is gone
		while(client != null) {
			try {
				this.wait(10000);
			} catch (InterruptedException e) {
				//not important
			}
		}
	}
}
