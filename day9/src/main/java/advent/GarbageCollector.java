package advent;

import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class GarbageCollector {

	public static int getScore(String string) {

		return new GarbageCollector()
				.readString(string)
				.getScore();
	}

	public static int getCollectedGarbage(String string) {

		return new GarbageCollector()
				.readString(string)
				.getCollectedGarbage();
	}

	private enum ReadState {
		START, GROUP, GARBAGE, IGNORE;
	}

	final Deque<ReadState> state = new LinkedList<>();
	final List<Integer> scoreList = new LinkedList<>();

	int groupDepth = 0;
	int garbageCollected = 0;

	public GarbageCollector() {

		state.addFirst(ReadState.START);
	}

	public int getScore() {

		return scoreList.stream()
				.mapToInt(i -> i.intValue())
				.sum();
	}

	public int getCollectedGarbage() {
		return garbageCollected;
	}

	public GarbageCollector readString(String string) {

		for (int i = 0; i < string.length(); i++) {
			readChar( string.charAt(i) );
		}

		return this;
	}

	public GarbageCollector readPath(Path path) throws IOException {

		FileReader reader = new FileReader(path.toFile());

		char[] buffer = new char[1024];
		int charsRead = reader.read(buffer);
		while (charsRead > 0) {
			for (int i = 0; i < charsRead; i++) {
				readChar(buffer[i]);
			}
			charsRead = reader.read(buffer);
		}

		reader.close();

		return this;
	}

	public void readChar(char c) {

		switch (state.peekFirst()) {

		case START:
		case GROUP:
			readAsGroup(c);
			break;	

		case GARBAGE:
			readAsGarbage(c);
			break;

		case IGNORE:
			readAsIgnored(c);
			break;
		}
	}

	private void readAsGroup(char c) {

		switch (c) {

		case '<':
			state.addFirst(ReadState.GARBAGE);
			break;

		case '{':
			groupDepth++;
			state.addFirst(ReadState.GROUP);
			break;

		case '}':
			state.removeFirst();
			scoreList.add(groupDepth--);
			break;

		case '!':
			state.addFirst(ReadState.IGNORE);
			break;
		}
	}

	private void readAsGarbage(char c) {

		switch (c) {

		case '>':
			state.removeFirst();
			break;

		case '!':
			state.addFirst(ReadState.IGNORE);
			break;

		default:
			garbageCollected++;
			break;
		}
	}

	private void readAsIgnored(char c) {

		state.removeFirst();
	}
}
