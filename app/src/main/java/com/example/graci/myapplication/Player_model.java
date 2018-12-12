package com.example.graci.myapplication;

public class Player_model {

	private String name;
	private int hp;
	private int currHp;
	private int strength;
	private int defense;
	private boolean isDefending;
	static int guardTimes = 0;
	static int atkTimes = 0;

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getHp() {
		return hp;
	}
	public void setHp(int hp) {
		this.hp = hp;
	}
	public int getStrength() {
		return strength;
	}
	public void setStrength(int strength) {
		this.strength = strength;
	}
	public int getDefense() {
		return defense;
	}
	public void setDefense(int defense) {
		this.defense = defense;
	}	
	public int getCurrHp() {
		return currHp;
	}
	public void setCurrHp(int currHp) {
		this.currHp = currHp;
	}
	public boolean isDefending() {
		return isDefending;
	}
	public void setDefend(boolean isDefending) {
		this.isDefending = isDefending;
	}

	public Player_model(String name, int hp, int currHp, int strength, int defense) {
		this.name = name;
		this.hp = hp;
		this.currHp = currHp;
		this.strength = strength;
		this.defense = defense;
	}
	
}
