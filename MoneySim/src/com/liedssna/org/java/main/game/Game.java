package com.liedssna.org.java.main.game;

import com.liedssna.org.java.main.display.Display;

public class Game {
	
	private boolean gameIsRunning = true;
	private Player player;
	private Display display;
	
	public Game() {
		player = new Player("Chris");
		display = new Display("MyMoney", player, this);
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
			player.increaseMoney(1);
			display.updateDisplay();
		}
		display.setVisible(false);
		display.dispose();
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
