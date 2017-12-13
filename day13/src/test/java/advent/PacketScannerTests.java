package advent;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import advent.PacketScanner.Layer;

public class PacketScannerTests {

	@Test
	public void solve() {

		List<Layer> layers = new ArrayList<>();

		Layer layer0 = PacketScanner.parse("0: 3");
		assertEquals(0, layer0.depth);
		assertEquals(3, layer0.range);

		layers.add(layer0);
		layers.add(new Layer(1, 2));
		layers.add(new Layer(4, 4));
		layers.add(new Layer(6, 4));

		assertEquals(24, PacketScanner.severityOfTrip(layers));
		assertEquals(10, PacketScanner.delayEnoughToNotGetCaught(layers));
	}
}