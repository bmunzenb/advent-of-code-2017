package advent;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class ElectromagneticMoat {

	public static class Port {

		public final int pins1;
		public final int pins2;

		public Port(int pins1, int pins2) {

			this.pins1 = pins1;
			this.pins2 = pins2;
		}

		protected boolean hasPins(int pins) {

			return pins1 == pins || pins == pins2;
		}

		protected int oppositePins(int pins) {

			return pins1 == pins ? pins2 : pins1;
		}

		protected int strength() {

			return pins1 + pins2;
		}

		@Override
		public String toString() {
			return "Port(" + pins1 + "/" + pins2 + ")";
		}
	}

	public static class Bridge {

		private int endPins;
		private final List<Port> ports = new ArrayList<>();

		private Bridge() {

		}

		private Bridge(Port port, int pins) {

			ports.add(port);
			endPins = port.oppositePins(pins);
		}

		private Bridge addPort(Port port, int pins) {

			Bridge b = new Bridge();
			b.ports.addAll(ports);
			b.ports.add(port);
			b.endPins = port.oppositePins(pins);

			return b;
		}

		public int endPins() {

			return endPins;
		}

		public int strength() {

			return ports.stream().mapToInt(p -> p.strength()).sum();
		}

		@Override
		public String toString() {

			StringBuilder sb = new StringBuilder();

			Iterator<Port> i = ports.iterator();
			while (i.hasNext()) {
				Port p = i.next();
				sb.append(p.pins1).append("/").append(p.pins2);
				if (i.hasNext()) {
					sb.append("--");
				}
			}

			return sb.toString();
		}
	}

	public static List<Bridge> buildBridges(List<Port> ports) {

		List<Bridge> bridges = new ArrayList<>();

		for (Port p : ports) {
			if (p.hasPins(0)) {

				Bridge b = new Bridge(p, 0);
				bridges.add(b);

				List<Bridge> more = buildBridges(b, ports);
				bridges.addAll(more);
			}
		}

		return bridges;
	}

	public static List<Bridge> buildBridges(Bridge bridge, List<Port> ports) {

		List<Bridge> bridges = new ArrayList<>();

		int pins = bridge.endPins;

		for (Port p : ports) {
			if (p.hasPins(pins) && !bridge.ports.contains(p)) {

				Bridge next = bridge.addPort(p, pins);
				bridges.add(next);

				List<Bridge> more = buildBridges(next, ports);
				bridges.addAll(more);
			}
		}

		return bridges;
	}

	public static int strongest(List<Bridge> bridges) {

		return bridges.stream().mapToInt(b -> b.strength()).max().getAsInt();
	}

	public static int longestAndStrongest(List<Bridge> bridges) {

		int longest = bridges.stream()
				.mapToInt(b -> b.ports.size())
				.max()
				.getAsInt();

		return bridges.stream()
				.filter(b -> b.ports.size() == longest)
				.mapToInt(b -> b.strength())
				.max()
				.getAsInt();
	}

	public static List<Port> parse(Path path) throws IOException {

		return Files.lines(path)
				.map(ElectromagneticMoat::parse)
				.collect(Collectors.toList());
	}

	private static Port parse(String str) {

		String[] split = str.split("/");

		int pins1 = Integer.parseInt(split[0]);
		int pins2 = Integer.parseInt(split[1]);

		return new Port(pins1, pins2);
	}
}
