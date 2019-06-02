package View;

import java.util.ArrayList;
import java.util.Random;

import controller.GameController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Screen;
import model.Dice;

public class GamePane extends BorderPane {
	/// *
	// Sets all instances of a variety of objects, some of them might be removed
	/// later on.
	/// **
	private Button close;
	private ArrayList<BoardPane> playField;
	private HBox boards;
	private HBox diceRow1;
	private HBox diceRow2;
	private HBox diceRow3;
	private VBox allDiceRows;
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
		allDiceRows = new VBox();
		allDiceRows.setSpacing(8);
		diceRow1 = new HBox();
		diceRow2 = new HBox();
		diceRow3 = new HBox();

		diceRow1.setSpacing(20);
		diceRow2.setSpacing(20);
		diceRow3.setSpacing(20);

		endTurn = new Button("Be�indig beurt.");
		track = new RoundTrack(gameController, gameController.getGame(), gameController.getGame().getLeftovers());

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
			playField.get(i).setMaxHeight(340);
			if (playField.get(i).getSelf() == false) {
				playField.get(i).setBackground(new Background(new BackgroundFill(Color.RED, null, null)));
			}
		}
		userClickables = new VBox();
		boards = new HBox();

		// aligns dice with user buttons
		userClickables.getChildren().add(allDiceRows);
		userClickables.getChildren().add(endTurn);
		userClickables.setAlignment(Pos.CENTER);
		userClickables.setSpacing(10);
		userClickables.setPadding(new Insets(20));

		boards.getChildren().addAll(playField);

		boards.setSpacing(20);
		boards.setPadding(new Insets(0, 0, 0, 5));
		boards.setAlignment(Pos.CENTER);

	}

	public void addTrack() {
		currentInfo = new Label(controller.shoutCurrentPlayer());
		currentInfo.setFont(new Font("Arial", 30));
		currentInfo.setTextFill(Color.WHITE);
		roundTrack = new HBox();
		roundTrack.getChildren().addAll(currentInfo, track);
		roundTrack.setAlignment(Pos.CENTER);
		roundTrack.setPadding(new Insets(20));
		roundTrack.setSpacing(20);
		setTop(roundTrack);
	}

	public void changeInfo(String lp) {
		this.currentInfo.setText(lp);
	}

	/// *
	// Add dices to the screen, this constructor of dicePane wants an instance of
	/// the model Dice.
	// This model contains the amount of eyes and the color that the dice should be.
	/// **

	public void addDice() {
		allDiceRows.getChildren().clear();

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
		allDiceRows.getChildren().addAll(diceRow1, diceRow2, diceRow3);
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
	
	public void refreshToolcards() {
		
	}

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
		
		allKeyCards.setSpacing(5);
		allKeyCards.setAlignment(Pos.CENTER);
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
		setRight(userClickables);
		setBottom(bottom);

		Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();

		// set boundaries to visible bounds of the main screen
		this.setPrefWidth(primaryScreenBounds.getWidth());
		this.setPrefHeight(primaryScreenBounds.getHeight() * 1.1);

		// sets background_image
		this.setBackground(new Background(new BackgroundImage(new Image("/Resources/gameBackground.jpg"),
				BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,
				new BackgroundSize(0, 0, false, false, false, true))));
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
		for (int i = 0; i < allDiceRows.getChildren().size(); i++) {
			HBox temporary = (HBox) allDiceRows.getChildren().get(i);
			for (int j = 0; j < temporary.getChildren().size(); j++) {
				// TODO Make it so that it will check all dicerows
				DicePane temporarilyDice = (DicePane) temporary.getChildren().get(j);
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
					userClickables.getChildren().remove(userClickables.getChildren().size() - 1);
					boards.getChildren().remove(boards.getChildren().size() - 1);
					boards.getChildren().add(userClickables);
					controller.updateEyes(temporarilyDice.getValue(), selected.getDieNumber(), selected.getColor());
				}
			}
		}

	}

	public void upSelected() {

		for (int i = 0; i < allDiceRows.getChildren().size(); i++) {
			HBox temporary = (HBox) allDiceRows.getChildren().get(i);
			for (int j = 0; j < temporary.getChildren().size(); j++) {
				// TODO Make it so that it will check all dicerows
				DicePane temporarilyDice = (DicePane) temporary.getChildren().get(j);
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
					userClickables.getChildren().remove(userClickables.getChildren().size() - 1);
					boards.getChildren().remove(boards.getChildren().size() - 1);
					boards.getChildren().add(userClickables);
					controller.updateEyes(temporarilyDice.getValue(), selected.getDieNumber(), selected.getColor());
				}

			}
		}
	}

	public void randomSelected() {

		for (int i = 0; i < allDiceRows.getChildren().size(); i++) {
			HBox temporary = (HBox) allDiceRows.getChildren().get(i);
			for (int j = 0; j < temporary.getChildren().size(); j++) {
				DicePane temporarilyDice = (DicePane) temporary.getChildren().get(j);
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
	}

	public void flipDice() {
		for (int i = 0; i < allDiceRows.getChildren().size(); i++) {
			HBox temporary = (HBox) allDiceRows.getChildren().get(i);
			for (int j = 0; j < temporary.getChildren().size(); j++) {
				DicePane temporarilyDice = (DicePane) temporary.getChildren().get(j);
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
					controller.updateEyes(temporarilyDice.getValue(), selected.getDieNumber(), selected.getColor());

				}
			}

		}
	}

	public void SelectedStaysEqual() {
		disableToolcard();
		userClickables.getChildren().remove(userClickables.getChildren().size() - 1);
		boards.getChildren().remove(boards.getChildren().size() - 1);
		boards.getChildren().add(userClickables);
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
		controller.swapDice(selected.getDieNumber(), selected.getColor(), chosenEyes);
		disableToolcard();
		userClickables.getChildren().remove(userClickables.getChildren().size() - 1);
		boards.getChildren().remove(boards.getChildren().size() - 1);
		boards.getChildren().add(userClickables);
	}

	public void disableToolcard() {
		toolcardIsActiveOne = false;
		toolcardIsActiveSix = false;
		toolcardIsActiveTen = false;
		toolcardIsActiveEleven = false;
		for (BoardPane bp : controller.getBoards()) {
			if (bp.getSelf()) {
				bp.setToolcardActiveFalse();
			}
		}

	}

	/// *
	// Returns selected, will be used in the class patternPane.
	/// **
	public DicePane getSelected() {
		return selected;
	}

	private ArrayList<Dice> getPlayableDices() {
		return controller.getPlayableDices();
	}

	public Button getClose() {
		return close;
	}

	public void setCurrentPlayerLabel(String string) {
		currentInfo.setText(string);
	}

	public void setRoundTrack(ArrayList<ArrayList<Dice>> d) {
		
		track.setRoundTrack(d);
	}
	
	
	

}
