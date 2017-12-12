package advent;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import advent.DigitalPlumber.Node;

public class DigitalPlumberTests {

	@SuppressWarnings("unused")
	@Test
	public void solve() {

		Map<Integer, Node> nodes = new HashMap<>();

		Node node0 = DigitalPlumber.parse("0 <-> 2", nodes);
		assertEquals(0, node0.id);
		assertEquals(1, node0.connected.size());
		assertEquals(2, node0.connected.stream().findFirst().get().id);

		Node node1 = DigitalPlumber.parse("1 <-> 1", nodes);

		Node node2 = DigitalPlumber.parse("2 <-> 0, 3, 4", nodes);
		assertEquals(2, node2.id);
		assertEquals(node0.connected.stream().findFirst().get(), node2);
		assertEquals(3, node2.connected.size());

		Node node3 = DigitalPlumber.parse("3 <-> 2, 4", nodes);
		Node node4 = DigitalPlumber.parse("5 <-> 6", nodes);
		Node node5 = DigitalPlumber.parse("4 <-> 2, 3, 6", nodes);
		Node node6 = DigitalPlumber.parse("6 <-> 4, 5", nodes);

		assertEquals(6, DigitalPlumber.programsInGroupWithId(0, nodes));
		assertEquals(2, DigitalPlumber.numberOfGroups(nodes));
	}
}
