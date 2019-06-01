package View;

import java.util.ArrayList;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public class DicePane extends Pane {
	//constants
	final static int CIRCLEDIA = 5;
	final static Color STROKECOLOR = Color.BLACK;
	final static Color DICEFILLCOLOR = Color.WHITE;
	final static double STROKEWIDTH = 1.5;
	
	//instance variables
	private Rectangle dice;
	private Circle one;
	private ArrayList<Circle> two;
	private ArrayList<Circle> three;
	private ArrayList<Circle> four;
	private ArrayList<Circle> five;
	private ArrayList<Circle> six;
	private GamePane gamePane;
	private int value;
	private String color;
	private int dieNumber;

	/// *
	// This constructor only gets used by the privateCardPane
	/// **

	public DicePane() {
		dice = new Rectangle(0, 0, 50, 50);
		dice.setStroke(STROKECOLOR);
		getChildren().addAll(dice);
	}

	/// *
	// This constructor is used for setting the template in the PatternPane
	/// **

	public DicePane(int amount, String c) {
		value = amount;
		color = c;
		if (amount == 0) {
			dice = new Rectangle(0, 0, 50, 50);
			dice.setStroke(STROKECOLOR);
			getChildren().add(dice);
			setColor(c);
			return;
		}
		if (amount != 0) {
			dice = new Rectangle(0, 0, 50, 50);
			dice.setStroke(STROKECOLOR);
			getChildren().add(dice);
			addDiceEyes(amount);
			setColor(c);
			return;
		}
	}

	/// *
	// This constructor is used to make a dice.
	/// **

	public DicePane(int eyes, String color, int dienumber, GamePane rp) {
		value = eyes;
		this.color = color;
		dieNumber = dienumber;
		addDice(value, this.color);
		gamePane = rp;
		addEvent(this);	
	}
	
	public DicePane(boolean isPrivateCard) {
		if(isPrivateCard) {
			dice = new Rectangle(0, 0, 100, 100);
			dice.setStroke(STROKECOLOR);
			getChildren().addAll(dice);
		}
		
	}
	
	public DicePane(int eyes, String color, int dienumber) {
		value = eyes;
		this.color = color;
		dieNumber = dienumber;
		addDice(value, this.color);
		
	}

	/// *
	// Sets the color of the rectangle called dice.
	/// **



	public void setColor(String c) {
		switch(c) {
		case "blauw":dice.setFill(Color.BLUE);
					 break;
		case "geel":dice.setFill(Color.YELLOW);
		 			 break;		
		case "groen":dice.setFill(Color.GREEN);
		 			 break;
		case "rood":dice.setFill(Color.RED);
					 break;
		case "paars":dice.setFill(Color.PURPLE);
					 break;
		case "transparant":dice.setFill(Color.TRANSPARENT);
					 break;
		default: dice.setFill(DICEFILLCOLOR);
		}
		
	}

	public void setWhite() {
		setColor("");
	}
	
	public void setTransparent() {
		setColor("transparant");
	}

	/// *
	// This is used for making a dice.
	/// **

	public void addDice(int value, String c) {
		dice = new Rectangle(0, 0, 50, 50);
		dice.setStroke(STROKECOLOR);
		getChildren().add(dice);
		addDiceEyes(value);
		setColor(c);

	}
	

	/// *
	// This method is directly or indirectly used for making a template AND a dice.
	/// **
	public void addDiceEyes(int value) {
		if (value == 1) {
			one = new Circle(25, 25, CIRCLEDIA);
			one.setFill(DICEFILLCOLOR);
			one.setStroke(STROKECOLOR);
			one.setStrokeWidth(STROKEWIDTH);
			getChildren().addAll(one);
		}
		if (value == 2) {
			two = new ArrayList<>();
			two.add(new Circle(12.5, 12.5, CIRCLEDIA));
			two.add(new Circle(37.5, 37.5, CIRCLEDIA));
			for (int i = 0; i < two.size(); i++) {
				two.get(i).setFill(DICEFILLCOLOR);
				two.get(i).setStroke(STROKECOLOR);
				two.get(i).setStrokeWidth(STROKEWIDTH);
			}
			getChildren().addAll(two);
		}
		if (value == 3) {
			three = new ArrayList<>();
			three.add(new Circle(12.5, 12.5, CIRCLEDIA));
			three.add(new Circle(25, 25, CIRCLEDIA));
			three.add(new Circle(38.5, 38.5, CIRCLEDIA));
			for (int i = 0; i < three.size(); i++) {
				three.get(i).setFill(DICEFILLCOLOR);
				three.get(i).setStroke(STROKECOLOR);
				three.get(i).setStrokeWidth(STROKEWIDTH);
			}
			getChildren().addAll(three);

		}
		if (value == 4) {
			four = new ArrayList<>();
			four.add(new Circle(12.5, 14, CIRCLEDIA));
			four.add(new Circle(38, 14, CIRCLEDIA));
			four.add(new Circle(12.5, 38, CIRCLEDIA));
			four.add(new Circle(38, 38, CIRCLEDIA));
			for (int i = 0; i < four.size(); i++) {
				four.get(i).setFill(DICEFILLCOLOR);
				four.get(i).setStroke(STROKECOLOR);
				four.get(i).setStrokeWidth(STROKEWIDTH);
			}
			getChildren().addAll(four);
		}
		if (value == 5) {
			five = new ArrayList<>();
			five.add(new Circle(12.5, 12, CIRCLEDIA));
			five.add(new Circle(37.5, 12, CIRCLEDIA));
			five.add(new Circle(12.5, 40, CIRCLEDIA));
			five.add(new Circle(37.5, 40, CIRCLEDIA));
			five.add(new Circle(24.5, 25, CIRCLEDIA));
			for (int i = 0; i < five.size(); i++) {
				five.get(i).setFill(DICEFILLCOLOR);
				five.get(i).setStroke(STROKECOLOR);
				five.get(i).setStrokeWidth(STROKEWIDTH);
			}
			getChildren().addAll(five);
		}
		if (value == 6) {
			six = new ArrayList<>();
			six.add(new Circle(10, 10, CIRCLEDIA));
			six.add(new Circle(10, 25, CIRCLEDIA));
			six.add(new Circle(10, 40, CIRCLEDIA));
			six.add(new Circle(40, 10, CIRCLEDIA));
			six.add(new Circle(40, 25, CIRCLEDIA));
			six.add(new Circle(40, 40, CIRCLEDIA));
			for (int i = 0; i < six.size(); i++) {
				six.get(i).setFill(DICEFILLCOLOR);
				six.get(i).setStroke(STROKECOLOR);
				six.get(i).setStrokeWidth(STROKEWIDTH);
			}
			getChildren().addAll(six);
		}
	}

	public void setValue(int newValue) {
		value = newValue;
	}
	public void removeEyes() {
		for (int i = getChildren().size() - 1; i > 0; i--) {
			getChildren().remove(i);
		}
	}

	public void addEvent(DicePane p) {
		setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				gamePane.setSelected(p);
			}
		});
	}

	public int getValue() {
		return value;
	}

	public String getColor() {
		return color;
	}

	public int getDieNumber() {
		return dieNumber;
	}

}
