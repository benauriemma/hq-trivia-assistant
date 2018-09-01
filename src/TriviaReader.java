import java.awt.AWTException;
import java.awt.Robot;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class TriviaReader {
	
	private ImageTranscriber myImageTranscriber = new ImageTranscriber();
	private Robot myRobot = new Robot();
	
	public TriviaReader() throws AWTException {
		// do nothing
	}
	
	public Trivia read() throws IOException {
		BufferedImage questionCapture = myRobot.createScreenCapture(BoundingBoxes.QUESTION);
		BufferedImage a1Capture = myRobot.createScreenCapture(BoundingBoxes.ANSWER_1);
		BufferedImage a2Capture = myRobot.createScreenCapture(BoundingBoxes.ANSWER_2);
		BufferedImage a3Capture = myRobot.createScreenCapture(BoundingBoxes.ANSWER_3);
		String question = myImageTranscriber.transcribe(questionCapture);
		String answer1 = myImageTranscriber.transcribe(a1Capture);
		String answer2 = myImageTranscriber.transcribe(a2Capture);
		String answer3 = myImageTranscriber.transcribe(a3Capture);
		return new Trivia(question, answer1, answer2, answer3);
	}
	
	private void logImages(
			BufferedImage questionCapture, 
			BufferedImage a1Capture,
			BufferedImage a2Capture,
			BufferedImage a3Capture
			) throws IOException {
		ImageIO.write(questionCapture, "png", new File("images/question.png"));
		ImageIO.write(a1Capture, "png", new File("images/a1.png"));
		ImageIO.write(a2Capture, "png", new File("images/a2.png"));
		ImageIO.write(a3Capture, "png", new File("images/a3.png"));
	}
	
}
