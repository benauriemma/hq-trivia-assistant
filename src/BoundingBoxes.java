import java.awt.Rectangle;

public final class BoundingBoxes {
	private static final Integer LEFT_SIDE = 860;
	private static final Integer WIDTH = 400;
	private static final Integer QUESTION_START = 130;
	private static final Integer QUESTION_HEIGHT = 200;
	private static final Integer A1_START = 340;
	private static final Integer A2_START = 410;
	private static final Integer A3_START = 480;
	private static final Integer ANSWER_HEIGHT = 60;
	public static final Rectangle QUESTION = new Rectangle(LEFT_SIDE, QUESTION_START, WIDTH, QUESTION_HEIGHT);
	public static final Rectangle ANSWER_1 = new Rectangle(LEFT_SIDE, A1_START, WIDTH, ANSWER_HEIGHT);
	public static final Rectangle ANSWER_2 = new Rectangle(LEFT_SIDE, A2_START, WIDTH, ANSWER_HEIGHT);
	public static final Rectangle ANSWER_3 = new Rectangle(LEFT_SIDE, A3_START, WIDTH, ANSWER_HEIGHT);
}
