package advent;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

public class DigitalPlumber {

	static class Node {

		final int id;
		final Set<Node> connected = new HashSet<>();

		Node(int id) {
			this.id = id;
		}
	}

	public static void parse(Path path, Map<Integer, Node> nodes) throws IOException {

		Files.lines(path).forEach(s -> parse(s, nodes));
	}

	public static Node parse(String string, Map<Integer, Node> nodes) {

		int start = 0;
		int end = string.indexOf('<');

		int id = Integer.parseInt(string.substring(start, end - 1));

		Node node = nodes.get(id);
		if (node == null) {
			node = new Node(id);
			nodes.put(id, node);
		}

		start = string.indexOf('>');
		StringTokenizer tokens = new StringTokenizer(string.substring(start+1), ",");
		while (tokens.hasMoreTokens()) {

			int connectedId = Integer.parseInt(tokens.nextToken().trim());
			Node connected = nodes.get(connectedId);
			if (connected == null) {
				connected = new Node(connectedId);
				nodes.put(connectedId, connected);
			}
			node.connected.add(connected);
			//connected.connected.add(node);
		}

		return node;
	}

	public static int programsInGroupWithId(int id, Map<Integer, Node> nodes) {

		Node node = nodes.get(id);

		return addAllInPath(node, nodes, new HashSet<>()).size();
	}

	public static Set<Node> addAllInPath(Node node, Map<Integer, Node> nodes, Set<Node> set) {

		if (set.contains(node)) {
			return set;
		}

		set.add(node);

		node.connected.stream().forEach(n -> addAllInPath(n, nodes, set));

		return set;
	}

	public static int numberOfGroups(Map<Integer, Node> nodes) {

		int sets = 0;

		List<Node> nodesToCheck = new ArrayList<>(nodes.values());

		while (!nodesToCheck.isEmpty()) {

			sets++;
			Node node = nodesToCheck.get(0);
			addAllInPath(node, nodes, new HashSet<>()).stream().forEach(nodesToCheck::remove);
		}

		return sets;
	}
}
