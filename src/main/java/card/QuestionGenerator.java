package main.java.card;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class QuestionGenerator {

	/* 
	 * /BlackCardRevoked/src/card/questions.txt
	 * 1. Reads in questions from questions.txt
	 * 2. Shuffle them
	 * 3. Return a deque of the cards
	 * 
	 * 
	 * */
	private static final String QUESTIONS_FILE_PATH = "questions.txt";
	
	public static Deque<Card> getQuestions() {
		InputStream questionsFile = QuestionGenerator.class.getResourceAsStream(QUESTIONS_FILE_PATH);
		try (BufferedReader questions = new BufferedReader(new InputStreamReader(questionsFile))) {
			Deque<Card> cards = questions.lines()
			.map(line -> csvToCard(line))
			.collect(Collectors.toCollection(LinkedList::new));
			questions.close();
			Collections.shuffle((List<Card>) cards);
			return cards;
			
		} catch (IOException e) {
			System.out.println("Error reading card file");
			e.printStackTrace();
		} 
		
		return new LinkedList<>();
	}
	
	private static Card csvToCard(String line) {
		String[] split = line.split(",");
		
		//1. Question type is always the first value
		String questionType = split[0];
		
		//2. Question is always the second value
		String question = split[1];
		
		//3. the correct answer is always the last value
		String correctAnswer = split[split.length - 1];
		
		//4. The answer options are the values from indices 1 - 4
		List<String> options = new ArrayList<>(4);
		
		for (int i = 2; i < 6; i++) {
			options.add(split[i]);
		}
		
		Collections.shuffle(options);
		
		Map<AnswerOption, String>  answerOptions = new TreeMap<>();
		
		answerOptions.put(AnswerOption.A, options.get(0));
		answerOptions.put(AnswerOption.B, options.get(1));
		answerOptions.put(AnswerOption.C, options.get(2));
		answerOptions.put(AnswerOption.D, options.get(3));
		
		if (isMultipleChoice(questionType)) {
			return new MultipleChoiceCard(question, answerOptions, correctAnswer);
		} else {
			return new MajorityRulesCard(question, answerOptions);
		}
		
	}
	
	private static boolean isMultipleChoice(String questionType) {
		return questionType.equalsIgnoreCase("mc");
	}
}
