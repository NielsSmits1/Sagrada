package model;

import java.util.Random;

public class Dice {

	private int dieNumber;
	private String dieColor;
	private int eyes;
	private Random r;
	///*
		//Dice contains the dieNumber, this number is unique. It also contains the amount of eyes and the color of the dice.
		///**
	public Dice() {
		r = new Random();
		dieNumber = 0;
		dieColor = "";
		setEyes();
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

	public void setEyes() {
		eyes = r.nextInt((6))+1;
//		return eyes;
	}
}
