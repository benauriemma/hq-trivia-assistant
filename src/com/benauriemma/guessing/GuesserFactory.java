package com.benauriemma.guessing;

import com.benauriemma.vision.Trivia;

/**
 * Used to dynamically choose a Guesser implementation based on the type of question
 * 
 * @author benauriemma
 *
 */
public class GuesserFactory {
	
	/**
	 * Determines the question type, then returns a Guesser best designed for this type
	 * 
	 * @param trivia that the Guesser will be applied to
	 * @return the Guesser that best fits this question type
	 */
	public Guesser getGuesser(Trivia trivia) {
		if(trivia.getQuestion().contains(" NOT ")) {
			return new NotGuesser();
		}
		else {
			return new SimpleGuesser();
		}
	}
	
}
