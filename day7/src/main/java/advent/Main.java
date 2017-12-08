package advent;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collection;

import advent.RecursiveCircus.Node;

public class Main {

	public static void main(String[] args) throws URISyntaxException, IOException {

		final URL resource = Main.class.getResource("Towers.txt");
		final Path source = Paths.get(resource.toURI());

		Collection<Node> nodes = RecursiveCircus.parse(source);

		Node root = RecursiveCircus.findRoot(nodes);
		System.out.println(root.name);

		RecursiveCircus.setTotalWeight(root);
		int unbalanced = RecursiveCircus.findUnbalanced(root);
		System.out.println(unbalanced);
	}
}
