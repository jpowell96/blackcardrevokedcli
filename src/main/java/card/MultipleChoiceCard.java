package main.java.card;

import java.util.Map;

import main.java.player.Player;
public class MultipleChoiceCard extends Card {
	
	public MultipleChoiceCard(String question, Map<AnswerOption, String> options, String correctAnswer) {
		super(question, options, correctAnswer);
		this.answerStrategy = new MultipleChoiceStrategy();
	} 
	
	@Override
	public Map<Player, Boolean> correctAnswers(Map<Player, AnswerOption> playerAnswers) {
		return answerStrategy.calculateRightAnswer(playerAnswers, answers, correctAnswer);
	}
}
