/**
 * Represents the assistant's guess for a question
 * 
 * @author benauriemma
 *
 */
public class Guess {
	private String myAnswer1;
	private Double myAnswer1Score;
	private String myAnswer2;
	private Double myAnswer2Score;
	private String myAnswer3;
	private Double myAnswer3Score;

	public Guess(
			String answer1, Double answer1Score, 
			String answer2, Double answer2Score, 
			String answer3, Double answer3Score
			) {
		this.myAnswer1 = answer1;
		this.myAnswer1Score = answer1Score;
		this.myAnswer2 = answer2;
		this.myAnswer2Score = answer2Score;
		this.myAnswer3 = answer3;
		this.myAnswer3Score = answer3Score;
	}
	
	/**
	 * Overrides Object's implementation so that an explanation of the Guess can be printed to the console
	 * 
	 * @return a String of each answer and its score, formatted nicely
	 */
	@Override
	public String toString() {
		return 
				answerScoreString(myAnswer1, myAnswer1Score)+
				answerScoreString(myAnswer2, myAnswer2Score)+
				answerScoreString(myAnswer3, myAnswer3Score);
	}
	
	/**
	 * Helper method to format an answer and its score
	 * 
	 * @param answer
	 * @param score of this answer
	 * @return the answer and score, formatted nicely
	 */
	private String answerScoreString(String answer, Double score) {
		return answer+": "+score+"\n";
	}

}
