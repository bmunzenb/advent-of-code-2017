package advent;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import advent.SporificaVirus.Point;

public class Main {

	public static void main(String[] args) throws URISyntaxException, IOException {

		final URL resource = Main.class.getResource("Input.txt");
		final Path source = Paths.get(resource.toURI());

		List<Point> infected = SporificaVirus.parse(source);
		Point start = new Point(12, 12);
		SporificaVirus virus = new SporificaVirus(start);

		System.out.println(virus.moveAndCountInfections(infected, 10000));
	}
}
