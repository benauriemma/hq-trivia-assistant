import java.awt.AWTException;
import java.io.IOException;

/**
 * When a key is pressed, this class carries out the process of assisting in answering a question
 * 
 * @author benauriemma
 *
 */
public class HQAssistant {

	private TriviaReader myTriviaReader = new TriviaReader();

	public HQAssistant() throws AWTException {
		// do nothing
		// empty constructor required to show that it throws exception
	}

	/**
	 * Assists user in answering a question. This involves:
	 * 	Delegating the behaviors of capturing and reading a question, choosing a guesser, and guessing
	 * 	Displaying the guess
	 * 	Timing the process and printing the time
	 * 
	 * @throws IOException
	 */
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
