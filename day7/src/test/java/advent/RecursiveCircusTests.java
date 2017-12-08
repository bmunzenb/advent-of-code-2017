package advent;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import advent.RecursiveCircus.Node;

public class RecursiveCircusTests {

	@Test
	public void bigBang() {

		Map<String, Node> nodes = new HashMap<>();

		Node pbga = RecursiveCircus.parseInto("pbga (66)", nodes);
		assertEquals("pbga", pbga.name);
		assertEquals(66, pbga.weight);
		assertEquals(1, nodes.size());
		assertTrue(nodes.containsValue(pbga));

		Node xhth = RecursiveCircus.parseInto("xhth (57)", nodes);
		@SuppressWarnings("unused")
		Node ebii = RecursiveCircus.parseInto("ebii (61)", nodes);
		@SuppressWarnings("unused")
		Node havc = RecursiveCircus.parseInto("havc (66)", nodes);
		Node ktlj = RecursiveCircus.parseInto("ktlj (57)", nodes);
		Node fwft = RecursiveCircus.parseInto("fwft (72) -> ktlj, cntj, xhth", nodes);
		@SuppressWarnings("unused")
		Node qoyq = RecursiveCircus.parseInto("qoyq (66)", nodes);
		Node padx = RecursiveCircus.parseInto("padx (45) -> pbga, havc, qoyq", nodes);
		Node tknk = RecursiveCircus.parseInto("tknk (41) -> ugml, padx, fwft", nodes);
		@SuppressWarnings("unused")
		Node jptl = RecursiveCircus.parseInto("jptl (61)", nodes);
		Node ugml = RecursiveCircus.parseInto("ugml (68) -> gyxo, ebii, jptl", nodes);
		@SuppressWarnings("unused")
		Node gyxo = RecursiveCircus.parseInto("gyxo (61)", nodes);
		Node cntj = RecursiveCircus.parseInto("cntj (57)", nodes);

		assertEquals(13, nodes.size());
		assertEquals(3, fwft.holdingUp.size());
		assertTrue(fwft.holdingUp.contains(ktlj));
		assertTrue(fwft.holdingUp.contains(cntj));
		assertTrue(fwft.holdingUp.contains(xhth));

		Node root = RecursiveCircus.findRoot(nodes.values());
		assertEquals(tknk, root);

		RecursiveCircus.setTotalWeight(root);
		assertEquals(251, ugml.totalWeight);
		assertEquals(243, padx.totalWeight);
		assertEquals(243, fwft.totalWeight);

		int unbalanced = RecursiveCircus.findUnbalanced(root);
		assertEquals(60, unbalanced);
	}
}
