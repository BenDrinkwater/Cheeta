package io.nexus;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class TreeBuilderTest {

	@Test
	public void BuilderIndexTest() {
		NexusTreeBuilder builder = new NexusTreeBuilder(new NexusString(null));
		for (int i = 0; i < 9999; ++i) {
			builder.getNextNodeName();
		}
		assertEquals("InternalNode_10000", builder.getNextNodeName());
	}
	
}
