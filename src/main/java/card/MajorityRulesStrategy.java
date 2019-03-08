package main.java.card;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import main.java.player.Player;
public class MajorityRulesStrategy implements AnswerStrategy {

	@Override
	public Map<Player, Boolean> calculateRightAnswer(Map<Player, AnswerOption> playerAnswers, Map<AnswerOption, String> answerOptions, 
			String rightAnswer) {
		//1. Count occurrences of answer, and return the most common answer
		Map<AnswerOption, Integer> answerFrequency = getAnswerFrequency(playerAnswers);
		
		//2. Get the Answer with the most votes;
		AnswerOption mostCommonAnswer = getMostCommonAnswer(answerFrequency);
		
		Map<Player, Boolean> isCorrectMap = new HashMap<>();
		playerAnswers.forEach((player, playerAnswer) -> {
			isCorrectMap.put(player, playerAnswer.equals(mostCommonAnswer));
		});
		 
		return isCorrectMap;
	}
	
	public AnswerOption getMostCommonAnswer(Map<AnswerOption, Integer> answerFrequency ) {
		AnswerOption mostCommonAnswer = AnswerOption.MAJORITY_RULES;
		for (Entry<AnswerOption, Integer> entry : answerFrequency.entrySet()) {
			if (entry.getValue() > answerFrequency.getOrDefault(mostCommonAnswer, -1)) {
				mostCommonAnswer = entry.getKey();
			}
		}
		return mostCommonAnswer;
	}
	
	public Map<AnswerOption, Integer> getAnswerFrequency(Map<Player, AnswerOption> playerAnswers) {
		Map<AnswerOption, Integer> frequency = new HashMap<>();
		
		for (AnswerOption playerAnswer : playerAnswers.values()) {
			if (frequency.containsKey(playerAnswer)) {
				frequency.put(playerAnswer, frequency.get(playerAnswer) + 1);
			} else {
				frequency.put(playerAnswer, 1);
			}
		}
		return frequency;
	}

}
