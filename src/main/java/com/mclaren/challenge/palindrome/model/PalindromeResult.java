package com.mclaren.challenge.palindrome.model;

import java.util.Set;

import com.mclaren.challenge.palindrome.exception.PalindromeNotFoundException;

/**
 * The {@code PalindromeResult} class represents a possible candidate for a
 * palindrome word that is contained in the class attribute named {@code text}.
 * The desirable palindrome would start at the {@code index} position and end at
 * the position fixed by {@code length}.
 * 
 * @author natalia.diaz
 * 
 */
public final class PalindromeResult {

	/**
	 * The string in which the palindrome may be contained.
	 */
	private final String text;

	/**
	 * Position in which the palindrome word may start.
	 */
	private final int index;

	/**
	 * Number of characters of the likely palindrome.
	 */
	private final int length;

	/**
	 * The minimum characters length to consider a word as a palindrome. It is being
	 * considered that a single character represents a palindrome in itself by
	 * default.
	 */
	public static final Integer MIN_LENGTH = 1;

	/**
	 * Initializes a newly created {@code PalindromeResult} object. It is an
	 * immutable object so once it is created it cannot be changed.
	 * 
	 * @param text
	 *            The word used to find a palindrome
	 * @param index
	 *            The word's position where the palindrome could start
	 * @param length
	 *            The possible palindrome's length
	 * @throws IllegalArgumentException
	 *             If the text is null / empty or the index and length values are
	 *             not within the expected range
	 * 
	 */
	public PalindromeResult(String text, int index, int length) {
		if (text == null || text.trim().isEmpty()) {
			throw new IllegalArgumentException("The palindrome text can't be null or empty");
		}
		int maxIndex = text.length() - 1;
		if (index < 0 || index > maxIndex) {
			throw new IllegalArgumentException("The index range should be between 0 and " + maxIndex);
		}
		if (length < MIN_LENGTH || length > text.length()) {
			throw new IllegalArgumentException(
					"The length range should be between " + MIN_LENGTH + " and " + text.length());
		}
		this.text = text;
		this.index = index;
		this.length = length;
	}

	/**
	 * {@link PalindromeResult#text}
	 */
	public String getText() {
		return text;
	}

	/**
	 * {@link PalindromeResult#index}
	 */
	public int getIndex() {
		return index;
	}

	/**
	 * {@link PalindromeResult#length}
	 */
	public int getLength() {
		return length;
	}

	/**
	 * In case {@code PalindromeResult} object actually represents a palindrome this
	 * method returns the palindrome contained between index and index + length.
	 * 
	 * @return The palindrome string
	 */
	public String getPalindrome() {
		return text.substring(index, index + length);
	}

	/**
	 * Check if there exists a palindrome in the positions indicated by index and
	 * length of {@code text}.
	 * 
	 * @return {@code true} if the palindrome verification is positive and
	 *         {@code false} otherwise
	 */
	public boolean isPalindrome() {
		int i = index;
		int j = index + length - 1;
		char[] array = text.toCharArray();
		while (i != j && j + 1 != i) {
			if (array[i] != array[j]) {
				return false;
			}
			i++;
			j--;
		}
		return true;
	}

	/**
	 * Once a palindrome has been found, this method verifies that the palindrome is
	 * not repeated in the rest of the string stored in {@code text}. This implies
	 * that the palindrome is unique.
	 * 
	 * @return {@code true} if the palindrome has no duplicates and {@code false}
	 *         otherwise
	 */
	public boolean isUniquePalindrome() {
		String palindrome = getPalindrome();
		String wordWithoutPalindrome = text.substring(0, index) + text.substring(index + length);
		return !wordWithoutPalindrome.contains(palindrome);
	}

	/**
	 * Given a set of palindromes, it verify that the palindrome represented in this
	 * {@code PalindromeResult} object is not a sub-palindrome of any other
	 * palindrome hold in the set.
	 * 
	 * @param palindromeSet
	 *            Set of palindromes that have been already found in {@code text}
	 * @return
	 */
	public boolean isSubPalindrome(Set<PalindromeResult> palindromeSet) {
		for (PalindromeResult palindromeResult : palindromeSet) {
			if (palindromeResult.getPalindrome().contains(this.getPalindrome())) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Given a input string, this method looks for a palindrome with a length of
	 * {@code maxLength} characters and starting at position {@code minIndex}. If
	 * such a long palindrome cann't be found then it iterates to find the next
	 * longest palindrome {@code maxLength-1} and it begin searching in and it
	 * resets the index to begin searching at zero position.
	 * 
	 * @param text
	 *            The word used to find a palindrome
	 * @param maxLength
	 *            The maximum length of the wanted palindrome
	 * @param minIndex
	 *            The index to start looking for a palindrome
	 * @return A {@code PalindromeResult} in case it is found
	 * @throws PalindromeNotFoundException
	 *             If no palindrome with a length greater than MIN_LENGTH is found
	 */
	public static PalindromeResult getMaxLengthPalindrome(String text, int maxLength, int minIndex)
			throws PalindromeNotFoundException {

		int length = maxLength;
		int index = minIndex;

		PalindromeResult result = null;
		while (length >= MIN_LENGTH) {
			while (index + length <= text.length()) {
				result = new PalindromeResult(text, index, length);
				if (result.isPalindrome()) {
					return result;
				}
				index++;
			}
			index = 0;
			length--;
		}
		throw new PalindromeNotFoundException();
	}

	@Override
	public String toString() {
		return "Text: " + getPalindrome() + ", Index: " + index + ", Length: " + length;
	}

}
