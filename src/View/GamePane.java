package View;

import java.util.ArrayList;
import java.util.Random;

import controller.GameController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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

	// private BoardPane player1;
	// private BoardPane player2;
	// private BoardPane player3;
	// private BoardPane player4;
	private ArrayList<BoardPane> playField;
	private HBox boards;
	private HBox diceRow1;
	private HBox diceRow2;
	private HBox diceRow3;
	private HBox roundTrack;
	private DicePane selected;
	private ArrayList<ToolCardPane> toolcards;
	private ArrayList<ObjectiveCardPane> objectiveCards;
	private PrivateCardPane privateObjectiveCard;
	private HeaderPane objectiveCardTitle;
	private HeaderPane privateCardTitle;
	private HeaderPane toolCardTitle;
	private BorderPane bottom;
	private GameController controller;
	// private ArrayList<Dice> diceArray;
	private boolean toolcardIsActiveOne;
	private boolean toolcardIsActiveSix;
	private boolean toolcardIsActiveTen;
	private boolean toolcardIsActiveEleven;
	private DecisionPane decisionpane;
	private Random r;
	private RoundTrack track;
	private Button endTurn;
	private VBox userClickables;
	private Label currentInfo;

	/// *
	// RootPane creates the controller to communicate with the model that gets all
	/// 90 dices.
	// This constructor also adds some boards, dices and all of the diverse cards to
	/// the screen.
	/// **

	public GamePane(GameController gameController) {
		this.controller = gameController;
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

		endTurn = new Button("Beëindig beurt.");
//		endTurn.setOnAction(e -> handle());

		track = new RoundTrack();

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
			if (playField.get(i).getSelf() == false) {
				playField.get(i).setBackground(new Background(new BackgroundFill(Color.RED, null, null)));
			}
		}

		userClickables = new VBox();
		boards = new HBox();

		// sets dice in rows
		VBox allDiceRows = new VBox(diceRow1, diceRow2, diceRow3);
		allDiceRows.setSpacing(8);

		// aligns dice with user buttons
		userClickables.getChildren().add(allDiceRows);
		userClickables.getChildren().add(endTurn);

		boards.getChildren().addAll(playField);
		boards.getChildren().addAll(userClickables);

		boards.setSpacing(20);
		boards.setPadding(new Insets(0, 0, 0, 5));
	}

	public void addTrack() {
		currentInfo = new Label(controller.shoutCurrentPlayer());
		roundTrack = new HBox();
		roundTrack.getChildren().addAll(currentInfo, track);
		setTop(roundTrack);
		roundTrack.setAlignment(Pos.CENTER);
	}

	/// *
	// Add dices to the screen, this constructor of dicePane wants an instance of
	/// the model Dice.
	// This model contains the amount of eyes and the color that the dice should be.
	/// **

	public void getLeftover() {

	}

	public void addDice() {

		diceRow1.getChildren().clear();
		for (int i = 0; i < getPlayableDices().size(); i++) {
			if (diceRow1.getChildren().size() < 3) {
				diceRow1.getChildren().add(new DicePane(getPlayableDices().get(i).getEyes(),
						getPlayableDices().get(i).getDieColor(), getPlayableDices().get(i).getDieNumber(), this));
			}
		}

		diceRow2.getChildren().clear();
		for (int i = 3; i < getPlayableDices().size(); i++) {
			if (diceRow2.getChildren().size() < 3) {

				diceRow2.getChildren().add(new DicePane(getPlayableDices().get(i).getEyes(),
						getPlayableDices().get(i).getDieColor(), getPlayableDices().get(i).getDieNumber(), this));
			}
		}

		diceRow3.getChildren().clear();
		for (int i = 6; i < getPlayableDices().size(); i++) {
			if (diceRow3.getChildren().size() < 3) {

				diceRow3.getChildren().add(new DicePane(getPlayableDices().get(i).getEyes(),
						getPlayableDices().get(i).getDieColor(), getPlayableDices().get(i).getDieNumber(), this));
			}
		}

	}

	// Dit kan pas gemaakt worden wanneer gameverloop werkt:

	// public void getLeftovers() {
	// if (if (beurt overslaan = amount of players x 2) {
	// getPlayableDices();
	// }
	// }

	/// *
	// Sets all cards, also adds the labels above the cards.
	/// **

	private void setCards() {
		// Creates new cards
		privateObjectiveCard = new PrivateCardPane();
		privateObjectiveCard.setDice(controller.getPrivateCardColor());

		// sets dice in rows
		bottom = new BorderPane();

		// gets cards information
		toolcards = controller.getToolCards();
		objectiveCards = controller.getObjectiveCardPanes();

		// Creates new headers
		objectiveCardTitle = new HeaderPane();
		privateCardTitle = new HeaderPane();
		toolCardTitle = new HeaderPane();

		// Changes the text of the labels
		objectiveCardTitle.changeLabel("Objective Cards");
		privateCardTitle.changeLabel("Private Card");
		toolCardTitle.changeLabel("Toolcards");

		// aligns objective cards horizontally
		HBox objectiveCardHBox = new HBox();
		objectiveCardHBox.setSpacing(5);
		objectiveCardHBox.getChildren().addAll(objectiveCards);

		// aligns objectivecards with the header text "objectivecard"
		VBox alignObjectiveCardWithHeaderText = new VBox(objectiveCardTitle, objectiveCardHBox);
		alignObjectiveCardWithHeaderText.setSpacing(5);

		// aligns privateObjectiveCards with the header text "privateObjectiveCard"
		VBox alignPrivateObjectiveCardWithHeaderText = new VBox(privateCardTitle, privateObjectiveCard);
		alignPrivateObjectiveCardWithHeaderText.setSpacing(5);

		// aligns the toolcards horizontally
		HBox toolcardHBox = new HBox();
		toolcardHBox.getChildren().addAll(toolcards);
		toolcardHBox.setSpacing(5);

		// aligns the toolcards with the header text "toolcards"
		VBox alignToolCardWithHeaderText = new VBox(toolCardTitle, toolcardHBox);
		alignToolCardWithHeaderText.setSpacing(5);

		// aligns all key cards horizontally
		HBox allKeyCards = new HBox(alignObjectiveCardWithHeaderText, alignPrivateObjectiveCardWithHeaderText,
				alignToolCardWithHeaderText);
		allKeyCards.setPadding(new Insets(0, 0, 0, 10));
		// , new VBox(controller.getChatBox().getScreen(), close
		// uit de bovenstaande hbox gehaald, gaf nullpointer.

		allKeyCards.setSpacing(5);
		bottom.setCenter(allKeyCards);

	}

	public Button getTurnSave() {
		return this.endTurn;
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
			userClickables.getChildren().add(decisionpane);
			;
		}
		if (toolcardIsActiveSix) {
			randomSelected();
		}
		if (toolcardIsActiveTen) {
			flipDice();
		}
		if (toolcardIsActiveEleven) {
			decisionpane.showInfoBoxToolcardEleven();
			userClickables.getChildren().add(decisionpane);
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
		// player1 = controller.returnBoardPane();
		// player2 = controller.getOpponentBoard().get(0);
		// player3 = controller.getOpponentBoard().get(1);
		// player4 = controller.getOpponentBoard().get(2);
	}

	public Button getClose() {
		return close;
	}
	
	public void setCurrentPlayerLabel(String string) {
		currentInfo.setText(string);
	}

//	public void handle() {
//		System.out.println("ja je druk de goeie knop inteunt");
//		currentInfo.setText(controller.shoutCurrentPlayer());	
//		controller.endTurn();
//	}

}
