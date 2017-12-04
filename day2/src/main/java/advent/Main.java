package advent;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class Main {

	public static void main(String[] args) throws URISyntaxException, IOException {

		final URL resource = Main.class.getResource("CorruptionChecksum.txt");
		final Path source = Paths.get(resource.toURI());
		final List<int[]> decoded = CorruptionChecksum.decode(source);

		System.out.println(CorruptionChecksum.solvePart1(decoded));
		System.out.println(CorruptionChecksum.solvePart2(decoded));
	}
}
