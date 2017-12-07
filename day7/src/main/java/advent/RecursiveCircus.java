package advent;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class RecursiveCircus {

	public static class Node {

		public final String name;
		public int weight;
		public final List<Node> holdingUp = new ArrayList<>();

		public Node(String name) {
			this.name = name;
		}
	}

	public static Collection<Node> parse(Path input) throws IOException {

		Map<String, Node> nodes = new HashMap<>();
		Files.lines(input).forEach(p -> parseInto(p, nodes));
		return nodes.values();
	}

	public static Node parseInto(String line, Map<String, Node> nodes) {

		int start = 0;
		int end = line.indexOf(' ');
		String name = line.substring(start, end);

		Node node = getNode(name, nodes);

		start = line.indexOf('(');
		end = line.indexOf(')');
		node.weight = Integer.parseInt(line.substring(start+1, end));

		start = line.indexOf('>');
		if (start > 0) {
			StringTokenizer tokens = new StringTokenizer(line.substring(start+1), ", ");
			while (tokens.hasMoreTokens()) {
				String other = tokens.nextToken();
				Node otherNode = getNode(other, nodes);
				node.holdingUp.add(otherNode);
			}
		}

		return node;
	}

	private static Node getNode(String name, Map<String, Node> nodes) {
		Node node = nodes.get(name);
		if (node == null) {
			node = new Node(name);
			nodes.put(name, node);
		}
		return node;
	}

	public static Node findRoot(Collection<Node> nodes) {

		List<Node> copy = new ArrayList<>(nodes);
		nodes.stream().forEach(n -> n.holdingUp.stream().forEach(copy::remove));
		return copy.stream().findFirst().get();
	}
}
