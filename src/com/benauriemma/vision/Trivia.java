package com.benauriemma.vision;

/**
 * Class representing an HQ Trivia question and its 3 answers
 * 
 * @author benauriemma
 *
 */
public class Trivia {
	
	private String myQuestion;
	private String myAnswer1;
	private String myAnswer2;
	private String myAnswer3;
	
	public Trivia(String question, String answer1, String answer2, String answer3) {
		this.myQuestion = question;
		this.myAnswer1 = answer1;
		this.myAnswer2 = answer2;
		this.myAnswer3 = answer3;
	}

	public String getQuestion() {
		return myQuestion;
	}
	
	public String getAnswer1() {
		return myAnswer1;
	}
	
	public String getAnswer2() {
		return myAnswer2;
	}
	
	public String getAnswer3() {
		return myAnswer3;
	}
	
}
