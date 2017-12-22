package advent;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import advent.SporificaVirus.Point;
import advent.SporificaVirus.Status;

public class SporificaVirusTests {

	@Test
	public void moveAndCount1() {

		List<Point> infected = new ArrayList<>();
		infected.add(new Point(2, 0));
		infected.add(new Point(0, 1));

		SporificaVirus virus = new SporificaVirus(new Point(1, 1));

		//assertEquals(41, virus.moveAndCountInfections(infected, 70));
		assertEquals(5587, virus.moveAndCountInfections(infected, 10000));
	}

	@Test
	public void moveAndCount2() {

		Map<Point, Status> points = new HashMap<>();
		points.put(new Point(2, 0), Status.INFECTED);
		points.put(new Point(0, 1), Status.INFECTED);

		SporificaVirus virus = new SporificaVirus(new Point(1, 1));

		//assertEquals(26, virus.moveAndCountInfections(points, 100));
		assertEquals(2511944, virus.moveAndCountInfections(points, 10000000));
	}
}
