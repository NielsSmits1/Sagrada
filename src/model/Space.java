package model;

import java.util.ArrayList;

import Database.db;

public class Space {
	private int xPos;
	private int yPos;
	private int eyes;
	private String color;
	///*
		//Space contains the x and y positions for the DicePane add the gridPane. Also contains the amount of eyes and the color of the dicePane.
		///**
	
	public Space() {
		xPos = 0;
		yPos = 0;
		eyes = 0;
		color = "";
	}
	
	public void setXPOS(int i) {
		xPos = i;
		}
	
	public void setYPOS(int i) {
		yPos = i;
		}
	
	public void setEyes(int i) {
		eyes = i;
		}
	
	public void setColor(String c) {
		color = c;
		}
	
	public int getXPos() {
		return xPos;
	}
	
	public int getYPos() {
		return yPos;
	}
	
	public int getEyes() {
		return eyes;
	}
	
	public String getColor() {
		return color;
	}
	
	 
}
