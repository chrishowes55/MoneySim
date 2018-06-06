package com.liedssna.org.java.main.game;

public class Upgrade {

	private String name;
	private int multiplier, price;

	public Upgrade(String name, int multiplier, int price) {
		this.name = name;
		this.multiplier = multiplier;
		this.price = price;
	}
	
	public int getPrice() {
		return this.price;
	}
	
	public int getMultiplier() {
		return this.multiplier;
	}
	
	public String getName() {
		return this.name;
	}
	
}
