package advent;

import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {

	public static void main(String[] args) {

		final URL resource = Main.class.getResource("Resource.txt");
		final Path source = Paths.get(resource.toURI());
	}
}