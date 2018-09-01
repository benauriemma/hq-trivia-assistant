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
	
	private ImageReader questionReader = new ImageReader(PersonalInfo.API_KEY);
	private ImageReader answer1Reader = new ImageReader(PersonalInfo.API_KEY);
	private ImageReader answer2Reader = new ImageReader(PersonalInfo.API_KEY);
	private ImageReader answer3Reader = new ImageReader(PersonalInfo.API_KEY);
	private TextSearcher searcher = new TextSearcher(PersonalInfo.API_KEY);
	private Robot robot = new Robot();

	HQAssistant() throws AWTException {
		// do nothing
	}

	public void makeGuess() throws IOException {
		System.out.println("\n\n\n");
		long start = System.currentTimeMillis();
		BufferedImage questionCapture = robot.createScreenCapture(BoundingBoxes.QUESTION);
		BufferedImage a1Capture = robot.createScreenCapture(BoundingBoxes.ANSWER_1);
		BufferedImage a2Capture = robot.createScreenCapture(BoundingBoxes.ANSWER_2);
		BufferedImage a3Capture = robot.createScreenCapture(BoundingBoxes.ANSWER_3);
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
