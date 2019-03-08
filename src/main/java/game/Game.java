package main.java.game;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import main.java.card.AnswerOption;
import main.java.card.Card;
import main.java.card.QuestionGenerator;
import main.java.player.Player;

public class Game {
	List<Player> players;
	Map<Player, Integer> score = new HashMap<>();
	BufferedReader reader;
	
	
	public void play() {
		//1. Register players
		reader = new BufferedReader(new InputStreamReader(System.in));
		this.players = registerPlayers();
		
		players.forEach(player -> {
			score.put(player, 0);
		});
		//3. Iterate through tickets and get answers
		startGame(players, getQuestions());
		
	}
	
	private void startGame(List<Player> players, Deque<Card> questions) {
		
		while (!questions.isEmpty()) {
			Card question = questions.pop();
			
			//1. Display the card
			System.out.println(question);
			
			//2. Get Answers from each player:
			Map<Player, AnswerOption> answers = acceptAnswers(players, question);
		
			//3. Calculate Correct Answers and Update the score
			score = updateScore(question.correctAnswers(answers), score);
			
			//4. Display the correct Answer
			displayCorrectAnswer(question);
			
			//5. Display the Scoreboard
			displayScoreBoard();
		}
		
		//6. Tell who won
	}
	
	public List<Player> registerPlayers() {
		System.out.println("Who is playing?");
		System.out.println("Type 'done' when finished or 'y' when prompted.");
		List<Player> players = new ArrayList<>();
		try {
			
			boolean registeredAllPlayers = false;
			while (!registeredAllPlayers) {
				System.out.print("Enter name of player:");
				
				String userInput = reader.readLine().trim();
				if (userInput.equals("done")) {
					if (players.size() < 2) {
						System.out.println(String.format("There's %d players right now. At least %d players required.", players.size(), 1));
						continue;
					} else {
						break;
					}
				}
				if (!userInput.isBlank()) {
					players.add(new Player(userInput));
					System.out.println(String.format("Player %s has been added.", userInput));
					System.out.print("Done? (y/n): ");
					registeredAllPlayers = reader.readLine().trim().equalsIgnoreCase("y");
				}
			}
			
			System.out.println("All players registered.");
			
			for (int i = 0; i < players.size(); i++) {
				System.out.println(String.format("%d. %s", i + 1, players.get(i).getName()));
			}
			
			return players;
		} catch (IOException e) {
			System.out.println("Error encountered reading player name");
			e.printStackTrace();
		}
		return Collections.emptyList(); 
	}
	
	private Map<Player, AnswerOption> acceptAnswers(List<Player> players, Card card) {
		Set<String> possibleAnswers = new HashSet<String>(Arrays.asList("a","b","c","d", "q"));

		//1. For each player, get their answer and put it in the map
		Map<Player, AnswerOption> answers = new HashMap<>();
		try {
			System.out.println("Enter your answer (a,b,c,d). Type 'q' to display the question again.");

			for (int i = 0; i < players.size(); i++) {
				System.out.print(String.format("%s's answer: ", players.get(i).getName()));
				String answer = reader.readLine().trim();
				
				if (!possibleAnswers.contains(answer.toLowerCase())) {
					System.out.println(String.format("Answer: %s, is not an option. Enter a, b, c, or d.", answer));
					//stay at the current player if they dont give a valid answer
					i = i - 1;
				} else if (answer.equalsIgnoreCase("q")) {
					System.out.println(card.toString());
					//stay at the current player if they dont give a valid answer
					i = i - 1;
				} else {
					answers.put(players.get(i), AnswerOption.valueOf(answer.toUpperCase()));
				}
			}
			return answers;
			
		} catch (IOException e) {
			System.out.println("Error encountered when accepting answers");
			e.printStackTrace();
		}
		
		return Collections.emptyMap();
	}
	
	public Map<Player, Integer> updateScore(Map<Player, Boolean> correctAnswers, Map<Player, Integer> scores) {
		Map<Player, Integer> updatedScores = new HashMap<>();
		correctAnswers.forEach((player, isCorrectAnswer) -> {
			int score = isCorrectAnswer ? scores.get(player) + 1 : scores.get(player);
			updatedScores.put(player, score);
		});
		
		return updatedScores;
	}
	
	public Deque<Card> getQuestions() {
		return QuestionGenerator.getQuestions();
	}
	
	private void displayCorrectAnswer(Card card) {
		System.out.println(card.getAnswer());
		System.out.println(String.format("\n-------------\n The correct answer is: %s \n-------------\n", card.getAnswer()));
	}
	
	private void displayScoreBoard() {
		String LINE_SPACING = "-----------------------------";
		System.out.println(LINE_SPACING);
		System.out.println(" Player    |  Score ");
		System.out.println(LINE_SPACING);

		for (Entry<Player, Integer> playerScore : score.entrySet()) {
			System.out.println(String.format("%s  %d", playerScore.getKey().getName(), playerScore.getValue()));
		}
	}
	
}
