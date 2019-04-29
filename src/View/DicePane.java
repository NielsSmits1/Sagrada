package View;

import java.util.ArrayList;


import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import model.Dice;

public class DicePane extends Pane{
	
	private Rectangle dice;
	private Circle one;
	private ArrayList<Circle> two;
	private ArrayList<Circle> three;
	private ArrayList<Circle> four;
	private ArrayList<Circle> five;
	private ArrayList<Circle> six;
	private BoardPane boardPane;
	private RootPane rootPane;
	private int value;
	private String color;
	
	///*
		//This constructor only gets used by the privateCardPane
		///**

	public DicePane() {
		dice = new Rectangle(0,0, 70, 70);
		dice.setStroke(Color.BLACK);
		getChildren().addAll(dice);

	}
	
	///*
		//This constructor is used for setting the template in the PatternPane
		///**
	
	public DicePane(int amount, String c) {
		value = amount;
		color = c;
		if(amount == 0) {
			dice = new Rectangle(0,0,70,70);
			dice.setStroke(Color.BLACK);
			getChildren().add(dice);
			setColor(c);
			return;
		}
		if(amount != 0) {
			dice = new Rectangle(0,0,70,70);
			dice.setStroke(Color.BLACK);
			getChildren().add(dice);
			addDiceEyes(amount);
			setColor(c);
			return;
		}

	}
	
	///*
		//This constructor is used to make a dice.
		///**
	
	public DicePane(Dice d) {
		value = d.getEyes();
		color = d.getDieColor();
		addDice(d.getEyes(), d.getDieColor());
//		rootPane = rp;
		addEvent(this);
	}
	
	///*
		//Sets the color of the rectangle called dice.
		///**
	
	public void setColor(String c) {
		dice.setFill(Color.valueOf(c));
	}
	
	public void setWhite() {
		setColor("WHITE");
	}
	
	///*
		//This is used for making a dice.
		///**
	
	public void addDice(int value, String c) {
		dice = new Rectangle(0,0,70,70);
		dice.setStroke(Color.BLACK);
		getChildren().add(dice);
		addDiceEyes(value);
		setColor(c);
		
		}
	
	///*
		//This method is directly or indirectly used for making a template AND a dice.
		///**
	public void addDiceEyes(int value) {
		if(value == 1) {
			one = new Circle(35, 35, 10);
			one.setFill(Color.WHITE);
			one.setStroke(Color.BLACK);
			one.setStrokeWidth(1.5);
			getChildren().addAll(one);
		}
		if(value == 2) {
			two = new ArrayList<>();
			two.add(new Circle(17.5, 17.5, 10));
			two.add(new Circle(52.5, 52.5, 10));
			for(int i = 0; i<two.size(); i++) {
				two.get(i).setFill(Color.WHITE);
				two.get(i).setStroke(Color.BLACK);
				two.get(i).setStrokeWidth(1.5);
			}
//			getChildren().addAll(dice);
			getChildren().addAll(two);
		}
		if(value == 3) {
			three = new ArrayList<>();
			three.add(new Circle(17.5, 17.5, 10));
			three.add(new Circle(35, 35, 10));
			three.add(new Circle(52.5, 52.5, 10));
			for(int i = 0; i<three.size(); i++) {
				three.get(i).setFill(Color.WHITE);
				three.get(i).setStroke(Color.BLACK);
				three.get(i).setStrokeWidth(1.5);
			}
//			getChildren().addAll(dice);
			getChildren().addAll(three);
			
		}
		if(value == 4) {
			four = new ArrayList<>();
			four.add(new Circle(17.5, 17.5, 10));
			four.add(new Circle(52.5, 17.5, 10));
			four.add(new Circle(17.5, 52.5, 10));
			four.add(new Circle(52.5, 52.5, 10));
			for(int i = 0; i<four.size(); i++) {
				four.get(i).setFill(Color.WHITE);
				four.get(i).setStroke(Color.BLACK);
				four.get(i).setStrokeWidth(1.5);
			}
//			getChildren().addAll(dice);
			getChildren().addAll(four);
		}
		if(value == 5) {
			five = new ArrayList<>();
			five.add(new Circle(17.5, 17.5, 10));
			five.add(new Circle(52.5, 17.5, 10));
			five.add(new Circle(17.5, 52.5, 10));
			five.add(new Circle(52.5, 52.5, 10));
			five.add(new Circle(35, 35, 10));
			for(int i = 0; i<five.size(); i++) {
				five.get(i).setFill(Color.WHITE);
				five.get(i).setStroke(Color.BLACK);
				five.get(i).setStrokeWidth(1.5);
			}
//			getChildren().addAll(dice);
			getChildren().addAll(five);
		}
		if(value == 6) {
			six = new ArrayList<>();
			six.add(new Circle(17.5, 13, 9));
			six.add(new Circle(17.5, 35, 9));
			six.add(new Circle(17.5, 57, 9));
			six.add(new Circle(52.5, 13, 9));
			six.add(new Circle(52.5, 35, 9));
			six.add(new Circle(52.5, 57, 9));
			for(int i = 0; i<six.size(); i++) {
				six.get(i).setFill(Color.WHITE);
				six.get(i).setStroke(Color.BLACK);
				six.get(i).setStrokeWidth(1.5);
			}
//			getChildren().addAll(dice);
			getChildren().addAll(six);
	}
	

		///*
		//This event is set only for the dicePane that become dices. When a dice is clicked is will become the selected rootPane.
		///**
		
	}
	public void addEvent(DicePane p) {
		setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				// TODO Auto-generated method stub
				rootPane.setSelected(p);
//				System.out.println("" + p);
			}
			
		});
	}
	
	public int getValue() {
		return value;
	}
	
	public String getColor() {
		return color;
	}
	
	
	
//	public DicePane getSelected() {
//		return boardPane.getSelected();
//	}
//	
	
}
