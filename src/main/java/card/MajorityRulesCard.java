package main.java.card;

import java.util.Map;

import main.java.player.Player;

public class MajorityRulesCard extends Card {
	private final String LINE_SPACING = "-----------------------";

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
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(LINE_SPACING);
		sb.append('\n');
		sb.append("Majority Rules Question:");
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