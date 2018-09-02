
public class GuesserFactory {
	
	public Guesser getGuesser(Trivia trivia) {
		if(trivia.getQuestion().contains(" NOT ")) {
			return new NotGuesser();
		}
		else {
			return new SimpleGuesser();
		}
	}
	
}
