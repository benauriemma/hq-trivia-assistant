import java.awt.AWTException;
import java.io.IOException;

public class HQAssistant {

	private TriviaReader myTriviaReader = new TriviaReader();

	HQAssistant() throws AWTException {
		// do nothing
	}

	public void assist() throws IOException {
		long timerStart = System.currentTimeMillis();
		Trivia trivia = myTriviaReader.read();
		Guess guess = new GuesserFactory().getGuesser(trivia).makeGuess(trivia);
		System.out.println(guess);
		long timerEnd = System.currentTimeMillis();
		System.out.println("Timed: "+(timerEnd-timerStart)+" millis");
		System.out.println("\n\n\n");
	}

}
