package com.liedssna.org.java.main.game;

public class Business {

	private String name;
	private int num, starterMoney, secs, time, starterPrice;
	private long lasttime = System.currentTimeMillis();

	public Business(String name, int num, int starterMoney, int secs, int starterPrice) {
		this.name = name;
		this.num = num;
		this.starterMoney = starterMoney;
		this.secs = secs;
		this.time = secs;
		this.starterPrice = starterPrice;
	}

	public void addToNum(int amount) {
		this.num += amount;
	}

	public String getName() {
		return name;
	}

	public int getNum() {
		return num;
	}

	public int getMoney(Game game) {
		return num * starterMoney * game.getOverallMultiplier();
	}

	public int getDisplayPrice() {
		return num * starterPrice;
	}

	public int getBuyPrice() {
		return num * starterPrice;
	}

	private void reset() {
		lasttime = System.currentTimeMillis();
	}

	public boolean hasTimePassed() {
		if ((System.currentTimeMillis() - lasttime) / 1000 >= secs) {
			reset();
			return true;
		}
		return false;
	}

	public void updateTime() {
		if (time <= 1) {
			time = this.secs;
		} else {
			time--;
		}
	}

	public int getTime() {
		return this.time;
	}

}
