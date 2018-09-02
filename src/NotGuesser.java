import java.io.IOException;

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
