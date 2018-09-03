package com.benauriemma.guessing;

import java.io.IOException;

import com.benauriemma.search.SearchResponse;
import com.benauriemma.search.TextSearcher;
import com.benauriemma.vision.Trivia;

/**
 * A Guesser used for question that contain the keyword NOT
 * Logic as follows:
 * 
 * The model creates three search requests;
 * 	all contain the question as the search term (with the word NOT removed),
 * 	and each contains one answer as an exclusion term
 * The answers are scored based on how many results are returned
 * 
 * For example:
 * 	Which of the following is NOT an animal?
 * 		Dog
 * 		Plane
 * 		Cat
 * We expect that excluding the term "dog" or "cat" would remove a lot of results from the query "Which of the following is an animal?" 
 * (because the word "dog" will come up in a lot of pages that are about animals, and we omit this when we exclude the term)
 * We do not expect that excluding the term "plane" would remove a lot of results
 * (because most pages about animals wouldn't have the word "plane")
 * Thus, we would guess that whichever search returns the most results is the best answer
 * 
 * @author benauriemma
 *
 */
public class NotGuesser implements Guesser {

	@Override
	public Guess makeGuess(Trivia trivia) throws IOException {
		SearchResponse searchResponse1 = new TextSearcher().search(trivia.getQuestion().replace(" NOT ", " "), trivia.getAnswer1());
		SearchResponse searchResponse2 = new TextSearcher().search(trivia.getQuestion().replace(" NOT ", " "), trivia.getAnswer2());
		SearchResponse searchResponse3 = new TextSearcher().search(trivia.getQuestion().replace(" NOT ", " "), trivia.getAnswer3());
		Double answer1Score = Double.valueOf(searchResponse1.numberOfResults());
		Double answer2Score = Double.valueOf(searchResponse2.numberOfResults());
		Double answer3Score = Double.valueOf(searchResponse3.numberOfResults());
		return new Guess(trivia.getAnswer1(), answer1Score, trivia.getAnswer2(), answer2Score, trivia.getAnswer3(), answer3Score);
	}
	
}
