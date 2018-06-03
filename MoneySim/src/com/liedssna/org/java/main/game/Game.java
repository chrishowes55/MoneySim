package com.liedssna.org.java.main.game;

import java.util.HashMap;
import java.util.LinkedHashMap;

import com.liedssna.org.java.main.display.Display;

public class Game {
	
	private LinkedHashMap<String, Business> businesses = new LinkedHashMap<String, Business>();
	private boolean gameIsRunning = true;
	private Player player;
	private Display display;
	
	public Game() {
		player = new Player("Chris");
		popBusinesses();
		display = new Display("MyMoney", player, this);
		display.setVisible(true);
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

	public HashMap<String, Business> getBusinesses() {
		return businesses;
	}
	
	

}
