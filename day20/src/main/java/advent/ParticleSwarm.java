package advent;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class ParticleSwarm {

	public static class Triple {

		public final int x;
		public final int y;
		public final int z;

		private Triple(int x, int y, int z) {

			this.x = x;
			this.y = y;
			this.z = z;
		}

		public int distance() {

			return Math.abs(x) + Math.abs(y) + Math.abs(z);
		}

		public Triple add(Triple addend) {

			int x = this.x + addend.x;
			int y = this.y + addend.y;
			int z = this.z + addend.z;

			return new Triple(x, y, z);
		}
	}

	public static class Particle {

		public final int id;

		public Triple position;
		public Triple velocity;
		public Triple acceleration;

		private Particle(int id, Triple position, Triple velocity, Triple acceleration) {

			this.id = id;
			this.position = position;
			this.velocity = velocity;
			this.acceleration = acceleration;
		}

		private void update() {

			velocity = velocity.add(acceleration);
			position = position.add(velocity);
		}
	}

	public static List<Particle> parse(Path input) throws IOException {

		AtomicInteger id = new AtomicInteger(-1);

		return Files.lines(input)
				.map(p -> parseParticle(id.incrementAndGet(), p))
				.collect(Collectors.toList());
	}

	public static Particle parseParticle(int id, String input) {

		int start = input.indexOf("p=<") + 3;
		int end = input.indexOf('>', start);

		Triple position = parseTriple(input.substring(start, end));

		start = input.indexOf("v=<") + 3;
		end = input.indexOf('>', start);

		Triple velocity = parseTriple(input.substring(start, end));

		start = input.indexOf("a=<") + 3;
		end = input.indexOf('>', start);

		Triple acceleration = parseTriple(input.substring(start, end));

		return new Particle(id, position, velocity, acceleration);
	}

	public static Triple parseTriple(String input) {

		String[] split = input.split(",");

		int x = Integer.parseInt(split[0].trim());
		int y = Integer.parseInt(split[1].trim());
		int z = Integer.parseInt(split[2].trim());

		return new Triple(x, y, z);
	}

	public static Particle slowest(List<Particle> particles) {

		Particle slowest = particles.get(0);

		for (Particle p : particles) {

			if (p.acceleration.distance() < slowest.acceleration.distance()) {
				slowest = p;
			}
		}

		return slowest;
	}
}
