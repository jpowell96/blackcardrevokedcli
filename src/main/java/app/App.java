package main.java.app;
 
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import main.java.game.Game;

public class App {
	
	public static void main(String[] args) {
		//print a welcome
		System.out.println("Welcome to Black Card Revoked!");
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			String line = null;
			System.out.println(menuOptions());
			System.out.print("Menu Option: ");

			while ((line = reader.readLine()) != null) {
				line = line.trim();
				if (isPlay(line)) {
					Game game = new Game();
					game.play();
				}
				
				if (isQuit(line)) {
					System.exit(0);
				}
				
				if (isMenu(line)) {
					System.out.println(menuOptions());
				}
				System.out.print("Menu Option: ");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		Game game = new Game();
		game.play();
	}
	private static boolean isPlay(String input) {
		return input.equals("play");
	}

	private static boolean isMenu(String input) {
		return input.equals("menu") || input.equals("m");
	}
	
	private static boolean isQuit(String input) {
		return input.equals("q") || input.equals("quit");
	}
	
	
	private static String menuOptions() {
		StringBuilder sb = new StringBuilder();
		String play = "Type 'play' to start a game";
		String about = "Type 'about' to learn about the game";
		String menu = "Type 'menu' or 'm' for the menu options";
		String quit = "Type 'q' or 'quit' to quit";

		sb.append("==================\nMenu\n==================\n");
		sb.append(String.format("1. %s\n", play));
		sb.append(String.format("2. %s\n", about));
		sb.append(String.format("3. %s\n", menu));
		sb.append(String.format("4. %s\n", quit));


		
		return sb.toString();
	}
}
