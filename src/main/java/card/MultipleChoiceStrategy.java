package main.java.card;

import java.util.HashMap;
import java.util.Map;

import main.java.player.Player;
public class MultipleChoiceStrategy implements AnswerStrategy {

	@Override 
	public Map<Player, Boolean> calculateRightAnswer(Map<Player, AnswerOption> playerAnswers, Map<AnswerOption, String> answerOptions,  String rightAnswer) {
		Map<Player, Boolean> correctAnswers = new HashMap<>();
		playerAnswers.forEach((player, answer) -> {
			correctAnswers.put(player, answerOptions.get(answer).equals(rightAnswer));
		});
		return correctAnswers;
	}

}
