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
import java.util.stream.Collectors;

public class RecursiveCircus {

	public static class Node implements Comparable<Node> {

		public final String name;
		public int weight;
		public int totalWeight;
		public final List<Node> holdingUp = new ArrayList<>();

		public Node(String name) {
			this.name = name;
		}

		@Override public int compareTo(Node o) {
			return this.totalWeight - o.totalWeight;
		}

		@Override public String toString() {
			return String.format("%s(%d)->%d", name, weight, totalWeight);
		}
	}

	public static Collection<Node> parse(Path input) throws IOException {

		Map<String, Node> nodes = new HashMap<>();
		Files.lines(input).forEach(p -> parseInto(p, nodes));
		return nodes.values();
	}

	public static int findUnbalanced(Node node) {

		for (Node n : node.holdingUp) {
			int unbalanced = findUnbalanced(n);
			if (unbalanced != 0) {
				return unbalanced;
			}
		}

		List<Node> nodes = node.holdingUp
				.stream()
				.sorted()
				.collect(Collectors.toList());

		if (nodes.size() > 1) {

			Node first = nodes.get(0);
			Node last = nodes.get(nodes.size()-1);

			if (first.totalWeight != last.totalWeight) {
				if (nodes.size() > 2) {
					int mid = nodes.get(1).totalWeight;
					if (first.totalWeight == mid) {
						// last one is unbalanced
						return last.weight - (last.totalWeight - mid);
					}
					else if (last.totalWeight == mid) {
						// first one is unbalanced
						return first.weight + (mid - first.totalWeight);
					}
				}

				throw new IllegalStateException("Could not determine unbalance!");
			}
		}

		return 0;
	}

	public static int setTotalWeight(Node node) {

		int total = node.weight;

		for (Node n : node.holdingUp) {
			total += setTotalWeight(n);
		}

		node.totalWeight = total;

		return total;
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
