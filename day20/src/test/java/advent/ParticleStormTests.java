package advent;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import advent.ParticleSwarm.Particle;

public class ParticleStormTests {

	@Test
	public void closest() {

		Particle p1 = ParticleSwarm.parseParticle(0, "p=< 3,0,0>, v=< 2,0,0>, a=<-1,0,0>");

		assertEquals(3, p1.position.x);
		assertEquals(0, p1.position.y);
		assertEquals(0, p1.position.z);

		assertEquals(2, p1.velocity.x);
		assertEquals(0, p1.velocity.y);
		assertEquals(0, p1.velocity.z);

		assertEquals(-1, p1.acceleration.x);
		assertEquals(0, p1.acceleration.y);
		assertEquals(0, p1.acceleration.z);

		Particle p2 = ParticleSwarm.parseParticle(1, "p=< 4,0,0>, v=< 0,0,0>, a=<-2,0,0>");

		List<Particle> particles = Arrays.asList(p1, p2);

		assertEquals(0, ParticleSwarm.closest(particles).id);
	}

	@Test
	public void collide() {

		Particle p1 = ParticleSwarm.parseParticle(0, "p=<-6,0,0>, v=< 3,0,0>, a=< 0,0,0>");
		Particle p2 = ParticleSwarm.parseParticle(1, "p=<-4,0,0>, v=< 2,0,0>, a=< 0,0,0>");
		Particle p3 = ParticleSwarm.parseParticle(2, "p=<-2,0,0>, v=< 1,0,0>, a=< 0,0,0>");
		Particle p4 = ParticleSwarm.parseParticle(3, "p=< 3,0,0>, v=<-1,0,0>, a=< 0,0,0>");

		List<Particle> particles = Arrays.asList(p1, p2, p3, p4);

		assertEquals(1, ParticleSwarm.collide(particles));
	}
}
