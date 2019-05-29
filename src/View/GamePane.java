package View;

import java.util.ArrayList;
import java.util.Random;

import controller.GameController;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import model.Dice;

public class GamePane extends BorderPane {
	/// *
	// Sets all instances of a variety of objects, some of them might be removed
	/// later on.
	/// **
	private Button close;

//	private BoardPane player1;
//	private BoardPane player2;
//	private BoardPane player3;
//	private BoardPane player4;
	private ArrayList<BoardPane> playField;
	private HBox boards;
	private HBox diceRow1;
	private HBox diceRow2;
	private HBox diceRow3;
	private DicePane selected;
	private ArrayList<ToolCardPane> toolcards;
	private ArrayList<ObjectiveCardPane> objectiveCards;
	private PrivateCardPane pc;
	private ObjectiveCardPane ocp;
	private ObjectiveCardPane ocp2;
	private HeaderPane objectiveCard;
	private HeaderPane privateCard;
	private HeaderPane toolCard;
	private BorderPane bottom;
	private GameController controller;
//	private ArrayList<Dice> diceArray;
	private boolean toolcardIsActiveOne;
	private boolean toolcardIsActiveSix;
	private boolean toolcardIsActiveTen;
	private boolean toolcardIsActiveEleven;
	private DecisionPane decisionpane;
	private Random r;
	private RoundTrack track;

	/// *
	// RootPane creates the controller to communicate with the model that gets all
	/// 90 dices.
	// This constructor also adds some boards, dices and all of the diverse cards to
	/// the screen.
	/// **

	public GamePane(GameController gameController) {
		r = new Random();
		objectiveCards = new ArrayList<>();
		playField = new ArrayList<>();
		toolcardIsActiveOne = false;
		toolcardIsActiveSix = false;
		toolcardIsActiveTen = false;
		toolcardIsActiveEleven = false;

		decisionpane = new DecisionPane(this);

		diceRow1 = new HBox();
		diceRow2 = new HBox();
		diceRow3 = new HBox();

		diceRow1.setSpacing(20);
		diceRow2.setSpacing(20);
		diceRow3.setSpacing(20);
		
		track = new RoundTrack();


		this.controller = gameController;
		setBoard();
		addTrack();
		addDice();
		finish();
	}

	/// *
	// Sets all boards.
	/// **

	private void setBoard() {
		/// 
		// The the number in the constructor from BoardPane stands for the number of the
		/// windowpattern in the DB.
		
		playField = controller.getBoards();
		
		for (int i = 0; i < playField.size(); i++) {
			if(playField.get(i).getSelf() == false) {
				playField.get(i).setBackground(new Background(new BackgroundFill(Color.RED, null, null)));
			}
		}
		
//		player1 = controller.returnBoardPane();
//		setBoardPlayerOne();
//
//		player2 = controller.getOpponentBoard().get(0);
////		
//		player3 = controller.getOpponentBoard().get(1);
//		player4 = controller.getOpponentBoard().get(2);

		boards = new HBox();
		boards.getChildren().addAll(playField);
		boards.setSpacing(20);
		boards.setPadding(new Insets(0, 0, 0, 50));
	}

	/// *
	// Add dices to the screen, this constructor of dicePane wants an instance of
	/// the model Dice.
	// This model contains the amount of eyes and the color that the dice should be.
	/// **

	public void addTrack() {
		setTop(track);
		//setAlignment(track, Pos.CENTER);
	}
	
	public void getLeftover() {
		
	}
	
	public void addDice() {

		diceRow1.getChildren().clear();
		for (int i = 0; i < getPlayableDices().size(); i++) {
			if (diceRow1.getChildren().size() < 4) {
				diceRow1.getChildren().add(new DicePane(getPlayableDices().get(i).getEyes(),
						getPlayableDices().get(i).getDieColor(), getPlayableDices().get(i).getDieNumber(), this));
			}
		}

		diceRow2.getChildren().clear();
		for (int i = 4; i < getPlayableDices().size(); i++) {
			if (diceRow2.getChildren().size() < 4) {

				diceRow2.getChildren().add(new DicePane(getPlayableDices().get(i).getEyes(),
						getPlayableDices().get(i).getDieColor(), getPlayableDices().get(i).getDieNumber(), this));
			}
		}

		diceRow3.getChildren().clear();
		for (int i = 8; i < getPlayableDices().size(); i++) {
			if (diceRow3.getChildren().size() < 4) {

				diceRow3.getChildren().add(new DicePane(getPlayableDices().get(i).getEyes(),
						getPlayableDices().get(i).getDieColor(), getPlayableDices().get(i).getDieNumber(), this));
			}
		}

	}

