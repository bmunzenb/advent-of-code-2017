package com.munzenberger.advent;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.function.Function;

public class HighEntropyPassphrases {

	public static long countValid(Path source) throws IOException {

		return Files.lines(source).filter(p -> isValid(p)).count();
	}

	public static long countValidWithoutAnagrams(Path source) throws IOException {

		return Files.lines(source).filter(p -> isValidWithoutAnagrams(p)).count();
	}

	public static boolean isValid(String passphrase) {

		return isValid(passphrase, s -> s);
	}

	public static boolean isValidWithoutAnagrams(String passphrase) {

		return isValid(passphrase, s -> sort(s));
	}

	public static boolean isValid(String passphrase, Function<String, String> func) {

		Set<String> words = new HashSet<>();

		StringTokenizer tokens = new StringTokenizer(passphrase);
		while (tokens.hasMoreTokens()) {

			String word = tokens.nextToken();
			word = func.apply(word);

			if (words.contains(word)) {
				return false;
			}

			words.add(word);
		}

		return true;
	}

	private static String sort(String string) {

		char[] chars = new char[string.length()];
		string.getChars(0, string.length(), chars, 0);
		Arrays.sort(chars);
		return new String(chars);
	}
}
