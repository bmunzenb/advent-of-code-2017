package advent;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import advent.FractalArt.Pattern;
import advent.FractalArt.Rule;

public class Main {

	public static void main(String[] args) throws URISyntaxException, IOException {

		final URL resource = Main.class.getResource("Input.txt");
		final Path source = Paths.get(resource.toURI());

		List<Rule> rules = FractalArt.parseRules(source);

		Pattern pattern = new Pattern(".#./..#/###");

		for (int i = 0; i < 5; i++) {
			pattern = FractalArt.process(pattern, rules);
			//System.out.println(pattern);
		}

		System.out.println(pattern.countPixels());

		//for (int i = 5; i < 18; i++) {
		//	pattern = FractalArt.process(pattern, rules);
		//	System.out.println(pattern);
		//}

		//System.out.println(pattern.countPixels());
	}
}
