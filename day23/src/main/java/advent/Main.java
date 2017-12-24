package advent;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import advent.Coprocessor.Instruction;

public class Main {

	public static void main(String[] args) throws URISyntaxException, IOException {

		final URL resource = Main.class.getResource("Input.txt");
		final Path source = Paths.get(resource.toURI());

		List<Instruction> input = Coprocessor.parse(source);

		Coprocessor processor = new Coprocessor(input);
		System.out.println(processor.executeAndCountInstructions("mul"));

		//System.out.println(Deconstructed.execute());
	}
}
