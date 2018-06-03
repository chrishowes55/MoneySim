package com.liedssna.org.java.main.game;

public class Business {
	
	private String name;
	private int num;
	
	public Business(String name, int num) {
		this.name = name;
		this.num = num;
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

}
