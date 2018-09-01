import java.io.IOException;

public interface Guesser {
	public Guess makeGuess(Trivia trivia) throws IOException;
}
