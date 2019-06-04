package model;

public class PlacedDice extends Dice {

	private int x;
	private int y;

	public PlacedDice(int x, int y, int dienumber, String diecolor, int eyes) {
		this.x = x;
		this.y = y;
		setDieNumber(dienumber);
		setDieColor(diecolor);
		setEyes(eyes);
	}

	public int getXpos() {
		return x;
	}

	public int getYpos() {
		return y;
	}
}
