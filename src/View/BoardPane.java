package View;

import java.util.ArrayList;
import java.util.Random;

import controller.BoardController;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.QuadCurve;
import javafx.scene.shape.Rectangle;
import model.PatternCard;
import model.PlacedDice;
import model.Player;
import model.Space;

public class BoardPane extends Pane {
	private QuadCurve top;
	private Rectangle square;
	private Circle tokenPlaceholder;
	private ArrayList<PatternPane> board;
	private GridPane field;
	private BoardController controller;
	private PatternPane selected;
	private int patternid;
	private boolean allowsMovement;
	private Label tokenAmount;
	private PatternCard chosenCard;
	private Label username;
	private Label score;
	private boolean self;
	private boolean placed = false;
	private boolean toolCardActive = false;
	private Random random;

	/// *
	// This constructor requires a rootPane to return the selected DicePane. It also
	/// asks for an int that it can give to the BoardController. This number stands
	/// for the number of the windowPattern.
	/// **
	public BoardPane(BoardController bc, PatternCard pc, Player p) {
		random = new Random();
		allowsMovement = false;
		controller = bc;
		chosenCard = pc;
		self = p.getSelf();
		score = new Label("" + p.getScore());
		tokenAmount = new Label("" + p.getTokenAmount());
		this.username = new Label(p.getUsername());
		this.username.setLayoutX(175);
		this.username.setLayoutY(70);
		setShape();
		setGrid();
		getChildren().addAll(top, square, tokenPlaceholder, tokenAmount, this.username, score);
		setBoard();
		addPlacedDice(p.getDiceField());
		
	}
	
	public void setChosenCard(PatternCard chosenCard) {
		this.chosenCard = chosenCard;
	}

	/// *
	// Sets the patternId.
	/// **

	public void setPatternId(int i) {
		patternid = i;
	}

	/// *
	// Returns the patternId.
	/// **

	public int getPatternId() {
		return patternid;
	}

	/// *
	// Sets the shape of the board, it has nothing to do with the actual
	/// windowPattern.
	/// **

	// TODO THOSE NUMBERS MIGHT BE MOVED TO A NEW MODEL
	private void setShape() {
		tokenAmount.setLayoutX(145);
		tokenAmount.setLayoutY(40);
		score.setLayoutX(205);
		score.setLayoutY(40);
		top = new QuadCurve(0, 100, 150, -100, 300, 100);
		top.setFill(generateRandomColor());
		top.setOpacity(50);
		if(self) {
			top.setFill(Color.LIGHTBLUE);
		}
		tokenPlaceholder = new Circle();
		tokenPlaceholder.setRadius(25);
		tokenPlaceholder.setCenterX(150);
		tokenPlaceholder.setCenterY(50);
		tokenPlaceholder.setFill(Color.TRANSPARENT);
		tokenPlaceholder.setStroke(Color.BLACK);
		
		square = new Rectangle();
		square.setX(0);
		square.setY(100);
		square.setWidth(300);
		square.setHeight(240);
		square.setFill(Color.WHITE);
		square.setStroke(Color.BLACK);
		top.setStroke(Color.BLACK);
		top.setStrokeWidth(0.5);
	}

	/// *
	// Sets the GridPane called field that represents the pattern.
	/// **

	private void setGrid() {
		field = new GridPane();
		field.setLayoutX(square.getX());
		field.setLayoutY(square.getY());
		field.setVgap(8);
		field.setHgap(8);
	}

	/// *
	// Sets the color of the PatternPane on the board.
	/// **
	public void setColor(int value, String color) {
		board.get(value).setColor(color);
	}

	/// *
	// Sets the color of the PatternPane to white;
	/// **

	public void setWhite(int value) {
		board.get(value).setWhite();
	}

	/// *
	// This might seem quite complex.
	// counter counts all the actual dices. The gridPane AKA field gets filled
	/// vertically. Firstly board adds the patternPane to itsself. Go to PatternPane
	/// if you want the constructor explained.
	// This dicePane constructor asks for the amount of eyes and the color. The
	/// amount can be 0 because it is a template and not an actual dice.
	// after this step field adds the last record of the ArrayList board to the
	/// grid. All of the position come straight from the class Space, which got them
	/// out of the DB.
	/// **

