package advent;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {

	public static void main(String[] args) throws URISyntaxException, IOException {

		final URL resource = Main.class.getResource("Input.txt");
		final Path source = Paths.get(resource.toURI());

		Machine machine = new Machine();
		machine.executeInstructions(source);

		System.out.println(machine.findLargestValue());
		System.out.println(machine.highestEver);
	}
}
