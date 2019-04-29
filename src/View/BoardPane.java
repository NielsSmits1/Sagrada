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
	private DicePane selected;
	private GridPane field;
	private BoardController controller;
	private int patternid;
	private RootPane rootPane;
//	private Board b;
	
	
	///*
		//This constructor requires a rootPane to return the selected DicePane. It also asks for an int that it can give to the BoardController. This number stands for the number of the windowPattern.
		///**
	public BoardPane(RootPane rp, int pattern) {
//		setPrefSize(s.getWidth()/4, s.getHeight() - 200);
		setPatternId(pattern);
		controller = new BoardController(this);
		rootPane = rp;
		setShape();
		setGrid();
//		b = be;
		getChildren().addAll(top, square);
		setBoard2();
		
		
		
		
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

	
	private void setBoard2() {
		int counter = 0;
		board = new ArrayList<>();
		for(int c = 1;c<=5;c++) {
			for(int i = 0; i<4;i++) {
					board.add(new PatternPane(this,new DicePane(getPatternField().get(counter).getEyes(), getPatternField().get(counter).getColor())));
					field.add(board.get(board.size()-1), getPatternField().get(counter).getXPos(), getPatternField().get(counter).getYPos());
					counter++;
				}
		}
		getChildren().add(field);
	}
	
	///*
		//Returns the ArrayList with Spaces.
		///**
	
	public ArrayList<Space> getPatternField() {
		return controller.getPatternField();
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

	public DicePane getSelected() {
		return rootPane.getSelected();
	}
	
	public void deleteSelected() {
		rootPane.deleteSelected();
		
	}
	
//	public boolean getNearDice(PatternPane p, DicePane s) {
//		int patternRow = field.getRowIndex(p);
//		int patternColumn = field.getColumnIndex(p);
//		System.out.println("" + patternRow + " " + patternColumn);
//	
//		
//		PatternPane downPane = (PatternPane) getSideDie(patternRow+1,patternColumn);
//		PatternPane upPane = (PatternPane) getSideDie(patternRow-1,patternColumn);
//		PatternPane leftPane = (PatternPane) getSideDie(patternRow,patternColumn-1);
//		PatternPane rightPane = (PatternPane) getSideDie(patternRow,patternColumn+1);
//			
//			if(downPane.getDice() == null && upPane.getDice() == null && leftPane.getDice() == null && rightPane.getDice() == null) {
//				return true;
//			}
//			if(downPane.getDice() != null) {
//				if(s.getColor().equals(downPane.getDiceColor())){
//					return false;
//				}
//			}
//			if(upPane.getDice() != null) {
//				if(s.getColor().equals(upPane.getDiceColor())){
//					return false;
//				}
//			}
//			if(leftPane.getDice() != null) {
//				if(s.getColor().equals(leftPane.getDiceColor())){
//					return false;
//				}
//			}
//			if(rightPane.getDice() != null) {
//				if(s.getColor().equals(downPane.getDiceColor())){
//					return false;
//				}
//			}
//		return true;
//	}
	
//	private Node getSideDie(int col, int row) {
//	    for (Node node : field.getChildren()) {
//	        if (field.getColumnIndex(node) == col && field.getRowIndex(node) == row) {
//	            return node;
//	        }
//	    }
//	    return null;
//	}
}

	
