package advent;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import advent.SporificaVirus.Point;

public class SporificaVirusTests {

	@Test
	public void test() {

		List<Point> infected = new ArrayList<>();
		infected.add(new Point(2, 0));
		infected.add(new Point(0, 1));

		SporificaVirus virus = new SporificaVirus(new Point(1, 1));

		//assertEquals(41, virus.moveAndCountInfections(infected, 70));
		assertEquals(5587, virus.moveAndCountInfections(infected, 10000));
	}
}
