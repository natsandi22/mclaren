package com.mclaren.challenge.palindrome;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.mclaren.challenge.palindrome.exception.PalindromeNotFoundException;


public class FindPalindromeTest {
	
	static final Logger logger = Logger.getLogger(FindPalindromeTest.class);

	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	
	@Before
	public void setUpStreams() {
		System.setOut(new PrintStream(outContent));
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void moreThanOneArgument() throws PalindromeNotFoundException {
		logger.info("*** Test case moreThanOneArgument ***");
		FindPalindrome.main(new String[] {"arg1", "arg2"});
	}
	
	@Test(expected=PalindromeNotFoundException.class)
	public void oneEmptyStringAsArgument() throws PalindromeNotFoundException {
		logger.info("*** Test case oneEmptyStringAsArgument ***");
		FindPalindrome.main(new String[] {""});
	}
	
	@Test(expected=PalindromeNotFoundException.class)
	public void inputWithoutEnoughLengthToGetPalindromes() throws PalindromeNotFoundException {
		logger.info("*** Test case inputWithoutEnoughLengthToGetPalindromes ***");
		FindPalindrome.main(new String[] {"ab"});
	}
	
	@Test
	public void inputMinLengthToGetPalindromes() throws PalindromeNotFoundException, IOException {
		logger.info("*** Test case inputMinLengthToGetPalindromes ***");
		FindPalindrome.main(new String[] {"abc"});
		Assert.assertEquals(IOUtils.toString(getClass().getClassLoader().getResourceAsStream("MinLengthToGetPalindromes.txt"), StandardCharsets.UTF_8), outContent.toString());
	}
	
	@Test(expected=PalindromeNotFoundException.class)
	public void inputWithRepitedPalindromes() throws PalindromeNotFoundException {
		logger.info("*** Test case inputWithRepitedPalindromes ***");
		FindPalindrome.main(new String[] {"abb"});
	}
	
	@Test
	public void defaultExampleInput() throws PalindromeNotFoundException, IOException {
		logger.info("*** Test case defaultExampleInput ***");
		FindPalindrome.main(new String[] {"sqrrqabccbatudefggfedvwhijkllkjihxymnnmzpop"});
		Assert.assertEquals(IOUtils.toString(getClass().getClassLoader().getResourceAsStream("DefaultExampleInput.txt"), StandardCharsets.UTF_8), outContent.toString());		
	}
	
	@Test
	public void exampleGettingTheLongestPalindromes() throws PalindromeNotFoundException, IOException {
		logger.info("*** Test case exampleGettingTheLongestPalindromes ***");
		FindPalindrome.main(new String[] {"abbadeffedjklmnnmlkjopqrstuvwwvutsrqp"});
		Assert.assertEquals(IOUtils.toString(getClass().getClassLoader().getResourceAsStream("GettingTheLongestPalindrome.txt"), StandardCharsets.UTF_8), outContent.toString());		
	}

}
