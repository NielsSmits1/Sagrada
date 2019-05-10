package View;

import java.util.ArrayList;
import java.util.Random;

import controller.BoardController;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import model.Space;

public class PatterncardSelect extends Pane {
	private ArrayList<PatternPane> board;
	private BoardController controller;
	private RootPane rootPane;
	private MyScene scene;
	
	private Random r;
	
	private GridPane field1;
	private GridPane field2;
	private GridPane field3;
	private GridPane field4;
	
	private int patternid1;
	private int patternid2;
	private int patternid3;
	private int patternid4;
	
	private Pane pattern1;
	private Pane pattern2;
	private Pane pattern3;
	private Pane pattern4;

	private int heightPosition = 375;
	private int paneHeight = 403;
	private int paneWidth = 324;
	
	private Text text1;
	private Text text2;
	private Text text3;
	private Text text4;
	
	
	public PatterncardSelect(MyScene s) {
		super();
		scene = s;
		
		this.setBackground(new Background(new BackgroundFill(Color.DARKGRAY, null, null)));
		
		r = new Random();
		
		setGrid();
		
		pattern1 = new Pane();
		pattern2 = new Pane();
		pattern3 = new Pane();
		pattern4 = new Pane();
		
		text1 = new Text("Kaart 1");
		text2 = new Text("kaart 2");
		text3 = new Text("kaart 3");
		text4 = new Text("kaart 4");
		
		controller = new BoardController();
		patternid1 = r.nextInt(23)+1;
		controller.setPatternId(patternid1);
		setBoard(field1);
		
		
		patternid2 = r.nextInt(23)+1;
		while (patternid2 == patternid1) {
			patternid2 = r.nextInt(23)+1;
		}
		controller.setPatternId(patternid2);
		setBoard(field2);
		
		
		patternid3 = r.nextInt(23)+1;
		while (patternid3 == patternid1 || patternid3 == patternid2) {
			patternid3 = r.nextInt(23)+1;
		}
		controller.setPatternId(patternid3);
		setBoard(field3);
		
		patternid4 = r.nextInt(23)+1;
		while (patternid4 == patternid1 || patternid4 == patternid2 || patternid4 == patternid3) {
			patternid4 = r.nextInt(23)+1;
		}
		controller.setPatternId(patternid4);
		setBoard(field4);
		
		
		pattern1.setBackground(new Background(new BackgroundFill(Color.HOTPINK, null, null)));
		pattern1.setPrefSize(paneHeight, paneWidth);
		pattern1.setMaxSize(paneHeight, paneWidth);
		pattern1.setMinSize(paneHeight, paneWidth);
		pattern1.setLayoutX(50);
		pattern1.setLayoutY(heightPosition);
		pattern1.getChildren().addAll(field1, text1);
		
		
		
		pattern2.setBackground(new Background(new BackgroundFill(Color.HOTPINK, null, null)));
		pattern2.setPrefSize(paneHeight, paneWidth);
		pattern2.setMaxSize(paneHeight, paneWidth);
		pattern2.setMinSize(paneHeight, paneWidth);
		pattern2.setLayoutX(525);
		pattern2.setLayoutY(heightPosition);
		pattern2.getChildren().addAll(field2, text2);
		
		
		
		pattern3.setBackground(new Background(new BackgroundFill(Color.HOTPINK, null, null)));
		pattern3.setPrefSize(paneHeight, paneWidth);
		pattern3.setMaxSize(paneHeight, paneWidth);
		pattern3.setMinSize(paneHeight, paneWidth);
		pattern3.setLayoutX(1000);
		pattern3.setLayoutY(heightPosition);
		pattern3.getChildren().addAll(field3, text3);
		
		
		
		pattern4.setBackground(new Background(new BackgroundFill(Color.HOTPINK, null, null)));
		pattern4.setPrefSize(paneHeight, paneWidth);
		pattern4.setMaxSize(paneHeight, paneWidth);
		pattern4.setMinSize(paneHeight, paneWidth);
		pattern4.setLayoutX(1450);
		pattern4.setLayoutY(heightPosition);
		pattern4.getChildren().addAll(field4, text4);
		
		
		this.getChildren().addAll(pattern1, pattern2, pattern3, pattern4);
		
	}

	///*
		//Sets the GridPane called field that represents the pattern.
		///**
		
	private void setGrid() {
		field1 = new GridPane();
		
		field1.setVgap(8);
		field1.setHgap(8);
//		field1.setLayoutX(50);
//		field1.setLayoutY(patternHeight);
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
//		field2.setLayoutX(525);
//		field2.setLayoutY(heightPosition);
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
		
//		field3.setLayoutX(1000);
//		field3.setLayoutY(heightPosition);
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
//		field4.setLayoutX(1450);
//		field4.setLayoutY(heightPosition);
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

