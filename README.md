# Palindrome

This application finds the 3 longest unique palindromes in a supplied string. For the 3 longest palindromes, report the palindrome, start index and length, in descending order of length.
These return information will be shown in the System out console. If there are not 3 available palindromes in the input string the application returns an exception.

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes.

### Prerequisites

```
JDK 1.8
Apache Maven 3.x
```

### Installing and running

```
Dowload the project from GitHub as a ZIP and uncompress it.
```
```
Open a command prompt and go to the root directory in which pom.xml is available.
```
```
Type "mvn --version" to ensure that Maven 3 is propertly installed in the system.
```
```
Execute this command replacing the content of "first_arg" with the string that will be tested:
mvn exec:java -Dexec.args="first_arg"
```

## Running the tests

To run the test cases you should run the command "mvn test" in a CMD and you will receive a summary of the execution similar to the following line.
For more detailed information a log file called palindrome.log is created in the root directory.

```
Test run: 7, Failures: 0, Errors: 0, Skipped: 0
```

## Assumptions

It has been assumed that the min lenght of a palindrome is 1. To be able to change this behaviour a constant "MIN_LENGTH" has been introduced in the class  com.mclaren.challenge.palindrome.model.PalindromeResult.