	/// *
	// Sets all cards, also adds the labels above the cards.
	/// **

	private void setCards() {
		// Creates new cards
		this.close = new Button("opgeven");
		pc = new PrivateCardPane();
//		ocp = new ObjectiveCardPane(1);
//		ocp2 = new ObjectiveCardPane(2);
		VBox allDiceRows = new VBox(diceRow1, diceRow2, diceRow3);
		allDiceRows.setSpacing(8);

		toolcards = controller.getToolCards();
		objectiveCards = controller.getObjectiveCardPanes();
		// Creates new headers
		objectiveCard = new HeaderPane();
		privateCard = new HeaderPane();
		toolCard = new HeaderPane();
		// Changes the text of the labels
		objectiveCard.changeLabel("Objective Cards");
		privateCard.changeLabel("Private Card");
		toolCard.changeLabel("Toolcards");
		// changes the price labels
		// tcp1.changePrice("2");
		bottom = new BorderPane();
		bottom.setPadding(new Insets(0, 130, 50, 50));
		bottom.setLeft(allDiceRows);

		HBox oc = new HBox();
		oc.setSpacing(5);
		oc.getChildren().addAll(objectiveCards);
		VBox finalOc = new VBox(objectiveCard, oc);
		finalOc.setSpacing(5);

		VBox finalPc = new VBox(privateCard, pc);
		finalPc.setSpacing(5);
		HBox tcp1 = new HBox();
		tcp1.getChildren().addAll(toolcards);
		tcp1.setSpacing(5);
		VBox finalTcp = new VBox(toolCard, tcp1);
		finalTcp.setSpacing(5);
		HBox toolCards = new HBox(finalOc, finalPc, finalTcp, close);
		toolCards.setSpacing(5);
		bottom.setRight(toolCards);

		// close.setOnAction(e -> controller.getProgress().closeGame());

	}

	/// *
	// Gets all dices out of the DB. This means the size of the Array is 90.
	/// **

	public ArrayList<Dice> getDiceArray() {
		return controller.getPlayableDices();
	}

	/// *
	// Adds all panes to the rootPane.
	/// **
	private void finish() {
		setCards();
		setCenter(boards);
		setBottom(bottom);
		setBackground(new Background(new BackgroundFill(Color.GRAY, null, null)));
	}

	/// *
	// Delete the selected DicePane by setting its value to null.
	/// **

	public void deleteSelected() {
		selected = null;
	}

	/// *
	// Selected becomes the dicePane that has been clicked
	/// **

	public void setSelected(DicePane p) {
		selected = p;
		if (toolcardIsActiveOne) {
			decisionpane.showInfoBoxToolcardOne();
			setRight(decisionpane);
		}
		if (toolcardIsActiveSix) {
			randomSelected();
		}
		if (toolcardIsActiveTen) {
			flipDice();
		}
		if (toolcardIsActiveEleven) {
			decisionpane.showInfoBoxToolcardEleven();
			setRight(decisionpane);
		}
	}

	public void downSelected() {

		for (int i = 0; i < diceRow1.getChildren().size(); i++) {
			DicePane temporarilyDice = (DicePane) diceRow1.getChildren().get(i);
			if (selected.getDieNumber() == temporarilyDice.getDieNumber()
					&& selected.getColor().equals(temporarilyDice.getColor())) {
				if (selected.getValue() == 1) {
					decisionpane.giveError();
					return;
				}
				temporarilyDice.removeEyes();
				temporarilyDice.setValue(temporarilyDice.getValue() - 1);
				temporarilyDice.addDiceEyes(temporarilyDice.getValue());
				disableToolcard();
				setRight(null);
				controller.updateEyes(selected.getValue(), selected.getDieNumber(), selected.getColor());

			}

		}
	}

