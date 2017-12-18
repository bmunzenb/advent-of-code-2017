package advent;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import advent.Duet.Instruction;

public class Main {

	public static void main(String[] args) throws URISyntaxException, IOException {

		final URL resource = Main.class.getResource("Input.txt");
		final Path source = Paths.get(resource.toURI());

		List<Instruction> input = Duet.parse(source);
		Duet duet = new Duet(input);

		System.out.println(duet.executeUntilRecover().lastSound());

		Queue<Long> queue0 = new LinkedList<>();
		Queue<Long> queue1 = new LinkedList<>();

		DuelDuet d0 = new DuelDuet(input, queue1, queue0, 0);
		DuelDuet d1 = new DuelDuet(input, queue0, queue1, 1);

		System.out.println(DuelDuet.duel(d0, d1));
	}
}
