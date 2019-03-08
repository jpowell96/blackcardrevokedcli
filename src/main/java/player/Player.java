package main.java.player;

import java.util.UUID;

public class Player {
	private String name;
	private UUID uuid;
	
	public Player(String name) {
		this.name = name;
		this.uuid = UUID.randomUUID();
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
}
