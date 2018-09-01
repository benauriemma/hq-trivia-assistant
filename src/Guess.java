
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
	
	@Override
	public String toString() {
		return 
				answerScoreString(myAnswer1, myAnswer1Score)+
				answerScoreString(myAnswer2, myAnswer2Score)+
				answerScoreString(myAnswer3, myAnswer3Score);
	}
	
	private String answerScoreString(String answer, Double score) {
		return answer+": "+score+"\n";
	}

}