	public void setBoard() {
		int counter = 0;
		board = new ArrayList<>();
        this.getChildren().remove(field);
		field.getChildren().clear();
		for (int c = 1; c <= 5; c++) {
			for (int i = 0; i < 4; i++) {
				board.add(new PatternPane(this,
						new DicePane(chosenCard.getPatternField().get(counter).getEyes(),
								chosenCard.getPatternField().get(counter).getColor()),
						chosenCard.getPatternField().get(counter).getXPos(), chosenCard.getPatternField().get(counter).getYPos()));
				field.add(board.get(board.size() - 1), chosenCard.getPatternField().get(counter).getXPos(),
						chosenCard.getPatternField().get(counter).getYPos());
				counter++;
			}
		}
		getChildren().add(field);
	}
	
	public void addPlacedDice(ArrayList<PlacedDice> diceField) {
		for(PlacedDice pd : diceField) {
			for(int i = 0;i<board.size(); i++) {
				if(board.get(i).getX() == pd.getXpos() && board.get(i).getY() == pd.getYpos()) {
					DicePane temporary = new DicePane(pd.getEyes(), pd.getDieColor(), pd.getDieNumber());
					board.get(i).setDice(temporary);
				}
			}
		}
	}


	/// *
	// Returns the ArrayList with Spaces.
	/// **

	public ArrayList<Space> getPatternField() {
		return controller.getPatternCard();
	}

	/// *
	// This is what rootPane is used for, to get the selected DicePane and to delete
	/// it. PatternPane uses those methods.
	/// **
	// TODO FIX THAT THE SELECTED DICE IS GIVEN THROUGH THE CONTROLLER, IF POSSIBLE
	public PatternPane getSelected() {
		return selected;
	}

	public void setSelectedPatternPane(PatternPane selected) {
		this.selected = selected;
	}

	public void setSelected(DicePane selected, int x, int y) {
		for (int i = 0; i < board.size(); i++) {
			if (board.get(i).getX() == x && board.get(i).getY() == y) {
				board.get(i).setDice(selected);
			}
		}
		placed = true;
	}

	public void giveCords(int x, int y) {
		if(!placed && !toolCardActive) {
			controller.validateMove(x, y);
		}
	}

	public void getTurns() {
		controller.getTurns();
	}

	public void enableDiceMovement() {
		for (PatternPane patternPane : board) {
			patternPane.setMouseTransparent(false);
		}
	}

	public void disableDiceMovement(int x, int y) {
		for (PatternPane patternPane : board) {
			if (patternPane.getDice() != null) {
				patternPane.setMouseTransparent(true);
			}
			if (patternPane.getX() == x && patternPane.getY() == y) {
				patternPane.setMouseTransparent(false);
			}
		}
	}

	public void allowMovement() {
		allowsMovement = true;
		toolCardActive = true;
	}

	public void disableMovement(int x, int y) {
		allowsMovement = false;
		toolCardActive = false;
		disableDiceMovement(x, y);
	}

	public boolean getAllowsMovement() {
		return allowsMovement;
	}

	public void moveDice(int dieNumber, String color, int xPos, int yPos) {
		controller.validateToolcardTwo(dieNumber, color, xPos, yPos);

	}

	public void moveDiceAccepted(int dieNumber, String color, int xPos, int yPos) {
		for (int i = 0; i < board.size(); i++) {
			if (board.get(i).getX() == xPos && board.get(i).getY() == yPos) {
				board.get(i).setDice(selected.getDice());
			}
		}

		for (int i = 0; i < board.size(); i++) {
			if (board.get(i).getDice() != null) {
				if (board.get(i).getDiceNumber() == dieNumber && board.get(i).getDiceColor().equals(color)) {
					selected = null;
				}
			}
		}
		allowsMovement = false;
		}
	
	
	public boolean getPlaced() {
		return placed;
	}

	public void setSelectedToNull() {
		selected = null;
	}
	
	public void setLabelValue(int value) {
		tokenAmount.setText("" + value);
	}
	
	public void changeTokenAmount(int value) {
		tokenAmount.setText("" + value);
	}
	
	public boolean getSelf() {
		return self;
	}
	
	public void setToolcardActiveFalse() {
		toolCardActive = false;
	}
	
	public void setToolcardActiveTrue() {
		toolCardActive = true;
	}
	
	public void resetPlaced() {
		placed = false;
	}
	
	private Color generateRandomColor() {
		int r = random.nextInt(255);
		int g = random.nextInt(255);
		int b = random.nextInt(255);
		
		return Color.rgb(r, g, b);
	}
	
	public void setScore(int score) {
		this.score.setText("" + score);
	}

}
