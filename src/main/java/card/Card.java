package main.java.card;

import java.util.Collections;
import java.util.Map;

import main.java.player.Player;
public abstract class Card {
	String question;
	Map<AnswerOption, String> answers;
	AnswerStrategy answerStrategy;
	String correctAnswer;
	private final String LINE_SPACING = "-----------------------";
	public Card(String question, Map<AnswerOption, String> answers, String correctAnswer) {
		//Add argument checking to ensure the correct answer is an answer option, and that the answers is not empty
		this.answers = answers;
		this.question = question;
		this.correctAnswer = correctAnswer;
	}	
	
	public Map<Player, Boolean> correctAnswers(Map<Player, AnswerOption> playerAnswers) {
		return answerStrategy.calculateRightAnswer(playerAnswers, answers, correctAnswer);
	}
	
	public Map<AnswerOption, String> getAnswers() {
		return Collections.unmodifiableMap(this.answers);
	}
	
	public void setAnswer(String answer) {
		this.correctAnswer = answer;
	}
	
	public String getAnswer() {
		return this.correctAnswer;
	}
	
	String getQuestion() {
		return this.question;
	}
	
	AnswerStrategy getStrategy() {
		return this.answerStrategy;
	}
	
	void setAnswerStrategy(AnswerStrategy answerStrategy) {
		this.answerStrategy = answerStrategy;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(LINE_SPACING);
		sb.append('\n');
		sb.append(String.format("Question: %s", question));
		sb.append('\n');
		sb.append(LINE_SPACING);
		
		sb.append('\n');
		this.answers.forEach((answer, answerValue) -> {
			sb.append((String.format("%s. %s \n", answer, answerValue)));
		});
		sb.append('\n');
		return sb.toString();
	}
}
