package advent;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;

import advent.Tubes.Result;

public class Main {

	public static void main(String[] args) throws URISyntaxException, IOException {

		final URL resource = Main.class.getResource("Input.txt");
		final Path source = Paths.get(resource.toURI());

		String[] input = Tubes.parse(source);
		Result r = Tubes.walkPath(input);

		System.out.println(r.path);
		System.out.println(r.steps);
	}
}
