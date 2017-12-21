package advent;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import advent.FractalArt.Pattern;
import advent.FractalArt.Rule;

public class FractalArtTests {

	@Test
	public void transformations() {

		String pattern = ".#./..#/###";

		String rotate90 = Pattern.rotate(pattern);
		assertEquals("#../#.#/##.", rotate90);

		String rotate180 = Pattern.rotate(rotate90);
		assertEquals("###/#../.#.", rotate180);

		String rotate270 = Pattern.rotate(rotate180);
		assertEquals(".##/#.#/..#", rotate270);

		String flipH = Pattern.flipHorizontal(pattern);
		assertEquals(".#./#../###", flipH);

		String flipV = Pattern.flipVertical(pattern);
		assertEquals("###/..#/.#.", flipV);
	}

	@Test
	public void subpattern() {

		Pattern pattern = new Pattern(".#./..#/###");

		Pattern[][] split = pattern.split();

		assertEquals(1, split.length);
		assertEquals(1, split[0].length);
		assertEquals(".#./..#/###", split[0][0].pattern);
	}

	@Test
	public void combine() {

		Pattern[][] patterns = new Pattern[1][1];
		patterns[0][0] = new Pattern("#..#/..../..../#..#");

		Pattern combined = new Pattern(patterns);

		assertEquals("#..#/..../..../#..#", combined.pattern);
	}

	@Test
	public void subpatternsSplitCombine() {

		Pattern pattern = new Pattern("#..#/..../..../#..#");

		assertEquals(4, pattern.size);
		assertEquals("#./..", pattern.subpattern(0, 0, 2).pattern);
		assertEquals("../.#", pattern.subpattern(2, 2, 2).pattern);

		Pattern[][] split = pattern.split();

		assertEquals("#./..", split[0][0].pattern);
		assertEquals(".#/..", split[0][1].pattern);
		assertEquals("../#.", split[1][0].pattern);
		assertEquals("../.#", split[1][1].pattern);

		Pattern combined = new Pattern(split);

		assertEquals("#..#/..../..../#..#", combined.pattern);
	}

	@Test
	public void process() {

		List<Rule> rules = Arrays.asList(
			new Rule(new Pattern("../.#"), new Pattern("##./#../...")),
			new Rule(new Pattern(".#./..#/###"), new Pattern("#..#/..../..../#..#"))
		);

		Pattern pattern = new Pattern(".#./..#/###");

		pattern = FractalArt.process(pattern, rules);

		assertEquals("#..#/..../..../#..#", pattern.pattern);

		pattern = FractalArt.process(pattern, rules);

		assertEquals("##.##./#..#../....../##.##./#..#../......", pattern.pattern);

		assertEquals(12, pattern.countPixels());
	}
}
