package advent;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class GarbageCollectorTests {

	@Test
	public void test() {

		assertEquals(1, GarbageCollector.getScore("{}"));
		assertEquals(6, GarbageCollector.getScore("{{{}}}"));
		assertEquals(5, GarbageCollector.getScore("{{},{}}"));
		assertEquals(16, GarbageCollector.getScore("{{{},{},{{}}}}"));
		assertEquals(1, GarbageCollector.getScore("{<a>,<a>,<a>,<a>}"));
		assertEquals(9, GarbageCollector.getScore("{{<ab>},{<ab>},{<ab>},{<ab>}}"));
		assertEquals(9, GarbageCollector.getScore("{{<!!>},{<!!>},{<!!>},{<!!>}}"));
		assertEquals(3, GarbageCollector.getScore("{{<a!>},{<a!>},{<a!>},{<ab>}}"));
	}
}
