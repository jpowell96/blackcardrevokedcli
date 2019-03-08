package test.java.card;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import main.java.card.AnswerOption;
import main.java.card.MajorityRulesStrategy;
import main.java.player.Player;

public class MajorityRulesStrategyTest {
	
	private MajorityRulesStrategy subject;
	
	@BeforeEach
	public void init() {
		subject = new MajorityRulesStrategy();
	}
	
	@Test
	public void calculateRightAnswerReturnsCorrectAnswer() {
		 //Arrange
		Map<Player, AnswerOption> playerAnswers = playerAnswers();
		Map<AnswerOption, String> answerOptions = answerOptions();
		
		//Act
		Map<Player, Boolean> result = subject.calculateRightAnswer(playerAnswers, answerOptions, "");
		
		//Assert: Most Common answer is A, Patricia and Jonathan should have true as their values
		//Martin and Marta should be false

		result.forEach((player, isCorrect) -> {
			if (player.getName().equals("Martin")) {
				Assertions.assertTrue(!result.get(player));
			}
			
			if (player.getName().equals("Marta")) {
				Assertions.assertTrue(!result.get(player));
			}
			
			if (player.getName().equals("Jonathan")) {
				Assertions.assertTrue(result.get(player));
			}
			
			if (player.getName().equals("Patricia")) {
				Assertions.assertTrue(result.get(player));
			}
		});
	}

	@Test
	public void getAnswerFrequencyReturnsEmptyMapGivenEmptyMap() {
		//Arrange
		Map<Player, AnswerOption> frequency = Collections.emptyMap();
		
		//Act
		Map<AnswerOption, Integer> result = subject.getAnswerFrequency(frequency);
		
		//Assert
		Assertions.assertTrue(result.isEmpty());
	}
	
	@Test
	public void getAnswerFrequencyReturnsCorrectFrequency() {
		//Arrange
		Map<Player, AnswerOption> playerAnswers = playerAnswers();
		
		//Act
		Map<AnswerOption, Integer> result = subject.getAnswerFrequency(playerAnswers);
		
		//Assert
		Assertions.assertTrue(
				result.get(AnswerOption.A) == 2
				&& result.get(AnswerOption.B) == 1
				&& result.get(AnswerOption.C) == 1);	
	}
	
	@Test
	public void getMostCommonAnswerReturnsMajorityRulesWhenEmpty() {
		//Arrange
		Map<AnswerOption, Integer> answerFrequency = Collections.emptyMap();
		
		//Act
		AnswerOption result = subject.getMostCommonAnswer(answerFrequency);
		
		//Assert
		Assertions.assertTrue(result.equals(AnswerOption.MAJORITY_RULES));
	}
	
	@Test
	public void getMostCommonAnswerReturnsMostCommonAnswer() {
		//Arrange
		Map<AnswerOption, Integer> answerFrequency = answerFrequency();
		
		//Act
		AnswerOption result = subject.getMostCommonAnswer(answerFrequency);
		
		//Assert
		Assertions.assertTrue(result.equals(AnswerOption.A));
	}
	
	private Map<Player, AnswerOption> playerAnswers() {
		Map<Player, AnswerOption> map = new HashMap<>();
		
		map.put(new Player("Jonathan"), AnswerOption.A);
		map.put(new Player("Martin"), AnswerOption.B);
		map.put(new Player("Patricia"), AnswerOption.A);
		map.put(new Player("Marta"), AnswerOption.C);
		
		return map;
	}
	
	private Map<AnswerOption, Integer> answerFrequency() {
		Map<AnswerOption, Integer> result = new HashMap<>();
		
		result.put(AnswerOption.A, 10);
		result.put(AnswerOption.B, 3);
		result.put(AnswerOption.C, 4);
		
		return result;
	}
	
	private Map<AnswerOption, String> answerOptions() {
		Map<AnswerOption, String> result = new HashMap<>();
		
		result.put(AnswerOption.A, "Apples");
		result.put(AnswerOption.B, "Bananas");
		result.put(AnswerOption.C, "Carrots");
		result.put(AnswerOption.D, "Dragonfruit");
		
		return result;
	}
}
