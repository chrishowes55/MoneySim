package com.liedssna.org.java.main.game;

public class Player {
	private String name;
	private int money;
	
	public Player(String name) {
		this.name = name;
		this.money = 0;
	}
	
	public void increaseMoney(int amount) {
		this.money += amount;
	}

	public String getName() {
		return name;
	}

	public int getMoney() {
		return money;
	}
	
	public boolean decreaseMoney(int amount) {
		if (this.money - amount < 0) {
			return false;
		}
		this.money -= amount;
		return true;
	}

}
