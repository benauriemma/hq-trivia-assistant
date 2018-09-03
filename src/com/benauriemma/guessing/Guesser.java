package com.benauriemma.guessing;

import java.io.IOException;

import com.benauriemma.vision.Trivia;

/**
 * Interface for a Guesser, to be implemented for each new set of guessing logic
 * 
 * @author benauriemma
 *
 */
public interface Guesser {
	
	/**
	 * Called to apply a Guesser's logic to a question and return its guess
	 * 
	 * @param trivia to guess the answer to
	 * @return this Guesser's guess
	 * @throws IOException
	 */
	public Guess makeGuess(Trivia trivia) throws IOException;
	
}
