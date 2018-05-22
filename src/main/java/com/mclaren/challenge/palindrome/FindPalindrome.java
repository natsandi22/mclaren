package com.mclaren.challenge.palindrome;

import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;

import org.apache.log4j.Logger;

import com.mclaren.challenge.palindrome.exception.PalindromeNotFoundException;
import com.mclaren.challenge.palindrome.model.PalindromeResult;

/**
 * The {@code FindPalindrome} class implements an application that receives a
 * command line argument and prints the three longest palindromes that it can
 * find. If there are less than three palindromes in the input string it throws
 * a PalindromeNotFoundException.
 * 
 * @author natalia.diaz
 *
 */
public class FindPalindrome {

	/**
	 * A constant holding the number of palindromes searched
	 */
	public static final int NUMBER_OF_PALINDROMES = 3;

	static final Logger logger = Logger.getLogger(FindPalindrome.class);

	public static void main(String[] args) throws PalindromeNotFoundException {

		logger.info("Starting main method");
		try {

			if (args.length != 1)
				throw new IllegalArgumentException("One string argument is expected.");

			String text = args[0];
			logger.info("Received args[0]: " + text);

			Set<PalindromeResult> palindromeSet = new HashSet<PalindromeResult>();
			int length = text.length();
			int index = 0;

			PalindromeResult result = null;
			while (palindromeSet.size() < NUMBER_OF_PALINDROMES) {
				logger.info("Looking for the [" + palindromeSet.size() + "] palindrome.");
				result = PalindromeResult.getMaxLengthPalindrome(text, length, index);
				if (result.isUniquePalindrome() && !result.isSubPalindrome(palindromeSet)) {
					palindromeSet.add(result);
					logger.info("--- " + result.getPalindrome() + " ---");
				} else {
					logger.debug(
							"Not valid " + result.getPalindrome() + " because it is a duplicate or a sub-palindrome.");
				}
				length = result.getLength();
				index = result.getIndex() + 1;
			}

			palindromeSet.stream().sorted(Comparator.comparing(PalindromeResult::getLength).reversed()
					.thenComparing(PalindromeResult::getPalindrome)).forEach(System.out::println);

		} catch (PalindromeNotFoundException e) {
			logger.error(e);
			throw e;
		}

		logger.info("Ending main method");
	}

}
