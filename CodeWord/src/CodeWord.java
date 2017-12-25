import java.util.*;
import java.io.*;

public class CodeWord {
	public static final int WORDLENGTH = 5;
	public static final int DICTIONARYSIZE = 8548;
	// Holds all words
	public static String[] dictionary = new String[DICTIONARYSIZE];
	// Indicates whether words can logically be ruled out
	public static boolean[] inPlay = new boolean[DICTIONARYSIZE];

	public static void main(String[] args) 
			throws FileNotFoundException {
		readDictionary(); 
		Scanner console = new Scanner(System.in);
		Random rand = new Random();
		
		while(yesTo("Do you want to play Code Word?", console)) {
			console.nextLine();
			String codeWord = dictionary[rand.nextInt(DICTIONARYSIZE)];
			
			for(int i = 0; i < inPlay.length; i++) {
				inPlay[i] = true; // all words are in play after a new code word is chosen
			}
			int numberOfGuesses = getValidNumberOfGuesses("How many guesses do you want? ", console);
			console.nextLine(); 
			
			String hint = ""; 
			int guessesSoFar = 0; 
			String[] historyOfGuesses = new String[numberOfGuesses]; 
			
			for(int i = numberOfGuesses; i > 0; i--) { 
				String guess = getValidGuess(i + ": Guess a 5-letter word (enter 'r' for remaining words): ", console);
				
				for(int j = 0; j < historyOfGuesses.length; j++)  
					if(historyOfGuesses[j] != null)	
						System.out.println(historyOfGuesses[j]);
				
				hint = feedback(guess, codeWord);
				System.out.println(guess + ": " + hint); // hint format (i.e. cloud: **+)
				guessesSoFar++; 
				
				historyOfGuesses[historyOfGuesses.length - i] = guess + ": " + hint;
				removeWordsWithDifferentFeedback(guess, hint);
	            	
				if(hint.equals("*****")) { 
					if(guessesSoFar == 1) { 
						System.out.println("You won in " + guessesSoFar + " guess!" 
								+ " The word was: " + codeWord);
					}
					else { 
						System.out.println("You won in " + guessesSoFar + " guesses!"
								+ " The word was: " + codeWord);
					i = 0; 
					}
				} 
				else if(i == 1) { 
					System.out.println("You are bad at guessing. Just kidding, the dictionary makes this game extremely difficult."
							+ "\nThe word was: " + codeWord);
				}
			}
		}
	}
	
	/**
	 * Prints the list of remaining possible words that the
	 * code word could be based on if the corresponding element
	 * in inPlay is still true.
	 * @param dictionary
	 */
	public static void listOfRemainingWords(String[] dictionary) {
		System.out.println("\tRemaining words:");
		for(int i = 0; i < DICTIONARYSIZE; i++) 
			if(inPlay[i])  
				System.out.println("\t" + dictionary[i]); 	
	}
	
	/**
	 * Determines whether or not the input number of guesses is valid,
	 * i.e. a positive integer. Uses a forever loop that continually
	 * loops and tells the user that their guess is not a positive integer
	 * until they enter a positive integer.
	 * @param prompt Text presented to user
	 * @param console Source of user input
	 * @return numberOfGuesses Valid number of guesses (positive integer)
	 */
	public static int getValidNumberOfGuesses(String prompt, Scanner console) {
		for(;;) {		
			System.out.print(prompt); 
			if(console.hasNextInt()) {
				int numberOfGuesses = console.nextInt();
				if(numberOfGuesses > 0) { 	
					return numberOfGuesses;
				}
				else { 
					console.nextLine(); 
					System.out.println("Please enter a positive integer.");				
				}
			}
			else { 
				console.nextLine(); 
				System.out.println("Please enter a positive integer with no other characters.");
			}	
		}
	}
	
