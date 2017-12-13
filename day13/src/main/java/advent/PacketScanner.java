package advent;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

public class PacketScanner {

	public static class Layer implements Comparable<Layer> {

		final int depth;
		final int range;
		final int period;
		final int severity;

		Layer(int depth, int range) {
			this.depth = depth;
			this.range = range;
			this.period = period(range);
			this.severity = depth * range;
		}

		public static int period(int range) {

			int period = 0;
			int scanner = 0;

			int step = 1;

			do {

				if (scanner + step >= range || scanner + step < 0) {
					step = -step;
				}

				scanner += step;
				period++;

			} while (scanner > 0);

			return period;
		}

		@Override public int compareTo(Layer other) {
			return this.depth - other.depth;
		}
	}

	public static List<Layer> parse(Path path) throws IOException {

		return Files.lines(path)
				.map(PacketScanner::parse)
				.sorted()
				.collect(Collectors.toList());
	}

	public static Layer parse(String string) {

		String[] s = string.split(": ");

		int depth = Integer.parseInt(s[0]);
		int range = Integer.parseInt(s[1]);

		return new Layer(depth, range);
	}

	public static int severityOfTrip(List<Layer> layers) {

		int severity = 0;

		for (Layer layer : layers) {

			int scan = layer.depth % layer.period;
			if (scan == 0) {
				severity += layer.severity;
			}
		}

		return severity;
	}

	public static int delayEnoughToNotGetCaught(List<Layer> layers) {

		int delay = 0;

		while (true) {

			boolean caught = false;

			for (Layer layer : layers) {

				int scan = (layer.depth + delay) % layer.period;
				if (scan == 0) {
					caught = true;
					delay++;
					break;
				}
			}

			if (!caught) {
				return delay;
			}
		}
	}
}
