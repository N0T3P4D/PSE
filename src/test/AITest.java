package test;

import org.junit.Test;
import org.ojim.client.ai.AIClient;

public class AITest {

	@Test(expected=IllegalArgumentException.class)
	public void nullServerTest() {
		new AIClient(null);
	}
}
