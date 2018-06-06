package com.liedssna.org.java.main.game;

import java.util.HashMap;
import java.util.LinkedHashMap;

import com.liedssna.org.java.main.display.Display;

public class Game {
	
	private LinkedHashMap<Integer, Business> businesses = new LinkedHashMap<Integer, Business>();
	private boolean gameIsRunning = true;
	private Player player;
	private Display display;
	private long lasttime = System.currentTimeMillis();
	
	public Game() {
		player = new Player("Chris");
		popBusinesses();
		display = new Display("MyMoney", player, this);
		display.setVisible(true);
		gameLoop();
	}	
	
	public void popBusinesses() {
		businesses.put(0, new Business("Ozan", 1, 10, 2, 5));
		businesses.put(1, new Business("Legs", 2, 14, 4, 10));
	}
	
	public void gameLoop() {
		while (true) {
			if (!gameIsRunning) {
				System.out.println("Not running");
				saveGame();
				break;
			}
			if (hasSecondPassed()) {
				for(int i = 0; i < businesses.size(); i++) {
					businesses.get((Integer)i).updateTime();
					display.updateTimes();
				}
			}
			for (int i = 0; i < businesses.size(); i++) {
				if (businesses.get((Integer)i).hasTimePassed()) {
					player.increaseMoney(businesses.get((Integer)i).getMoney());
					System.out.println("Player money increased by: " + businesses.get((Integer)i).getMoney() + " from " + businesses.get((Integer)i).getName());
				}
			}
			display.updateDisplay();
		}
		display.setVisible(false);
		display.dispose();
	}
	
	private void reset() {
		this.lasttime = System.currentTimeMillis();
	}
	
	private boolean hasSecondPassed() {
		long delta = System.currentTimeMillis() - this.lasttime;
		if (delta/1000 >= 1) {
			reset();
			return true;
		}
		return false;
	}
	
	public void saveGame() {
		System.out.println("SAVED");
	}

	public void setGameIsRunning(boolean gameIsRunning) {
		System.out.println("Set");
		this.gameIsRunning = gameIsRunning;
		System.out.println(this.gameIsRunning);
	}
	
	public void increaseNumOfBusiness(Integer key, int num) {
		if (player.decreaseMoney(businesses.get(key).getBuyPrice())) {
			businesses.get(key).addToNum(num);
			display.updatePrices((int)key);
		}
		else {
			display.noBuy();
		}
	}

	public LinkedHashMap<Integer, Business> getBusinesses() {
		return businesses;
	}
	
	

}
