package test.java.card;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import main.java.card.AnswerOption;
import main.java.card.MajorityRulesCard;
import main.java.player.Player;

public class MajorityRulesCardTest {
	
	private MajorityRulesCard subject;
	
	@BeforeEach
	public void init() {
		subject = new MajorityRulesCard("What is the best fruit?", answers());
	}
	
	@Test
	public void testCorrectAnswers() {
		//Arrange
		Map<Player, AnswerOption> playerAnswers = playerAnswers();
		
		//Act
		Map<Player, Boolean> result = subject.correctAnswers(playerAnswers);
		
		//Assert
		result.forEach((player, isCorrect) -> {
			Assertions.assertTrue(isCorrect);
		});
		
	}
	
	private Map<AnswerOption, String> answers() {
		Map<AnswerOption, String> result = new HashMap<>();
		
		result.put(AnswerOption.A, "Apple");
		result.put(AnswerOption.B, "Bananas");
		
		return result;
	}
	
	private Map<Player, AnswerOption>  playerAnswers() {
		Map<Player, AnswerOption> result = new HashMap<>();
		Player a = new Player("Jonathan");
		Player b = new Player("Alva");
		result.put(a, AnswerOption.A);
		result.put(b, AnswerOption.A);
		return result;
	}
}
