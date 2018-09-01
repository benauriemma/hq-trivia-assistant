import java.io.IOException;
import java.util.List;

public class SimpleGuesser implements Guesser {

	@Override
	public Guess makeGuess(Trivia trivia) throws IOException {
		SearchResponse searchResponse = new TextSearcher().search(trivia.getQuestion());
		List<String> titles = searchResponse.getTitles();
		List<String> snippets = searchResponse.getSnippets();
		Double answer1Score = scoreAnswer(trivia.getAnswer1(), titles, snippets);
		Double answer2Score = scoreAnswer(trivia.getAnswer2(), titles, snippets);
		Double answer3Score = scoreAnswer(trivia.getAnswer3(), titles, snippets);
		return new Guess(trivia.getAnswer1(), answer1Score, trivia.getAnswer2(), answer2Score, trivia.getAnswer3(), answer3Score);
	}

	private Double scoreAnswer(String answer, List<String> titles, List<String> snippets) {
		Double score = 0.0;
		for(int k = 0; k<titles.size(); k++) {
			if(titles.get(k).contains(answer)) {
				score++;
			}
			if(snippets.get(k).contains(answer)) {
				score++;
			}
		}
		return score;
	}

}