	/**
	 * Determines whether or not the guess is valid, i.e. 5 
	 * lower case characters ('r' is also also allowed for the hint system).
	 * Uses a forever loop that continually loops and tells the user 
	 * what is wrong with their guess until they enter a 5 lower case letters or 'r'.
	 * @param prompt Text presented to user
	 * @param console Source of user input
	 * @return guess The valid guess (5 characters)
	 */
	public static String getValidGuess(String prompt, Scanner console) {
		for(;;) {
			boolean valid = true;
			System.out.print(prompt);
			String guess = console.nextLine();
			if(guess.equals("r")) { 
				listOfRemainingWords(dictionary); 
			}
			else if(guess.length() != WORDLENGTH) { 
				System.out.println("Your guess is not 5-letters long. Please try again.");
				valid = false;
			}
			else {
				for(int i = 0; i < WORDLENGTH && valid; i++) { 
					if(!Character.isLetter(guess.charAt(i)) || Character.isUpperCase(guess.charAt(i))) {
						System.out.println("Please use only lowercase letters in your guess. Try again.");
						valid = false;
					}
				}
				if(valid) 
					return guess;
			}
		}
	}
	
	/**
	 * Reads the dictionary from the file into the dictionary array.
	 * @throws FileNotFoundException 
	 */
	public static void readDictionary() 
			throws FileNotFoundException {
		Scanner scan = new Scanner(new File("wordList.txt"));	
		for(int i = 0; i < dictionary.length; i++) 
			dictionary[i] = scan.next(); 
		scan.close();
	}

	/**
	 * Compare the feedback from comparing a guess to the codeword to the feedback from
	 * comparing the guess to each dictionary word, and filter out all dictionary words
	 * with differing feedback (makes element of inPlay false).
	 * @param guess A user guess from the console
	 * @param feedback String of *'s and +'s resulting from comparing the guess to the code word
	 */
	public static void removeWordsWithDifferentFeedback(String guess, String feedback) {
		for(int i = 0; i < DICTIONARYSIZE; i++) 
			if(!feedback.equals(feedback(dictionary[i], guess))) 
				inPlay[i] = false;
	}

	/**
	 * This method takes two 5-letter words and returns a String
	 * of asterisks containing the feedback that the user would get
	 * for comparing word1 to word2. Recall that the number of asterisks (*'s)
	 * equals the number of in-place matches, and the number of plus signs (+'s)
	 * equals the number of out-of-place matches. All asterisks should
	 * precede all plus signs.
	 *
	 * @param word1 5-letter word, e.g. a user guess
	 * @param word2 Another 5-letter word, e.g. the secret code word
	 * @return Feedback of asterisks and plus signs indicating the number
	 *         of direct and indirect matches.
	 */
	public static String feedback(String word1, String word2) {
		boolean[] b1 = new boolean[WORDLENGTH]; 
		boolean[] b2 = new boolean[WORDLENGTH];
		String result = ""; 
		
		// direct match
		for(int i = 0; i < WORDLENGTH; i++) {
			if(word1.charAt(i) == word2.charAt(i)) { 
				result += "*";
				b1[i] = true;
				b2[i] = true;				
			}
		}
		// indirect match
		for(int i = 0; i < WORDLENGTH; i++) {
			for(int j = 0; j < WORDLENGTH; j++) {
				 if(!b1[i] && !b2[j] && word1.charAt(i) == word2.charAt(j)) {
					result += "+";
					b1[i] = true; 
					b2[j] = true;
				}
			}
		}
		return result;
	}
	
	/**
	 * Utility function to ask user yes or no.
         * It uses a forever loop -- but the loop stops when something is returned.
	 * @param prompt Text presented to user
	 * @param console Source of user input
	 * @return Whether user types 'y' (as opposed to 'n').
	 */
	public static boolean yesTo(String prompt, Scanner console) {
		for (;;) {
			System.out.print(prompt + " (y/n)? ");
			String response = console.next().trim().toLowerCase();
			if (response.equals("y"))
				return true;
			else if (response.equals("n"))
				return false;
			else
				System.out.println("Please answer y or n.");
		}
	}
}
