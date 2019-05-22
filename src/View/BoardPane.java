package View;

import java.util.ArrayList;

import controller.BoardController;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.QuadCurve;
import javafx.scene.shape.Rectangle;
import model.Space;

public class BoardPane extends Pane {
	private QuadCurve top;
	private Rectangle square;
	// private ArrayList<DicePane> board;
	private ArrayList<PatternPane> board;
	// private DicePane selected;
	private GridPane field;
	private BoardController controller;
	private PatternPane selected;
	private int patternid;
	private boolean transparent;
	private boolean allowsMovement;
	// private Board b;

	/// *
	// This constructor requires a rootPane to return the selected DicePane. It also
	/// asks for an int that it can give to the BoardController. This number stands
	/// for the number of the windowPattern.
	/// **
	public BoardPane(BoardController bc) {
		allowsMovement = false;
		// setPrefSize(s.getWidth()/4, s.getHeight() - 200);
		// transparent = true;
		// setPatternId(pattern);
		controller = bc;
		// controller.setPatternId(patternid);
		setShape();
		setGrid();
		// b = be;
		getChildren().addAll(top, square);
		setBoard();
	}

	public BoardPane(ArrayList<Space> opponentBoard) {
		// setPrefSize(s.getWidth()/4, s.getHeight() - 200);
		// transparent = true;
		// setPatternId(pattern);
		// controller = bc;
		// controller.setPatternId(patternid);
		setShape();
		setGrid();
		// b = be;
		getChildren().addAll(top, square);
		setOpponentBoard(opponentBoard);
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

	/// *
	// Sets the GridPane called field that represents the pattern.
	/// **

	private void setGrid() {
		field = new GridPane();
		field.setLayoutX(square.getX());
		field.setLayoutY(square.getY() + 70);
		field.setVgap(8);
		field.setHgap(8);
	}

	// public void setDice(DicePane p) {
	// board.get(2).setDice(p);
	// field.add(p, 2, 2);
	// }

	// public void setEye(int value, int numberOfEyes) {
	// board.get(value).addPatternEyes(numberOfEyes);
	// }

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

	private void setBoard() {
		int counter = 0;
		board = new ArrayList<>();
		for (int c = 1; c <= 5; c++) {
			for (int i = 0; i < 4; i++) {
				board.add(new PatternPane(this,
						new DicePane(getPatternField().get(counter).getEyes(),
								getPatternField().get(counter).getColor()),
						getPatternField().get(counter).getXPos(), getPatternField().get(counter).getYPos()));
				field.add(board.get(board.size() - 1), getPatternField().get(counter).getXPos(),
						getPatternField().get(counter).getYPos());
				counter++;
			}
		}
		getChildren().add(field);
		// System.out.println("Should have worked");
	}
	
	

	private void setOpponentBoard(ArrayList<Space> opponentBoard) {
		int counter = 0;
		board = new ArrayList<>();
		for (int c = 1; c <= 5; c++) {
			for (int i = 0; i < 4; i++) {
				board.add(new PatternPane(this,
						new DicePane(opponentBoard.get(counter).getEyes(), opponentBoard.get(counter).getColor())));
				field.add(board.get(board.size() - 1), opponentBoard.get(counter).getXPos(),
						opponentBoard.get(counter).getYPos());
				counter++;
			}
		}
		getChildren().add(field);
		// System.out.println("Should have worked");
	}

	/// *
	// Returns the ArrayList with Spaces.
	/// **

	public ArrayList<Space> getPatternField() {
		return controller.getPatternCard();
	}

	// public void getClicked(PatternPane p) {
	// System.out.println("" + field.getRowIndex(p) + " " +
	// field.getColumnIndex(p));
	// }

	// public void setSelected(DicePane p, boolean onOff) {
	// selected = p;
	// System.out.println("" + selected);
	// }

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
	}

	public void giveCords(int x, int y) {
		controller.validateMove(x, y);
	}
	//
	// public void deleteSelected() {
	// rootPane.deleteSelected();
	//
	// }

	// public void switchTransparent() {
	// transparent = !transparent;
	// setMouseTransparent(transparent);
	// }

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
			if(patternPane.getDice() != null) {
				patternPane.setMouseTransparent(true);
			}
			if(patternPane.getX() == x && patternPane.getY() == y) {
				patternPane.setMouseTransparent(false);
			}
		}
	}

	public void allowMovement() {
		allowsMovement = true;
	}
	
	public void disableMovement(int x, int y) {
		allowsMovement = false;
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
//					board.get(i).setDiceNull();
				}
			}
			
		}
	}
	
	public void setSelectedToNull() {
		selected = null;
	}

}
