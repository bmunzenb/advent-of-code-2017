package advent;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

public class PacketScanner {

	public static class Layer {

		final int depth;
		final int range;

		int scanner = 0;
		int scanStep = 1;

		Layer(int depth, int range) {
			this.depth = depth;
			this.range = range;
		}

		void tick() {
			if (scanner + scanStep >= range || scanner + scanStep < 0) {
				scanStep = -scanStep;
			}
			scanner += scanStep;
		}

		int severity() {
			return depth * range;
		}
	}

	public static Map<Integer, Layer> parse(Path path) throws IOException {

		Map<Integer, Layer> layers = new HashMap<>();

		Files.lines(path).map(PacketScanner::parse).forEach(m -> layers.put(m.depth, m));

		return layers;
	}

	public static Layer parse(String string) {

		String[] s = string.split(": ");

		int depth = Integer.parseInt(s[0]);
		int range = Integer.parseInt(s[1]);

		return new Layer(depth, range);
	}

	public static int severityOfTrip(Map<Integer, Layer> layers) {

		int max = layers.keySet().stream().mapToInt(i -> i.intValue()).max().getAsInt();

		int packet = -1;
		int severity = 0;

		do {

			packet++;

			Layer layer = layers.get(packet);

			if (layer != null && layer.scanner == 0) {
				severity += layer.severity();
			}

			layers.values().stream().forEach(l -> l.tick());

		} while (packet < max);

		return severity;
	}
}
