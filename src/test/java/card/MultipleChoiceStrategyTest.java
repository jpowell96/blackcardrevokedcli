package test.java.card;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import main.java.card.AnswerOption;
import main.java.card.MultipleChoiceStrategy;
import main.java.player.Player;

public class MultipleChoiceStrategyTest {
	
	private MultipleChoiceStrategy subject;
	
	@BeforeEach
	public void init() {
		subject = new MultipleChoiceStrategy();
	}
	
	@Test
	public void calculateRightAnswerReturnsCorrectAnswer() {
		//Arrange
		Map<Player, AnswerOption> playerAnswers = playerAnswers();
		Map<AnswerOption, String> answerOptions = answerOptions();
		String rightAnswer = "Apples";
		
		//Act
		Map<Player, Boolean> result = subject.calculateRightAnswer(playerAnswers, answerOptions, rightAnswer);
		
		//Assert
		result.forEach((player, isCorrect) -> {
			if (player.getName().equals("Jonathan")) {
				Assertions.assertTrue(isCorrect);
			}
			
			if (player.getName().equals("Martin")) {
				Assertions.assertTrue(!isCorrect);
			}
		});
	}
	
	private Map<Player, AnswerOption> playerAnswers() {
		Map<Player, AnswerOption> map = new HashMap<>();
		
		map.put(new Player("Jonathan"), AnswerOption.A);
		map.put(new Player("Martin"), AnswerOption.B);

		
		return map;
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
