package com.liedssna.org.java.main.game;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import com.liedssna.org.java.main.display.Display;

public class Game {

	private LinkedHashMap<Integer, Business> businesses = new LinkedHashMap<Integer, Business>();
	private ArrayList<Upgrade> upgrades = new ArrayList<Upgrade>();
	private boolean gameIsRunning = true;
	private Player player;
	private Display display;
	private long lasttime = System.currentTimeMillis();
	private int overallMultiplier = 1;

	public Game() {
		player = new Player("Chris");
		popBusinesses();
		popUpgrades();
		display = new Display("MyMoney", player, this);
		display.setVisible(true);
		gameLoop();
	}

	public void popBusinesses() {
		businesses.put(0, new Business("Ozan", 1, 10, 2, 5));
		businesses.put(1, new Business("Legs", 2, 14, 4, 10));
		businesses.put(2, new Business("GIMME MONEY", 1, 1000, 1, 15));
	}

	public void popUpgrades() {
		upgrades.add(new Upgrade("2", 2, 100));
		upgrades.add(new Upgrade("3", 3, 1000));
		upgrades.add(new Upgrade("4", 4, 100000));
		upgrades.add(new Upgrade("5", 2, 10000000));
		upgrades.add(new Upgrade("6", 3, 100000000));
		upgrades.add(new Upgrade("7", 4, 1000000000));
	}

	public void gameLoop() {
		while (true) {
			if (!gameIsRunning) {
				System.out.println("Not running");
				saveGame();
				break;
			}
			if (hasSecondPassed()) {
				for (int i = 0; i < businesses.size(); i++) {
					businesses.get((Integer) i).updateTime();
					display.updateTimes();
				}
			}
			for (int i = 0; i < businesses.size(); i++) {
				if (businesses.get((Integer) i).hasTimePassed()) {
					player.increaseMoney(businesses.get((Integer) i).getMoney(this));
					System.out.println("Player money increased by: " + businesses.get((Integer) i).getMoney(this)
							+ " from " + businesses.get((Integer) i).getName());
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
		if (delta / 1000 >= 1) {
			reset();
			return true;
		}
		return false;
	}

	private void increaseOverallMultiplier(Upgrade upgrade) {
		overallMultiplier *= upgrade.getMultiplier();
	}

	public void buyUpgrade(int i) {
		if (player.decreaseMoney((upgrades.get(i).getPrice()))) {
			increaseOverallMultiplier(upgrades.get(i));
			upgrades.remove(i);
			display.updateUpgrades();
			System.out.println("Overall Multiplier = " + overallMultiplier);
		} else {
			display.noBuyUpgrade();
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

	public void increaseNumOfBusiness(Integer key, int num) {
		if (player.decreaseMoney(businesses.get(key).getBuyPrice())) {
			businesses.get(key).addToNum(num);
			display.updatePrices((int) key);
		} else {
			display.noBuyBusiness();
		}
	}

	public LinkedHashMap<Integer, Business> getBusinesses() {
		return businesses;
	}

	public ArrayList<Upgrade> getUpgrades() {
		return upgrades;
	}

	public int getOverallMultiplier() {
		return overallMultiplier;
	}

}
