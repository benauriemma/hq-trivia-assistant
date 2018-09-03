package com.benauriemma.vision;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.benauriemma.constants.BoundingBoxes;

/**
 * Class responsible for the behavior of screenshotting a trivia question and answers, and transcribing the images to text
 * 
 * @author benauriemma
 *
 */
public class TriviaReader {
	
	private ImageTranscriber myImageTranscriber = new ImageTranscriber();
	private Robot myRobot = new Robot();
	
	public TriviaReader() throws AWTException {
		// do nothing
		// empty constructor required to show that it throws exception
	}
	
	/**
	 * Captures the question and answers from screen, transcribes them, and return the text in a Trivia object
	 * 
	 * @return a Trivia object for this question
	 * @throws IOException
	 */
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
	
	/**
	 * Saves the screenshots of a question and its answers to disk
	 * This method is currently unused, but can be used for testing/debugging purposes,
	 * 	especially when setting up bounding boxes on a new different device
	 * 
	 * @param questionCapture
	 * @param a1Capture
	 * @param a2Capture
	 * @param a3Capture
	 * @throws IOException
	 */
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
