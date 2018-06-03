package com.liedssna.org.java.main.game;

import java.util.ArrayList;
import java.util.HashMap;

import com.liedssna.org.java.main.display.Display;

public class Game {
	
	private HashMap<String, Business> businesses = new HashMap<String, Business>();
	private boolean gameIsRunning = true;
	private Player player;
	private Display display;
	
	public Game() {
		player = new Player("Chris");
		display = new Display("MyMoney", player, this);
		display.setVisible(true);
		popBusinesses();
		gameLoop();
	}	
	
	public void popBusinesses() {
		businesses.put("Ozan", new Business("Ozan", 1));
		businesses.put("Legs", new Business("Legs", 2));
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
	
	public void increaseNumOfBusiness(String key, int num) {
		businesses.get(key).addToNum(num);
	}

}
