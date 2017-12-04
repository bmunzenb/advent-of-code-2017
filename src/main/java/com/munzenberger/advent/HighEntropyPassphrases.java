package com.munzenberger.advent;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class HighEntropyPassphrases {

	public static long countValid(Path source) throws IOException {

		return Files.lines(source).filter(p -> isValid(p)).count();
	}

	public static boolean isValid(String passphrase) {

		Set<String> words = new HashSet<>();

		StringTokenizer tokens = new StringTokenizer(passphrase);
		int count = tokens.countTokens();
		while (tokens.hasMoreTokens()) {
			String word = tokens.nextToken();
			words.add(word);
		}

		return words.size() == count;
	}
}
