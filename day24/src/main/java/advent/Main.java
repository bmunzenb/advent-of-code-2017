package advent;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import advent.ElectromagneticMoat.Bridge;
import advent.ElectromagneticMoat.Port;

public class Main {

	public static void main(String[] args) throws URISyntaxException, IOException {

		final URL resource = Main.class.getResource("Input.txt");
		final Path source = Paths.get(resource.toURI());

		List<Port> ports = ElectromagneticMoat.parse(source);
		List<Bridge> bridges = ElectromagneticMoat.buildBridges(ports);

		System.out.println(ElectromagneticMoat.strongest(bridges));
		System.out.println(ElectromagneticMoat.longestAndStrongest(bridges));
	}
}
