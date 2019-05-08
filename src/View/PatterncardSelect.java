package View;

import java.util.ArrayList;
import java.util.Random;

import controller.BoardController;
import controller.MyScene;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import model.Space;

public class PatterncardSelect extends Pane {
	private ArrayList<PatternPane> board;
	private BoardController controller;
	private GridPane field1;
	private GridPane field2;
	private GridPane field3;
	private GridPane field4;
	private int patternid1;
	private int patternid2;
	private int patternid3;
	private int patternid4;
	private Random r;
	private RootPane rootPane;
	private MyScene scene;
 
	
	public PatterncardSelect(MyScene s) {
		super();
		scene = s;
		r = new Random();
		
		setGrid();
		
		controller = new BoardController();
		patternid1 = r.nextInt(11)+1;
		controller.setPatternId(patternid1);
		setBoard(field1);
		
		
		patternid2 = r.nextInt(11)+1;
		while (patternid2 == patternid1) {
			patternid2 = r.nextInt(11)+1;
		}
		controller.setPatternId(patternid2);
		setBoard(field2);
		
		
		patternid3 = r.nextInt(11)+1;
		while (patternid3 == patternid1 || patternid3 == patternid2) {
			patternid3 = r.nextInt(11)+1;
		}
		controller.setPatternId(patternid3);
		setBoard(field3);
		
		patternid4 = r.nextInt(11)+1;
		while (patternid4 == patternid1 || patternid4 == patternid2 || patternid4 == patternid3) {
			patternid4 = r.nextInt(11)+1;
		}
		controller.setPatternId(patternid4);
		setBoard(field4);
		
		GetPattern();
		
	}
	
	public void GetPattern() {
		
		
//		
//		patterncards = new HBox(option1, option2, option3, option4);
		//patterncards.setSpacing(20);
		//patterncards.setPadding(new Insets(0, 0, 0, 50));
		

	}
	
	///*
		//Sets the GridPane called field that represents the pattern.
		///**
		
	private void setGrid() {
		field1 = new GridPane();
		
		field1.setVgap(8);
		field1.setHgap(8);
		
		field1.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				rootPane = new RootPane(patternid1);
				scene.setRoot(rootPane);
			}
			
		});
		
		field2 = new GridPane();
		
		field2.setVgap(8);
		field2.setHgap(8);
		field2.setLayoutX(500);
		field2.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				rootPane = new RootPane(patternid2);
				scene.setRoot(rootPane);
			}
			
		});
		
		field3 = new GridPane();
		
		field3.setVgap(8);
		field3.setHgap(8);
		field3.setLayoutX(1000);
		field3.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				rootPane = new RootPane(patternid3);
				scene.setRoot(rootPane);
			}
			
		});
		
		field4 = new GridPane();
		
		field4.setVgap(8);
		field4.setHgap(8);
		field4.setLayoutX(1500);
		field4.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				rootPane = new RootPane(patternid4);
				scene.setRoot(rootPane);
			}
			
		});
	}
		
	///*
		//This might seem quite complex.
		//counter counts all the actual dices. The gridPane AKA field gets filled vertically. Firstly board adds the patternPane to itsself. Go to PatternPane if you want the constructor explained.
		//This dicePane constructor asks for the amount of eyes and the color. The amount can be 0 because it is a template and not an actual dice.
		//after this step field adds the last record of the ArrayList board to the grid. All of the position come straight from the class Space, which got them out of the DB.
		///**

			
		private void setBoard(GridPane pane) {
			int counter = 0;
			board = new ArrayList<>();
			for(int c = 1;c<=5;c++) {
				for(int i = 0; i<4;i++) {
						board.add(new PatternPane(new DicePane(getPatternField().get(counter).getEyes(), getPatternField().get(counter).getColor()), counter));
						pane.add(board.get(board.size()-1), getPatternField().get(counter).getXPos(), getPatternField().get(counter).getYPos());
						counter++;
					}
			}
			getChildren().add(pane);
			board.removeAll(board);
		}
		
		///*
			//Returns the ArrayList with Spaces.
			///**
			
		public ArrayList<Space> getPatternField() {
			return controller.getPatternField();
		}
	
}

