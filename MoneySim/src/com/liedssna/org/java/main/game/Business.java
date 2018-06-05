package com.liedssna.org.java.main.game;

public class Business {
	
	private String name;
	private int num, starterMoney, secs;
	private long lasttime = System.currentTimeMillis();
	
	public Business(String name, int num, int starterMoney, int secs) {
		this.name = name;
		this.num = num;
		this.starterMoney = starterMoney;
		this.secs = secs;
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
	
	public int getMoney() {
		return num * starterMoney;
	}
	
	private void reset() {
		lasttime = System.currentTimeMillis();
	}
	
	public boolean hasTimePassed() {
		if ((System.currentTimeMillis() - lasttime)/1000 >= secs) {
			reset();
			return true;
		}
		return false;
	}

}
