package com.liedssna.org.java.main.game;

import com.liedssna.org.java.main.display.Display;

public class Game {
	
	private boolean gameIsRunning = true;
	
	public Game() {
		Display display = new Display("MyMoney", new Player("Chris"), this);
		display.setVisible(true);
		gameLoop();
	}	

	public void gameLoop() {
		while (true) {
			if (!gameIsRunning) {
				System.out.println("Not running");
				saveGame();
				break;
			}
			System.out.println("Trying");
		}
	}
	
	public void saveGame() {
		System.out.println("SAVED");
	}

	public void setGameIsRunning(boolean gameIsRunning) {
		System.out.println("Set");
		this.gameIsRunning = gameIsRunning;
		System.out.println(this.gameIsRunning);
	}
	
	

}
