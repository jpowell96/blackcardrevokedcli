package main.java.card;

import java.util.Map;

import main.java.player.Player;

public class MajorityRulesCard extends Card {
	
	public MajorityRulesCard(String question, Map<AnswerOption, String> answers) {
		super(question, answers, AnswerOption.MAJORITY_RULES.toString());
		this.answerStrategy = new MajorityRulesStrategy();
	}
	
	@Override
	public Map<Player, Boolean> correctAnswers(Map<Player, AnswerOption> playerAnswers) {
		Map<AnswerOption, Integer> answerFrequency = 
				((MajorityRulesStrategy) answerStrategy).getAnswerFrequency(playerAnswers);
		AnswerOption mostCommonAnswer = 
				((MajorityRulesStrategy) answerStrategy).getMostCommonAnswer(answerFrequency);
		this.setAnswer(answers.get(mostCommonAnswer));
		return super.correctAnswers(playerAnswers);
	}
}