	public void upSelected() {

		for (int i = 0; i < diceRow1.getChildren().size(); i++) {
			DicePane temporarilyDice = (DicePane) diceRow1.getChildren().get(i);
			if (selected.getDieNumber() == temporarilyDice.getDieNumber()
					&& selected.getColor().equals(temporarilyDice.getColor())) {
				if (selected.getValue() == 6) {
					decisionpane.giveError();
					return;
				}
				temporarilyDice.removeEyes();
				temporarilyDice.setValue(temporarilyDice.getValue() + 1);
				temporarilyDice.addDiceEyes(temporarilyDice.getValue());
				disableToolcard();
				setRight(null);
				controller.updateEyes(selected.getValue(), selected.getDieNumber(), selected.getColor());
			}

		}
	}

	public void randomSelected() {

		for (int i = 0; i < diceRow1.getChildren().size(); i++) {
			DicePane temporarilyDice = (DicePane) diceRow1.getChildren().get(i);
			if (selected.getDieNumber() == temporarilyDice.getDieNumber()
					&& selected.getColor().equals(temporarilyDice.getColor())) {
				temporarilyDice.removeEyes();
				temporarilyDice.setValue(r.nextInt(6) + 1);
				temporarilyDice.addDiceEyes(temporarilyDice.getValue());
				disableToolcard();
				controller.updateEyes(selected.getValue(), selected.getDieNumber(), selected.getColor());

			}

		}
	}

	public void flipDice() {

		for (int i = 0; i < diceRow1.getChildren().size(); i++) {
			DicePane temporarilyDice = (DicePane) diceRow1.getChildren().get(i);
			if (selected.getDieNumber() == temporarilyDice.getDieNumber()
					&& selected.getColor().equals(temporarilyDice.getColor())) {
				temporarilyDice.removeEyes();
				switch (temporarilyDice.getValue()) {
				case 1:
					temporarilyDice.setValue(6);
					break;
				case 2:
					temporarilyDice.setValue(5);
					break;
				case 3:
					temporarilyDice.setValue(4);
					break;
				case 4:
					temporarilyDice.setValue(3);
					break;
				case 5:
					temporarilyDice.setValue(2);
					break;
				case 6:
					temporarilyDice.setValue(1);
					break;
				}
				temporarilyDice.addDiceEyes(temporarilyDice.getValue());
				disableToolcard();
				controller.updateEyes(selected.getValue(), selected.getDieNumber(), selected.getColor());

			}

		}
	}

	public void SelectedStaysEqual() {
		disableToolcard();
		setRight(null);
	}

	public void setToolCardOneActive() {
		toolcardIsActiveOne = true;
	}

	public void setToolCardSixActive() {
		toolcardIsActiveSix = true;
	}

	public void setToolCardTenActive() {
		toolcardIsActiveTen = true;
	}

	public void setToolCardElevenActive() {
		toolcardIsActiveEleven = true;
	}

	public void swapDice(int chosenEyes) {
		controller.swapDice(selected.getDieNumber(), selected.getColor(), selected.getValue(), chosenEyes);
		disableToolcard();
		setRight(null);
	}

	public void disableToolcard() {
		toolcardIsActiveOne = false;
		toolcardIsActiveSix = false;
		toolcardIsActiveTen = false;
		toolcardIsActiveEleven = false;

	}

	// public void enableDiceMovement() {
	// player1.enableDiceMovement();
	// }

	/// *
	// Returns selected, will be used in the class patternPane.
	/// **
	public DicePane getSelected() {
		return selected;
	}

	private ArrayList<Dice> getPlayableDices() {
		return controller.getPlayableDices();
	}

	public void setBoardPlayerOne() {
//		player1 = controller.returnBoardPane();
//		player2 = controller.getOpponentBoard().get(0);
//		player3 = controller.getOpponentBoard().get(1);
//		player4 = controller.getOpponentBoard().get(2);
	}

	public Button getClose() {
		return close;
	}

}
