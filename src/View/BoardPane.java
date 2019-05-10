package View;

import java.util.ArrayList;


import controller.BoardController;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.QuadCurve;
import javafx.scene.shape.Rectangle;
import model.Space;

public class BoardPane extends Pane{
	private QuadCurve top;
	private Rectangle square;
//	private ArrayList<DicePane> board;
	private ArrayList<PatternPane> board;
//	private DicePane selected;
	private GridPane field;
	private BoardController controller;
	private int patternid;
	private boolean transparent;
//	private Board b;
	
	
	///*
		//This constructor requires a rootPane to return the selected DicePane. It also asks for an int that it can give to the BoardController. This number stands for the number of the windowPattern.
		///**
	public BoardPane(BoardController bc) {
//		setPrefSize(s.getWidth()/4, s.getHeight() - 200);
//		transparent = true;
//		setPatternId(pattern);
		controller = bc;
//		controller.setPatternId(patternid);
		setShape();
		setGrid();
//		b = be;
		getChildren().addAll(top, square);
		setBoard();
		
		
		
		
	}
	
	///*
		//Sets the patternId.
		///**
	
	public void setPatternId(int i) {
		patternid = i;
	}
	
	///*
		//Returns the patternId.
		///**
	
	public int getPatternId() {
		return patternid;
	}
	
	///*
		//Sets the shape of the board, it has nothing to do with the actual windowPattern.
		///**
	
	//TODO THOSE NUMBERS MIGHT BE MOVED TO A NEW MODEL
	private void setShape() {
		top = new QuadCurve(0, 200, 200, -150, 400, 200);
		square = new Rectangle();
		square.setX(0);
		square.setY(200);
		square.setWidth(400);
		square.setHeight(500);
		square.setFill(Color.WHITE);
		square.setStroke(Color.BLACK);
		top.setStroke(Color.BLACK);
		top.setFill(Color.CHARTREUSE);
		top.setStrokeWidth(0.5);
	}
	
	///*
		//Sets the GridPane called field that represents the pattern.
		///**
	
	private void setGrid() {
		field = new GridPane();
		field.setLayoutX(square.getX());
		field.setLayoutY(square.getY() +70);
		field.setVgap(8);
		field.setHgap(8);
	}
	
//	public void setDice(DicePane p) {
//		board.get(2).setDice(p);
//		field.add(p, 2, 2);
//	}
	
//	public void setEye(int value, int numberOfEyes) {
//		board.get(value).addPatternEyes(numberOfEyes);
//	}
	
	
	///*
		//Sets the color of the PatternPane on the board.
		///**
	public void setColor(int value, String color) {
		board.get(value).setColor(color);
	}
	
	///*
		//Sets the color of the PatternPane to white;
		///**
	
	public void setWhite(int value) {
		board.get(value).setWhite();
	}
	
	///*
		//This might seem quite complex.
		//counter counts all the actual dices. The gridPane AKA field gets filled vertically. Firstly board adds the patternPane to itsself. Go to PatternPane if you want the constructor explained.
		//This dicePane constructor asks for the amount of eyes and the color. The amount can be 0 because it is a template and not an actual dice.
		//after this step field adds the last record of the ArrayList board to the grid. All of the position come straight from the class Space, which got them out of the DB.
		///**

	
	private void setBoard() {
		int counter = 0;
		board = new ArrayList<>();
		for(int c = 1;c<=5;c++) {
			for(int i = 0; i<4;i++) {
					board.add(new PatternPane(this,new DicePane(getPatternField().get(counter).getEyes(), getPatternField().get(counter).getColor()), getPatternField().get(counter).getXPos(), getPatternField().get(counter).getYPos()));
					field.add(board.get(board.size()-1), getPatternField().get(counter).getXPos(), getPatternField().get(counter).getYPos());
					counter++;
				}
		}
		getChildren().add(field);
//		System.out.println("Should have worked");
	}
	
	///*
		//Returns the ArrayList with Spaces.
		///**
	
	public ArrayList<Space> getPatternField() {
		return controller.getPatternCard();
		}
	
	
	
//	public void getClicked(PatternPane p) {
//		System.out.println("" + field.getRowIndex(p) + " " + field.getColumnIndex(p));
//	}
	
//	public void setSelected(DicePane p, boolean onOff) {
//		selected = p;
//		System.out.println("" + selected);
//	}
	
	///*
		//This is what rootPane is used for, to get the selected DicePane and to delete it. PatternPane uses those methods.
		///**
//TODO FIX THAT THE SELECTED DICE IS GIVEN THROUGH THE CONTROLLER, IF POSSIBLE
	public DicePane getSelected() {
		return controller.getSelected();
	}
	
	public void giveCords(int x, int y) {
		controller.validateMove(x, y);
	}
//	
//	public void deleteSelected() {
//		rootPane.deleteSelected();
//		
//	}
	
	//TODO THOSE CHECKS MIGHT BE MOVING TO THE MODEL, WHICH WILL CHECK WITH THE INFO OUT OF THE DB
	public boolean getNearDice(PatternPane p, DicePane s) {
		PatternPane upPane = null;
		PatternPane downPane = null;
		PatternPane leftPane = null;
		PatternPane rightPane = null;
		boolean up;
		boolean down;
		boolean left;
		boolean right;
		
		//TODO IN MODEL X & Y POSITIONS.
		if(p.getNumber()-1 >= 0 && board.get(p.getNumber()-1) != null && p.getNumber()-1 != 3 && p.getNumber()-1 != 7 && p.getNumber()-1 != 11 && p.getNumber()-1 != 15) {
			upPane = board.get(p.getNumber()-1);
		}
		if(p.getNumber()+1 <= 19 && board.get(p.getNumber()+1) != null && p.getNumber()+1 != 4 && p.getNumber()+1 != 8 && p.getNumber()+1 != 12 && p.getNumber()+1 != 16) {
			downPane = board.get(p.getNumber()+1);
			
		}
		if(p.getNumber()-4 >= 0 && board.get(p.getNumber()-4) != null) {
			leftPane = board.get(p.getNumber()-4);
			
		}
		if(p.getNumber()+4 <= 19 && board.get(p.getNumber()+4) != null) {
			rightPane = board.get(p.getNumber()+4);
			
		}
		
		//TODO IN MODEL VALUE & COLOR.
		if(upPane != null && upPane.getDice() != null && (upPane.getColor().equals(s.getColor()) || upPane.getEyes() == s.getValue())) {
			return false;
		}else {
			up = true;
			
		}
		if(downPane != null && downPane.getDice() != null && (downPane.getColor().equals(s.getColor()) || downPane.getEyes() == s.getValue())) {
			return false;
		}else {
			down = true;
			
		}
		if(leftPane != null && leftPane.getDice() != null && (leftPane.getColor().equals(s.getColor()) || leftPane.getEyes() == s.getValue())) {
			return false;
		}else {
			left = true;
			
		}
		if(rightPane != null && rightPane.getDice() != null && (rightPane.getColor().equals(s.getColor()) || rightPane.getEyes() == s.getValue())) {
			return false;
		}else {
			right = true;
			
		}
		
		if(up == true && down == true && left == true && right == true) {
			return true;
		}else {
			return false;
		}

	}
	
//	public void switchTransparent() {
//		transparent = !transparent;
//		setMouseTransparent(transparent);
//	}

}

	
