package advent;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {

	public static void main(String[] args) throws URISyntaxException, IOException {

		final URL resource = Main.class.getResource("JumpInstructions.txt");
		final Path source = Paths.get(resource.toURI());

		int[] input = JumpInstructions.parse(source);
		System.out.println(JumpInstructions.steps(input));

		input = JumpInstructions.parse(source);
		System.out.println(JumpInstructions.stepsWithComplication(input));
	}
}
