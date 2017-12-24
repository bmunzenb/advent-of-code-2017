package advent;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import advent.ElectromagneticMoat.Bridge;
import advent.ElectromagneticMoat.Port;

public class ElectromagneticMoatTests {

	@Test
	public void test() {

		List<Port> ports = Arrays.asList(
				new Port(0, 2),
				new Port(2, 2),
				new Port(2, 3),
				new Port(3, 4),
				new Port(3, 5),
				new Port(0, 1),
				new Port(10, 1),
				new Port(9, 10)
		);

		List<Bridge> bridges = ElectromagneticMoat.buildBridges(ports);

		assertEquals(11, bridges.size());
		assertEquals(31, ElectromagneticMoat.strongest(bridges));
		assertEquals(19, ElectromagneticMoat.longestAndStrongest(bridges));
	}
}
