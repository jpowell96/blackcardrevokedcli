package main.java.card;

import java.util.Map;

import main.java.player.Player;
public class MultipleChoiceCard extends Card {
	private final String LINE_SPACING = "-----------------------";

	public MultipleChoiceCard(String question, Map<AnswerOption, String> options, String correctAnswer) {
		super(question, options, correctAnswer);
		this.answerStrategy = new MultipleChoiceStrategy();
	} 
	
	@Override
	public Map<Player, Boolean> correctAnswers(Map<Player, AnswerOption> playerAnswers) {
		return answerStrategy.calculateRightAnswer(playerAnswers, answers, correctAnswer);
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(LINE_SPACING);
		sb.append('\n');
		sb.append("Multiple Choice Question:");
		sb.append('\n');
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
