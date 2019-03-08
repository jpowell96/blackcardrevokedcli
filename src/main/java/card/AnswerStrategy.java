package main.java.card;

import java.util.Map;

import main.java.player.Player;
public interface AnswerStrategy {
	
	Map<Player, Boolean> calculateRightAnswer(Map<Player, AnswerOption> playerAnswers, Map<AnswerOption, String> answerOptions, String rightAnswer);
}
