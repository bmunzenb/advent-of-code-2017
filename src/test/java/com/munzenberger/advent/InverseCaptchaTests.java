package com.munzenberger.advent;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class InverseCaptchaTests {

	@Test
	public void part1() {
		assertEquals("3", InverseCaptcha.solvePart1("1122"));
		assertEquals("4", InverseCaptcha.solvePart1("1111"));
		assertEquals("0", InverseCaptcha.solvePart1("1234"));
		assertEquals("9", InverseCaptcha.solvePart1("91212129"));
	}

	@Test
	public void part2() {
		assertEquals("6", InverseCaptcha.solvePart2("1212"));
		assertEquals("0", InverseCaptcha.solvePart2("1221"));
		assertEquals("4", InverseCaptcha.solvePart2("123425"));
		assertEquals("12", InverseCaptcha.solvePart2("123123"));
		assertEquals("4", InverseCaptcha.solvePart2("12131415"));
	}
}
