package model;

public class Dice {

	private int dieNumber;
	private String dieColor;
	private int eyes;
	///*
		//Dice contains the dieNumber, this number is unique. It also contains the amount of eyes and the color of the dice.
		///**
	public Dice() {
		dieNumber = 0;
		dieColor = "";
		eyes = 0;
	}

	public int getDieNumber() {
		return dieNumber;
	}

	public void setDieNumber(int dieNumber) {
		this.dieNumber = dieNumber;
	}

	public String getDieColor() {
		return dieColor;
	}

	public void setDieColor(String dieColor) {
		this.dieColor = dieColor;
	}

	public int getEyes() {
		return eyes;
	}

	public void setEyes(int eyes) {
		this.eyes = eyes;
	}
}
