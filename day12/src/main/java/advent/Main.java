package advent;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import advent.DigitalPlumber.Node;

public class Main {

	public static void main(String[] args) throws URISyntaxException, IOException {

		final URL resource = Main.class.getResource("Input.txt");
		final Path input = Paths.get(resource.toURI());

		Map<Integer, Node> nodes = new HashMap<>();

		DigitalPlumber.parse(input, nodes);

		System.out.println(DigitalPlumber.programsInGroupWithId(0, nodes));
		System.out.println(DigitalPlumber.numberOfGroups(nodes));
	}
}
