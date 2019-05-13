package View;


import java.util.ArrayList;

import java.util.Random;

import controller.BoardController;
import controller.GameController;
import controller.MyScene;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import model.PatternCard;
import model.Space;

public class PatterncardSelect extends Pane {
	private ArrayList<PatternPane> board;
//	private GridPane field1;
//	private GridPane field2;
//	private GridPane field3;
//	private GridPane field4;
	private ArrayList<Integer> id;
	private int patternId;
	private ArrayList<GridPane> choice;
	private Button button;
	private GameController controller;
	final private Border peru = new Border(new BorderStroke(Color.PERU, BorderStrokeStyle.SOLID, null, new BorderWidths(10.0)));
	
	public PatterncardSelect(GameController gc) {
		super();
		controller = gc;
		id = new ArrayList<>();
		setGrid();
		button = new Button("Pick This one!");
		button.setOnAction(e -> handle());
		button.setDisable(true);
//		controller = new BoardController();
//		patternid1 = r.nextInt(23)+1;
//		controller.setPatternId(patternid1);
//		setBoard(field1);
//		
//		
//		patternid2 = r.nextInt(23)+1;
//		while (patternid2 == patternid1) {
//			patternid2 = r.nextInt(23)+1;
//		}
//		controller.setPatternId(patternid2);
//		setBoard(field2);
//		
//		
//		patternid3 = r.nextInt(23)+1;
//		while (patternid3 == patternid1 || patternid3 == patternid2) {
//			patternid3 = r.nextInt(23)+1;
//		}
//		controller.setPatternId(patternid3);
//		setBoard(field3);
//		
//		patternid4 = r.nextInt(23)+1;
//		while (patternid4 == patternid1 || patternid4 == patternid2 || patternid4 == patternid3) {
//			patternid4 = r.nextInt(23)+1;
//		}
//		controller.setPatternId(patternid4);
		setBoard();
		
	}
	
	
	///*
		//Sets the GridPane called field that represents the pattern.
		///**
		
	private void setGrid() {
		
		choice = new ArrayList<>();
		
		for (int i = 0; i < 4; i++) {
			choice.add(new GridPane());
		}
		for(int i = 0;i<choice.size();i++) {
			choice.get(i).setVgap(8);
			choice.get(i).setHgap(8);
		}
		choice.get(0).setOnMouseClicked(new EventHandler<MouseEvent>() {
			
			@Override
			public void handle(MouseEvent event) {
				patternId = id.get(0);
				for (int i = 0; i < 4; i++) {
					if(i == 0) {
						choice.get(i).setBorder(peru);
					}else {
						choice.get(i).setBorder(null);
					}
				}
				button.setDisable(false);
			}
			
		});
		choice.get(1).setOnMouseClicked(new EventHandler<MouseEvent>() {
			
			@Override
			public void handle(MouseEvent event) {
				patternId = id.get(1);
				for (int i = 0; i < 4; i++) {
					if(i == 1) {
						choice.get(i).setBorder(peru);
					}else {
						choice.get(i).setBorder(null);
					}
				}
				button.setDisable(false);
			}
			
		});
		choice.get(2).setOnMouseClicked(new EventHandler<MouseEvent>() {
			
			@Override
			public void handle(MouseEvent event) {
				patternId = id.get(2);
				for (int i = 0; i < 4; i++) {
					if(i == 2) {
						choice.get(i).setBorder(peru);
					}else {
						choice.get(i).setBorder(null);
					}
				}
				button.setDisable(false);
			}
			
		});
		choice.get(3).setOnMouseClicked(new EventHandler<MouseEvent>() {
			
			@Override
			public void handle(MouseEvent event) {
				patternId = id.get(3);
				for (int i = 0; i < 4; i++) {
					if(i == 3) {
						choice.get(i).setBorder(peru);
					}else {
						choice.get(i).setBorder(null);
					}
				}
				button.setDisable(false);
			}
			
		});
//		field1 = new GridPane();
//		
//		field1.setVgap(8);
//		field1.setHgap(8);
//		
//		field1.setOnMouseClicked(new EventHandler<MouseEvent>() {
//
//			@Override
//			public void handle(MouseEvent event) {
//				rootPane = new RootPane(patternid1);
//				scene.setRoot(rootPane);
//			}
//			
//		});
//		
//		field2 = new GridPane();
//		
//		field2.setVgap(8);
//		field2.setHgap(8);
//		field2.setLayoutX(500);
//		field2.setOnMouseClicked(new EventHandler<MouseEvent>() {
//
//			@Override
//			public void handle(MouseEvent event) {
//				rootPane = new RootPane(patternid2);
//				scene.setRoot(rootPane);
//			}
//			
//		});
//		
//		field3 = new GridPane();
//		
//		field3.setVgap(8);
//		field3.setHgap(8);
//		field3.setLayoutX(1000);
//		field3.setOnMouseClicked(new EventHandler<MouseEvent>() {
//
//			@Override
//			public void handle(MouseEvent event) {
//				rootPane = new RootPane(patternid3);
//				scene.setRoot(rootPane);
//			}
//			
//		});
//		
//		field4 = new GridPane();
//		
//		field4.setVgap(8);
//		field4.setHgap(8);
//		field4.setLayoutX(1500);
//		field4.setOnMouseClicked(new EventHandler<MouseEvent>() {
//
//			@Override
//			public void handle(MouseEvent event) {
//				rootPane = new RootPane(patternid4);
//				scene.setRoot(rootPane);
//			}
//			
//		});
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
			for (int j = 0; j < 4; j++) {
				counter = 0;
				for(int c = 1;c<=5;c++) {
					for(int i = 0; i<4;i++) {
							board.add(new PatternPane(new DicePane(getPatternCard().get(j).getPatternField().get(counter).getEyes(), getPatternCard().get(j).getPatternField().get(counter).getColor())));
							choice.get(j).add(board.get(board.size()-1), getPatternCard().get(j).getPatternField().get(counter).getXPos(), getPatternCard().get(j).getPatternField().get(counter).getYPos());
							counter++;
						}	
				}
				id.add(getPatternCard().get(j).getPatternId());
				board.removeAll(board);
			
			}
			HBox hBox = new HBox();
			for (int i = 0; i < choice.size(); i++) {
				hBox.getChildren().add(choice.get(i));
			}
			hBox.setSpacing(50.0);
			VBox box = new VBox(hBox, button);
			box.setSpacing(25.0);
			getChildren().addAll(box);
			
			
			
		}
		
		public int getChosenId() {
			if(patternId != 0) {
				return patternId;
			}
			return 0;
		}
		
		public void handle(){
			controller.setPatternCard(getChosenId());
		}
		
		public ArrayList<PatternCard> getPatternCard(){
			return controller.getPatternCardOptions();
		}
		
		
		
		///*
			//Returns the ArrayList with Spaces.
			///**
			
		
	
}

