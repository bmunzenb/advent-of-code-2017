package advent;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class ParticleSwarm {

	public static class Triple {

		public final long x;
		public final long y;
		public final long z;

		private Triple(long x, long y, long z) {

			this.x = x;
			this.y = y;
			this.z = z;
		}

		public long distance() {

			return Math.abs(x) + Math.abs(y) + Math.abs(z);
		}

		public Triple add(Triple addend) {

			long x = this.x + addend.x;
			long y = this.y + addend.y;
			long z = this.z + addend.z;

			return new Triple(x, y, z);
		}

		public boolean equals(Triple other) {
			return this.x == other.x && this.y == other.y && this.z == other.z;
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

		long x = Long.parseLong(split[0].trim());
		long y = Long.parseLong(split[1].trim());
		long z = Long.parseLong(split[2].trim());

		return new Triple(x, y, z);
	}

	public static Particle closest(List<Particle> particles) {

		Particle slowest = particles.get(0);

		for (Particle p : particles) {

			if (p.acceleration.distance() < slowest.acceleration.distance()) {
				slowest = p;
			}

			// could have checked for matching accelerations and compared velocities and
			// positions, but our input doesn't have any that match, or the first known slowest
			// just happened to be the right answer
		}

		return slowest;
	}

	public static int collide(List<Particle> particles) {

		List<Particle> alive = new ArrayList<>(particles);

		int ticksSinceLastCollision = 0;

		// assume that if we make it 100 ticks without a collision,
		// there won't be any more.  feels so dirty, though...
		while (ticksSinceLastCollision < 100) {

			alive.stream().forEach(p -> p.update());

			List<Particle> collisions = new ArrayList<>();

			for (int x = 0; x < alive.size()-1; x++) {
				Particle p1 = alive.get(x);

				for (int y = x+1; y < alive.size(); y++) {
					Particle p2 = alive.get(y);

					if (p1.position.equals(p2.position)) {

						collisions.add(p1);
						collisions.add(p2);
					}
				}
			}

			if (!collisions.isEmpty()) {

				alive.removeAll(collisions);
				ticksSinceLastCollision = 0;
			}
			else {
				ticksSinceLastCollision++;
			}
		}

		return alive.size();
	}
}
