package advent;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import advent.Duet.Instruction;

public class Main {

	public static void main(String[] args) throws URISyntaxException, IOException {

		final URL resource = Main.class.getResource("Input.txt");
		final Path source = Paths.get(resource.toURI());

		List<Instruction> input = Duet.parse(source);
		Duet duet = new Duet(input);

		System.out.println(duet.executeUntilRecover().lastSound());
	}
}
