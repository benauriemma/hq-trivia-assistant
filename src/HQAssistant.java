import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

public class HQAssistant {
	
	private static final Integer LEFT_SIDE = 860;
	private static final Integer WIDTH = 400;
	private static final Integer QUESTION_START = 130;
	private static final Integer QUESTION_HEIGHT = 200;
	private static final Integer A1_START = 340;
	private static final Integer A2_START = 410;
	private static final Integer A3_START = 480;
	private static final Integer ANSWER_HEIGHT = 60;
	private static final Rectangle QUESTION_RECTANGLE = new Rectangle(LEFT_SIDE, QUESTION_START, WIDTH, QUESTION_HEIGHT);
	private static final Rectangle ANSWER_1_RECTANGLE = new Rectangle(LEFT_SIDE, A1_START, WIDTH, ANSWER_HEIGHT);
	private static final Rectangle ANSWER_2_RECTANGLE = new Rectangle(LEFT_SIDE, A2_START, WIDTH, ANSWER_HEIGHT);
	private static final Rectangle ANSWER_3_RECTANGLE = new Rectangle(LEFT_SIDE, A3_START, WIDTH, ANSWER_HEIGHT);
	
	private ImageReader questionReader = new ImageReader(PersonalInfo.API_KEY);
	private ImageReader answer1Reader = new ImageReader(PersonalInfo.API_KEY);
	private ImageReader answer2Reader = new ImageReader(PersonalInfo.API_KEY);
	private ImageReader answer3Reader = new ImageReader(PersonalInfo.API_KEY);
	private TextSearcher searcher = new TextSearcher(PersonalInfo.API_KEY);
	private Robot robot;

	HQAssistant() throws AWTException {
		robot = new Robot();
	}

	public void makeGuess() throws IOException {
		System.out.println("\n\n\n");
		long start = System.currentTimeMillis();
		BufferedImage questionCapture = robot.createScreenCapture(QUESTION_RECTANGLE);
		BufferedImage a1Capture = robot.createScreenCapture(ANSWER_1_RECTANGLE);
		BufferedImage a2Capture = robot.createScreenCapture(ANSWER_2_RECTANGLE);
		BufferedImage a3Capture = robot.createScreenCapture(ANSWER_3_RECTANGLE);
		String question = questionReader.read(questionCapture);
		String answer1 = answer1Reader.read(a1Capture);
		String answer2 = answer2Reader.read(a2Capture);
		String answer3 = answer3Reader.read(a3Capture);

		SearchResponse searchResponse = searcher.search(question.replace(" ", "+"));
		List<String> titles = searchResponse.getTitles();
		List<String> snippets = searchResponse.getSnippets();

		Integer ans1Score = scoreAnswer(answer1, titles, snippets);
		Integer ans2Score = scoreAnswer(answer2, titles, snippets);
		Integer ans3Score = scoreAnswer(answer3, titles, snippets);
		System.out.println(answer1+": "+ans1Score);
		System.out.println(answer2+": "+ans2Score);
		System.out.println(answer3+": "+ans3Score);
		
		long end = System.currentTimeMillis();
		System.out.println("Timed: "+(end-start)+" millis");

		ImageIO.write(questionCapture, "png", new File("images/question.png"));
		ImageIO.write(a1Capture, "png", new File("images/a1.png"));
		ImageIO.write(a2Capture, "png", new File("images/a2.png"));
		ImageIO.write(a3Capture, "png", new File("images/a3.png"));
	}

	private Integer scoreAnswer(String answer, List<String> titles, List<String> snippets) {
		Integer score = 0;
		for(int k = 0; k<titles.size(); k++) {
			//System.out.println(snippets.get(k));
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
