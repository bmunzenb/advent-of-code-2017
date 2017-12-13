package advent;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import advent.PacketScanner.Layer;

public class PacketScannerTests {

	@Test
	public void solvePart1() {

		Map<Integer, Layer> layers = new HashMap<>();

		Layer layer0 = PacketScanner.parse("0: 3");
		assertEquals(0, layer0.depth);
		assertEquals(3, layer0.range);

		layers.put(0, layer0);
		layers.put(1, new Layer(1, 2));
		layers.put(4, new Layer(4, 4));
		layers.put(6, new Layer(6, 4));

		assertEquals(24, PacketScanner.severityOfTrip(layers));
	}
}