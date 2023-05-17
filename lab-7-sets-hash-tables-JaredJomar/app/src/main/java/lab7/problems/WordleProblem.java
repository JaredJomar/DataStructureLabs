package lab7.problems;

import lab7.interfaces.List;
import lab7.interfaces.Map;
import lab7.util.LinkedList;
import lab7.util.hashTable.HashTableOA;
import lab7.util.hashTable.HashTableSC;
import lab7.util.hashTable.SimpleHashFunction;

public class WordleProblem {
	/**
	 * TODO EXERCISE 2:
	 * 
	 * In the game of Wordle, you try to guess the secret word. 
	 * Every Time you guess a word, you receive a response 
	 * consisting of green, yellow and black tiles.
	 * 
	 * The rules are as follows:
	 * 
	 * 1) A green tile at position i indicates that the letter at position i 
	 * 	  in the guess word is also in the secret word at that position.
	 * 2) A yellow tile at position i indicates that the letter at position i 
	 *    in the guess word is in the secret word, but at a different position.
	 * 3) A black tile at position i indicates that the letter at position i 
	 *    in the guess word is not in the secret word at all.
	 * 
	 * Write a static non-member method called wordle(String guess, String secretWord) 
	 * that takes a string guess and a string secretWord and returns the response as 
	 * as string "G" for green, "Y" for yellow and "B" for black.
	 * 
	 * Assumptions:
	 * 1) The input words are the same length
	 * 2) The input words are all in upper-case
	 * 3) The input words contain only letters (no numbers, symbols, etc.)
	 * 4) The input words will not be empty strings
	 * 
	 * @param guess  		Input guess user made
	 * @param secretWord 	Input secret word user must guess
	 * @return				Response as a string using "G", "Y" or "B"
	 */
	public String wordle(String guess, String secretWord) {
		/*TODO ADD YOUR CODE HERE*/
		StringBuilder result = new StringBuilder();
		String[] resultArray = new String[guess.length()];

		Map<Character, Integer> SWordSearch = new HashTableSC<>(guess.length(), new SimpleHashFunction<>());
		for (int i = 0; i < secretWord.length(); i++) {
			char c = secretWord.charAt(i);
			if (!SWordSearch.containsKey(c)) {
				SWordSearch.put(c, 1);
			} else {
				int x = SWordSearch.get(c);
				SWordSearch.put(c, ++x);
			}
		}

		for (int i = 0; i < secretWord.length(); i++) {
			char guessChar = guess.charAt(i);
			char secretChar = secretWord.charAt(i);

			if (guessChar == secretChar && SWordSearch.containsKey(guessChar)) {
				int x = SWordSearch.get(guessChar) - 1;
				SWordSearch.put(guessChar, x);
				resultArray[i] = "G";
				if (x == 0) {
					SWordSearch.remove(guessChar);
				}
			} else {
				resultArray[i] = "";
			}
		}

		for (int i = 0; i < secretWord.length(); i++) {
			if (resultArray[i].equals("")) {
				char guessChar = guess.charAt(i);
				if (SWordSearch.containsKey(guessChar)) {
					int x = SWordSearch.get(guessChar) - 1;
					SWordSearch.put(guessChar, x);
					resultArray[i] = "Y";
					if (x == 0) {
						SWordSearch.remove(guessChar);
					}
				} else {
					resultArray[i] = "B";
				}
			}
		}

		for (int i = 0; i < guess.length(); i++) {
			result.append(resultArray[i]);
		}
		return result.toString();
	}
}

