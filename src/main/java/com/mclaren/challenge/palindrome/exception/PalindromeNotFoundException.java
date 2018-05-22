package com.mclaren.challenge.palindrome.exception;

/**
 * 
 * Thrown when the
 * {@link com.mclaren.challenge.palindrome.PalindromeResult#getMaxLengthPalindrome(String text, int maxLength, int minIndex)}
 * method is not able to find any palindrome with the expected conditions.
 * 
 * @author natalia.diaz
 *
 */
public class PalindromeNotFoundException extends Exception {

	private static final long serialVersionUID = 7386990365951091064L;

	/**
	 * Constructs a {@code PalindromeNotFoundException} with no detail message.
	 */
	public PalindromeNotFoundException() {
		super();
	}

	/**
	 * Constructs a {@code PalindromeNotFoundException} with the specified detail
	 * message.
	 *
	 * @param message
	 *            the detail message.
	 */
	public PalindromeNotFoundException(String message) {
		super(message);
	}

}
